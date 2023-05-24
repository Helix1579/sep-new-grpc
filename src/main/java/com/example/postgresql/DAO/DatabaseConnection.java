package com.example.postgresql.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    public static Connection getConnection()
    {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "admin";
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(url,username,password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
        }
        return connection;
    }
}
