package com.example.postgresql.ClientTEst;

import com.protobuf.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.xml.crypto.Data;

public class Test
{
    public static void main(String[] args)
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext().build();

//        UserAccessGrpc.UserAccessBlockingStub userAccessBlockingStub = UserAccessGrpc.newBlockingStub(channel);
//
//        DataAccess.UserCreationDto userCreationDto = DataAccess.UserCreationDto.newBuilder()
//                .setUsername("Yash")
//                .setPassword("12345")
//                .build();
//        System.out.println(userCreationDto);
//        DataAccess.Response stubUser = userAccessBlockingStub.createUser(userCreationDto);
//        System.out.println(stubUser);

//        DataAccess.Username username = DataAccess.Username.newBuilder()
//                .setUsername("Yash")
//                .build();
//
//        System.out.println(username);
//        DataAccess.FilteredUsersResponse stubUser = userAccessBlockingStub.lookForUsers(username);
//        System.out.println(stubUser);

        ProjectAccessGrpc.ProjectAccessBlockingStub projectAccessBlockingStub = ProjectAccessGrpc.newBlockingStub(channel);

//        DataAccess.ProjectCreationDto projectCreationDto = DataAccess.ProjectCreationDto.newBuilder()
//                .setOwnerUsername("Yash")
//                .setTitle("fdkhb")
//                .build();
//
//        System.out.println(stubProject);

//        DataAccess.AddToProjectDto addToProjectDto = DataAccess.AddToProjectDto.newBuilder()
//                .setProjectId(1)
//                .setUsername("Yash")
//                .build();
//        System.out.println(addToProjectDto);
//

//        DataAccess.UserStoryMessage message = DataAccess.UserStoryMessage.newBuilder()
//                .setProjectId(1)
//                .setTaskBody("new Task")
//                .build();
//        System.out.println(message);

        DataAccess.Id delete = DataAccess.Id.newBuilder()
                .setId(1)
                .build();

        System.out.println(delete);
        DataAccess.Response response = projectAccessBlockingStub.deleteUserStory(delete);
        System.out.println(response);

        /*
        delete user story
         */
    }
}
