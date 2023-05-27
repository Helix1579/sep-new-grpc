package com.example.postgresql.DAO;

import com.example.protobuf.DataAccess;

public class DAOTest
{
    public static void main(String[] args)
    {
        UserDAO userDAO = new UserDAO();
//        DataAccess.UserCreationDto userCreationDto = DataAccess.UserCreationDto.newBuilder()
//                .setUsername("Yash")
//                .setPassword("12345")
//                .build();
//        userDAO.createUser(userCreationDto);

        DataAccess.Username username = DataAccess.Username.newBuilder()
                .setUsername("Yash")
                .build();
        userDAO.getByUsername("Yash");

//        ProjectDAO projectDAO = new ProjectDAO();
//        DataAccess.ProjectCreationDto projectCreationDto = DataAccess.ProjectCreationDto.newBuilder()
//                .setTitle("skygh")
//                .setOwnerUsername("cxgh")
//                .build();
//        projectDAO.createProject(projectCreationDto);

    }
}
