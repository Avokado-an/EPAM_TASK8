package com.anton.day8.model.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionCreator {
    private ConnectionCreator() {

    }

    public static Connection provideConnection() throws SQLException {
        Connection connection;
        ResourceBundle rb = ResourceBundle.getBundle("property.database");
        String url = rb.getString("url");
        String username = rb.getString("username");
        String password = rb.getString("password");
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
