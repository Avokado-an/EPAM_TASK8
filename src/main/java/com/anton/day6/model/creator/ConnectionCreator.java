package com.anton.day6.model.creator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionCreator {
    public static Connection provideConnection() throws SQLException {
        Connection connection;
        ResourceBundle rb = ResourceBundle.getBundle("property.database");
        String url = rb.getString("url");
        String username = rb.getString("username");
        String password = rb.getString("password");
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    /*public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can not close connection");
        }
    }*/
}
