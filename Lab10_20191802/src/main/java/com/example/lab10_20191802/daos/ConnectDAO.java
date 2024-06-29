package com.example.lab10_20191802.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectDAO {
    public Connection getConection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Falla conexion");
        }

        String username = "root";
        String password = "root";
        String params = "serverTimezone=America/Lima&useSSL=false&allowPublicKeyRetrieval=true";
        String url = "jdbc:mysql://localhost:3306/lab10";

        return DriverManager.getConnection(url, username, password);
    }
}
