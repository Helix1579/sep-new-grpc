package com.example.postgresql.DAO;

import com.example.postgresql.model.*;
import com.protobuf.DataAccess;

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

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO projects(id, is_completed, project_name, owner) VALUES (?,?,?,?)");
            stmt.setInt(1, id);
            stmt.setBoolean(2, false);
            stmt.setString(3, dto.getTitle());
            stmt.setString(4, dto.getOwnerUsername());
            System.out.println(stmt);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addCollaborators(DataAccess.AddToProjectDto dto)
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement getId = conn.prepareStatement("SELECT id FROM users where username = ?");
            getId.setString(1, dto.getUsername());
            ResultSet rs = getId.executeQuery();
            while (rs.next())
            {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO projects_users_of_project(projects_id, users_of_project_id) VALUES (?,?)");
                stmt.setInt(1, dto.getProjectId());
                stmt.setInt(2,rs.getInt(1));
                System.out.println(stmt);
                stmt.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }

    public ArrayList<Users> getAllCollaborators(int id)
    {
        ArrayList<Users> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement searchUsers = conn.prepareStatement("SELECT * FROM projects_users_of_project WHERE projects_id = ?");
            searchUsers.setInt(1,id);
            ResultSet rs = searchUsers.executeQuery();

            while (rs.next())
            {
                PreparedStatement stmt = conn.prepareStatement("SELECT username FROM users WHERE id = ?");
                stmt.setInt(1,rs.getInt("id"));
                ResultSet resultSet = stmt.executeQuery();
                Users userFound = new Users(resultSet.getString("username"));
                list.add(userFound);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        return list;
    }
    public void removeCollaborator(DataAccess.AddToProjectDto dto)
    {
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement getUserId = conn.prepareStatement("SELECT id FROM users WHERE username = ?");
            getUserId.setString(1, dto.getUsername());
            ResultSet rs = getUserId.executeQuery();

            while (rs.next())
            {
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM projects_users_of_project WHERE users_of_project_id = ?");
                stmt.setInt(1, rs.getInt("id"));
                stmt.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }
    public void addUserStory(DataAccess.UserStoryMessage message)
    {
        int id = 1;
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement getId = conn.prepareStatement("SELECT id FROM tasks ORDER BY id DESC LIMIT 1");
            ResultSet rs = getId.executeQuery();
            while (rs.next())
            {
                id = rs.getInt(1);
                id += 1;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tasks (id, is_completed, project_id, title) VALUES (?,?,?,?)");
            stmt.setInt(1, id);
            stmt.setBoolean(2, false);
            stmt.setInt(3, message.getProjectId());
            stmt.setString(4, message.getTaskBody());
            System.out.println(stmt);
            stmt.executeUpdate();

            PreparedStatement updateTable = conn.prepareStatement("INSERT INTO tasks_tasks_of_project(tasks_id, tasks_of_project_id) VALUES (?,?)");
            updateTable.setInt(1, id);
            updateTable.setInt(2, message.getProjectId());
            updateTable.executeUpdate();
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
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tasks WHERE project_id = ? AND is_completed = false");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

//            PreparedStatement stmt = conn.prepareStatement("SELECT t.id AS task_id, t.title, p.id AS project_id\n" +
//                    "FROM tasks t\n" +
//                    "LEFT JOIN tasks_tasks_of_project tp ON t.id = tp.tasks_id\n" +
//                    "LEFT JOIN projects p ON p.id = tp.tasks_of_project_id");
//            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Tasks task = new Tasks();

                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setCompleted(rs.getBoolean("is_completed"));

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