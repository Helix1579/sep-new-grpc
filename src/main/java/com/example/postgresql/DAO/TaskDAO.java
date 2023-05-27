package com.example.postgresql.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDAO
{
    public void updateTask(int id, boolean status)
    {
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("UPDATE tasks SET is_completed = ? WHERE id = ?");
            stmt.setBoolean(1,status);
            stmt.setInt(2,id);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void deleteTask(int id)
    {
        try(Connection conn = DatabaseConnection.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tasks WHERE id = ?");
            stmt.setInt(1,id);
            stmt.executeUpdate();

            PreparedStatement updateTable = conn.prepareStatement("DELETE FROM tasks_tasks_of_project WHERE tasks_id = ?");
            updateTable.setInt(1, id);
            updateTable.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
