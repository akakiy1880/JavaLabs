package bd;

import dragon.*;
import util.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

public class BDCollection {
    private Database database;
    private static final String TABLE_NAME = "collection",
            ID_COLUMN = "id",
            USER_NAME_COLUMN = "user_name",
            DRAGON_NAME_COLUMN = "dragon_name",
            COORDINATE_X_COLUMN = "coordinate_X",
            COORDINATE_Y_COLUMN = "coordinates_Y",
            CREATION_DATE_COLUMN = "creation_date",
            AGE_COLUMN = "age",
            WEIGHT_COLUMN = "weight",
            SPEAKABLE_COLUMN = "speakable",
            CHARACTER_COLUMN = "character",
            KILLER_NAME_COLUMN = "killer_name",
            PASSPORT_COLUMN = "passport",
            HAIR_COLOR_COLUMN = "hair_color",
            COUNTRY_COLUMN = "country",
            COORDINATE_X_KILLER_COLUMN = "killer_coordinate_x",
            COORDINATE_Y_KILLER_COLUMN = "killer_coordinate_y",
            PLACE_NAME_COLUMN = "name";

    public BDCollection(Database database) {
        this.database = database;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                ID_COLUMN + " SERIAL PRIMARY KEY UNIQUE, " +
                USER_NAME_COLUMN + " varchar(16) NOT NULL, " +
                DRAGON_NAME_COLUMN + " varchar(16) NOT NULL, " +
                COORDINATE_X_COLUMN + " numeric NOT NULL, " +
                COORDINATE_Y_COLUMN + " integer NOT NULL, " +
                CREATION_DATE_COLUMN + " varchar(32) NOT NULL, " +
                AGE_COLUMN + " integer NOT NULL, " +
                WEIGHT_COLUMN + " integer NOT NULL, " +
                SPEAKABLE_COLUMN + " varchar(16) NOT NULL, " +
                CHARACTER_COLUMN + " varchar(16), " +
                KILLER_NAME_COLUMN + " varchar(16) NOT NULL, " +
                PASSPORT_COLUMN + " varchar(16) NOT NULL, " +
                HAIR_COLOR_COLUMN + " varchar(16), " +
                COUNTRY_COLUMN + " varchar(16), " +
                COORDINATE_X_KILLER_COLUMN + " numeric NOT NULL, " +
                COORDINATE_Y_KILLER_COLUMN + " integer NOT NULL, " +
                PLACE_NAME_COLUMN + " varchar(16) NOT NULL" + ")";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }

    public void add(String username, Dragon dragon) {
        String query = "INSERT INTO " + TABLE_NAME + "(user_name, dragon_name, coordinate_X, coordinates_Y, creation_date, age, weight" +
                ", speakable, character, killer_name, passport, hair_color, country, killer_coordinate_x" +
                ", killer_coordinate_y, name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, dragon.getName());
            preparedStatement.setFloat(3, dragon.getCoordinates().getX());
            preparedStatement.setFloat(4, dragon.getCoordinates().getY());
            preparedStatement.setDate(5, dragon.getCreationDate());
            preparedStatement.setLong(6, dragon.getAge());
            preparedStatement.setLong(7, dragon.getWeight());
            preparedStatement.setBoolean(8, dragon.getSpeaking());
            preparedStatement.setString(9, dragon.getCharacter() == null ? "" : dragon.getCharacter().name());
            preparedStatement.setString(10, dragon.getKiller().getName());
            preparedStatement.setString(11, dragon.getKiller().getPassportID());
            preparedStatement.setString(12, dragon.getKiller().getHairColor() == null ? "" : dragon.getKiller().getHairColor().name());
            preparedStatement.setString(13, dragon.getKiller().getLocation().getName());
            preparedStatement.setDouble(14, dragon.getKiller().getLocation().getX());
            preparedStatement.setInt(15, dragon.getKiller().getLocation().getY());
            preparedStatement.setString(16, dragon.getKiller().getNationality() == null ? "" : dragon.getKiller().getNationality().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void del(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        String query = "DELETE FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Dragon construct(ResultSet resultSet) {
        try  {
            Coordinates coordinates = new Coordinates(resultSet.getFloat(COORDINATE_X_COLUMN), resultSet.getInt(COORDINATE_Y_COLUMN));
            Date creationDate = resultSet.getDate(CREATION_DATE_COLUMN);
            DragonCharacter dragonCharacter = DragonCharacter.valueOf(resultSet.getString(CHARACTER_COLUMN));
            Location killerLocation = new Location(
                    resultSet.getDouble(COORDINATE_X_KILLER_COLUMN),
                    resultSet.getInt(COORDINATE_Y_KILLER_COLUMN),
                    resultSet.getString(PLACE_NAME_COLUMN)
            );
            Person killer = new Person(
                    resultSet.getString(KILLER_NAME_COLUMN),
                    resultSet.getString(PASSPORT_COLUMN),
                    Color.valueOf(resultSet.getString(HAIR_COLOR_COLUMN)),
                    Country.valueOf(resultSet.getString(PLACE_NAME_COLUMN)),
                    killerLocation
            );
            Dragon dragon = new Dragon(
                    resultSet.getInt(ID_COLUMN),
                    resultSet.getString(DRAGON_NAME_COLUMN),
                    coordinates,
                    creationDate,
                    resultSet.getLong(DRAGON_NAME_COLUMN),
                    resultSet.getLong(WEIGHT_COLUMN),
                    resultSet.getBoolean(SPEAKABLE_COLUMN),
                    dragonCharacter,
                    killer
            );
            return dragon;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Stack<Dragon> getDragonBD() {
        Stack<Dragon> dragons = new Stack<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(query)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                dragons.push(construct(results));
            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dragons;
    }

}
