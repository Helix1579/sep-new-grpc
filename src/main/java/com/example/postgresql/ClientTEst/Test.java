package com.example.postgresql.ClientTEst;

import com.example.protobuf.DataAccess;
import com.example.protobuf.ProjectAccessGrpc;
import com.example.protobuf.UserAccessGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Test
{
    public static void main(String[] args)
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
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

        ProjectAccessGrpc.ProjectAccessBlockingStub projectAccessBlockingStub = ProjectAccessGrpc.newBlockingStub(channel);

        DataAccess.ProjectCreationDto projectCreationDto = DataAccess.ProjectCreationDto.newBuilder()
                .setOwnerUsername("Yash")
                .setTitle("fdkhb")
                .build();

        System.out.println(projectCreationDto);
        DataAccess.ResponseWithID stubProject = projectAccessBlockingStub.createProject(projectCreationDto);
        System.out.println(stubProject);
    }
}
