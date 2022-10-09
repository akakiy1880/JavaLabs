package util;

import utils.IOUtils;

import java.sql.*;

public class Database {
    private Connection connection;

    public void run() {
        try {
            connection = DBConnectionManager.open();
            if (connection != null)
                connection.getTransactionIsolation();
        } catch (SQLException e) {
            IOUtils.printErr("Не удалось подключиться к базе данных!");
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
