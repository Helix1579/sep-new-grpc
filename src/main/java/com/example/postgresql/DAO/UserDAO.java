package com.example.postgresql.DAO;
import com.example.postgresql.model.Users;
import com.example.protobuf.DataAccess;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO
{
    public void createUser(DataAccess.UserCreationDto dto)
    {
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(username, password) VALUES (?,?)");
            stmt.setString(1,dto.getUsername());
            stmt.setString(2, dto.getPassword());
            System.out.println(stmt);
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
        try(Connection conn =  DatabaseConnection.getConnection())
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
        try (Connection conn =  DatabaseConnection.getConnection()) {
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

    public void deleteUser(String username)
    {

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE username = ?");
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public ArrayList<Users> lookForUser(String username)
    {
        ArrayList<Users> user = new ArrayList<>();
        try (Connection conn =  DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                if (rs.getString("username").contains(username))
                {
                    Users userFound = new Users(rs.getInt("id"),rs.getString("username.1"),rs.getString("password"));
                    user.add(userFound);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
