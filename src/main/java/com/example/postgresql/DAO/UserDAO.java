package com.example.postgresql.DAO;
import com.example.postgresql.model.Users;

import java.sql.*;

public class UserDAO
{
    private static Connection getConnection()
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
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void createUser(String username, String password)
    {
        try(Connection conn = UserDAO.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(username, password) VALUES (?,?)");
            stmt.setString(1,username);
            stmt.setString(2, password);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Users getByUsername(String username)
    {
        Users userFound = null;
        try(Connection conn = UserDAO.getConnection())
        {
            String sql = "SELECT * FROM users where username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery(sql);

            userFound = new Users(rs.getString("username"),rs.getString("password"));

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return userFound;
    }

    public void updateUser(int id, String username, String password) {
        try (Connection conn = UserDAO.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET username = ?, password = ? WHERE id = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int id)
    {

    }
}
