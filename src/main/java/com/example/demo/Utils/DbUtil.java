package com.example.demo.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection createConnectionViaUserPwd() throws Exception {
        String username = "postgres";
        String password = "postgres";
        String jdbc_url = "jdbc:postgresql://localhost:5432/withdrawalProcess";

        try {
            Connection connection = DriverManager.getConnection(jdbc_url, username, password);
            System.out.println("Connection Established to: " + jdbc_url);
            return connection;
        } catch (SQLException e) {
            System.out.println("Failed to connect to PostgreSQL database!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
