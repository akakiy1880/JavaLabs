package util;

import utils.IOUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnectionManager {

    private static final String URL = PropertiesUtil.get("db.url");
    private static final String USERNAME = PropertiesUtil.get("db.username");
    private static final String PASSWORD = PropertiesUtil.get("db.password");

    private DBConnectionManager() {
    }

    public static Connection open() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            IOUtils.printErr("Не удалось подключиться!");
            return null;
        }
    }
}
