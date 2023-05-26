package com.example.postgresql.DAO;

import com.example.postgresql.model.*;
import com.example.protobuf.DataAccess;

import java.sql.*;
import java.util.ArrayList;

public class ProjectDAO
{
    public void createProject(DataAccess.ProjectCreationDto dto)
    {
        int id = 1;
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement getId = conn.prepareStatement("SELECT id FROM projects ORDER BY id DESC LIMIT 1");
            ResultSet rs = getId.executeQuery();
            while (rs.next())
            {
                id = rs.getInt(1);
                id += 1;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO projects(id,project_name, is_completed,owner) VALUES (?,?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, dto.getTitle());
            stmt.setBoolean(3, false);
            stmt.setString(4, dto.getOwnerUsername());
            System.out.println(stmt);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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

    public ArrayList<String> getAllCollaborators(int id)
    {
        ArrayList<String> list = new ArrayList<>();
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
        return list;
    }
    public void removeCollaborator(DataAccess.AddToProjectDto dto)
    {
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM projects_users_of_project WHERE projects_id = ?");
            stmt.setInt(1, dto.getProjectId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public void addUserStory(DataAccess.UserStoryMessage message)
    {
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tasks (project_id, title) VALUES (?,?)");
            stmt.setInt(1, message.getProjectId());
            stmt.setString(2, message.getTaskBody());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<Projects> getAllProjects(String username)
    {
        ArrayList<Projects> projectsList = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM projects where owner = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Projects project = new Projects(rs.getInt("id"),rs.getString("project_name"));
                projectsList.add(project);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return projectsList;
    }

    public ArrayList<Tasks> getProductBacklog(int id)
    {
        ArrayList<Tasks> tasksList = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("SELECT t.id AS task_id, t.title, p.id AS project_id\n" +
                    "FROM tasks t\n" +
                    "LEFT JOIN tasks_tasks_of_project tp ON t.id = tp.tasks_id\n" +
                    "LEFT JOIN projects p ON p.id = tp.tasks_of_project_id");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Tasks task = new Tasks();

                task.setId(rs.getInt("task_id"));
                task.setTitle(rs.getString("title"));

                Projects project = new Projects();
                project.setId(rs.getInt("project_id"));

                task.getTasksOfProject().add(project);

                tasksList.add(task);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tasksList;
    }
}