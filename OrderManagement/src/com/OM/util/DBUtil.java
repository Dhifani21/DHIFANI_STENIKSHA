package com.OM.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    public static Connection getDBConn() {
        Connection conn = null;

        try {
            Properties prop = DBPropertyUtil.getDBProperties("com/OM/util/mysql.properties");

            String url = prop.getProperty("url");
            String user = prop.getProperty("username");
            String pass = prop.getProperty("password");

            conn = DriverManager.getConnection(url, user, pass);

            if (conn != null) {
                System.out.println("Connection established");
            } else {
                System.out.println("Failed to establish connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        DBUtil.getDBConn();
    }
}