package com.example.postgresql.DAO;
import com.example.postgresql.model.Users;
import com.example.protobuf.DataAccess;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO
{
    public UserDAO()
    {





//        try(Connection conn = DatabaseConnection.getConnection())
//        {
//            PreparedStatement stmt = conn.prepareStatement("SELECT users.id, projects.id FROM users,projects");
//            ResultSet loadData = stmt.executeQuery();
//
//            while (loadData.next())
//            {
//                PreparedStatement insertData = conn.prepareStatement("INSERT INTO projects_users_of_project(projects_id, users_of_project_id) VALUES (?,?)");
//                insertData.setInt(1,loadData.getInt(1));
//                insertData.setInt(2,loadData.getInt(2));
//                System.out.println(insertData);
//            }
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
    }

    public void createUser(DataAccess.UserCreationDto dto)
    {
        int id = 1;
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement getId = conn.prepareStatement("SELECT id FROM users ORDER BY id DESC LIMIT 1");
            ResultSet rs = getId.executeQuery();
            while (rs.next())
            {
                id = rs.getInt(1);
                id += 1;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(id,username, password) " +
                    "VALUES (?,?,?)");
            stmt.setInt(1,id);
            stmt.setString(2,dto.getUsername());
            stmt.setString(3, dto.getPassword());
            System.out.println(stmt);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Users getByUsername(String username)
    {
        Users userFound = null;
        try(Connection conn =  DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users where username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                userFound = new Users(rs.getString("username"), rs.getString("password"));
            }
            System.out.println(stmt);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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
                    Users userFound = new Users(rs.getInt("id"),rs.getString("username"),rs.getString("password"));
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
