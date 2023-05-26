package com.example.postgresql.DAO;

import com.example.protobuf.DataAccess;

public class DAOTest
{
    public static void main(String[] args)
    {
        UserDAO userDAO = new UserDAO();
        DataAccess.UserCreationDto dto = DataAccess.UserCreationDto.newBuilder()
                .setUsername("cxgh")
                .setPassword("w456")
                .build();
        userDAO.createUser(dto);

        ProjectDAO projectDAO = new ProjectDAO();
        DataAccess.ProjectCreationDto projectCreationDto = DataAccess.ProjectCreationDto.newBuilder()
                .setTitle("skygh")
                .setOwnerUsername("cxgh")
                .build();
        projectDAO.createProject(projectCreationDto);

    }
}
