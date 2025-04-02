package com.example.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariCpConnection {
    private HikariCpConnection() {
    }
     static{
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
     }
    public static Connection getConnection() throws SQLException, IOException {
        String fileLoc = "C:\\Users\\abhil\\IdeaProjects\\ServletJdbcCrudApp\\src\\main\\java\\com\\example\\properties\\db.properties";
        HikariConfig hk = new HikariConfig(fileLoc);
        HikariDataSource source =new HikariDataSource(hk);
        return source.getConnection();
    }
}
