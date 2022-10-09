package bd;

import server.Request;
import server.Response;
import util.Database;
import utils.IOUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BDUser {
    Database database;
    Response response;

    private static final String TABLE_NAME = "users",
            USERNAME_COLUMN = "username",
            PASSWORD_HASH_COLUMN = "password_hash";


    public BDUser() {
    }

    public boolean runBDUser(Database database) {
        this.database = database;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                USERNAME_COLUMN + " varchar(16) PRIMARY KEY, " +
                PASSWORD_HASH_COLUMN + " varchar(128)" + ")";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            return true;
        } catch (SQLException ignored) {
            IOUtils.printErr("БД недоступна!");
            return false;
        }
    }

    public List<String> getAll() {
        List<String> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + TABLE_NAME;
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                users.add(getUsername(results));
            }
            results.close();
        } catch (SQLException ignored) {
        }
        return users;
    }

    public String getUserPassword(String username) {
        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME_COLUMN + "='" + username + "'";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            ResultSet results = preparedStatement.executeQuery();
            results.next();
            String password = getPassword(results);
            results.close();
            return password;
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean add(String username, String password) {
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?)";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return true;
    }

    public String getUsername(ResultSet resultSet) {
        try {
            return resultSet.getString(USERNAME_COLUMN);
        } catch (SQLException ex) {
            return null;
        }
    }
    public String getPassword(ResultSet resultSet) {
        try {
            return resultSet.getString(PASSWORD_HASH_COLUMN);
        } catch (SQLException ex) {
            return null;
        }
    }
}

