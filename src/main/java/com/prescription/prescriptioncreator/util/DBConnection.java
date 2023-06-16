package com.prescription.prescriptioncreator.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn = null;

    public static Connection getConnection() throws Exception {
        if (conn == null) {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "prescription?useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "root";

            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        }

        return conn;
    }
}
