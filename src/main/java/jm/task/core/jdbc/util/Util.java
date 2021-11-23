package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String DBUrl = "jdbc:mysql://localhost:3306/test?useSSL=false";
    private static String DBUser = "root";
    private static String DBPass = "pass";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DBUrl, DBUser, DBPass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
}
