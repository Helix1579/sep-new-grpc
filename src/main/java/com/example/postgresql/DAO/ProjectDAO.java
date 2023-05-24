package com.example.postgresql.DAO;

import com.example.postgresql.model.Users;
import com.example.protobuf.DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO
{
    public void createProject(DataAccess.ProjectCreationDto dto)
    {
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Projects(project_name, is_completed,owner) VALUES (?,?,?)");
            stmt.setString(1, dto.getTitle());
            stmt.setBoolean(2, false);
            stmt.setString(3, dto.getOwnerUsername());
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

    public void addCollaborators(DataAccess.AddToProjectDto dto)
    {
        try (Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO projects(id, owner) VALUES (?,?)");
            stmt.setInt(1, dto.getProjectId());
            stmt.setString(2, dto.getUsername());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void getAllCollaborators(int id)
    {
        List<String> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection())
        {

            PreparedStatement searchUsers = conn.prepareStatement("SELECT * FROM projects_users_of_project WHERE projects_id = ?");
            searchUsers.setInt(1,id);
            ResultSet rs = searchUsers.executeQuery();
            while (rs.next())
            {
                PreparedStatement stmt = conn.prepareStatement("SELECT username FROM users WHERE id = ?");
                stmt.setInt(1,rs.getInt("id"));
                ResultSet set = stmt.executeQuery();
                list.add(set.getString("username"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
