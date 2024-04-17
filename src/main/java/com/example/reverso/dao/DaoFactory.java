package com.example.reverso.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    public static Connection getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/reverso", "root", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
