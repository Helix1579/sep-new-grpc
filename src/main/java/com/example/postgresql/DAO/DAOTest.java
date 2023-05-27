package com.example.postgresql.DAO;

import com.protobuf.*;

public class DAOTest
{
    public static void main(String[] args)
    {
//        UserDAO userDAO = new UserDAO();
//        DataAccess.UserCreationDto userCreationDto = DataAccess.UserCreationDto.newBuilder()
//                .setUsername("Yash")
//                .setPassword("12345")
//                .build();
//        userDAO.createUser(userCreationDto);

//        DataAccess.Username username = DataAccess.Username.newBuilder()
//                .setUsername("Yash")
//                .build();
//        userDAO.getByUsername(username.getUsername());

//        userDAO.lookForUser(username.getUsername());

        ProjectDAO projectDAO = new ProjectDAO();
        DataAccess.ProjectCreationDto projectCreationDto = DataAccess.ProjectCreationDto.newBuilder()
                .setTitle("skyHigh")
                .setOwnerUsername("cxgh")
                .build();
        projectDAO.createProject(projectCreationDto);

//        DataAccess.AddToProjectDto addToProjectDto = DataAccess.AddToProjectDto.newBuilder()
//                .setProjectId(1)
//                .setUsername("Yash")
//                .build();
//        projectDAO.addCollaborators(addToProjectDto);
//        projectDAO.removeCollaborator(addToProjectDto);

//        DataAccess.UserStoryMessage userStoryMessage = DataAccess.UserStoryMessage.newBuilder()
//                .setProjectId(1)
//                .setTaskBody("new Task")
//                .build();
//        projectDAO.addUserStory(userStoryMessage);

    }
}
