package com.example.postgresql.ClientTEst;

import com.example.protobuf.DataAccess;
import com.example.protobuf.UserAccessGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;

public class Test
{
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost",7156)
                .usePlaintext().build();

        UserAccessGrpc.UserAccessBlockingStub blockingStub=UserAccessGrpc.newBlockingStub(managedChannel);

        DataAccess.UserCreationDto request=DataAccess.UserCreationDto.newBuilder()
                .setUsername("dsfg")
                .setPassword("545")
                .build();

        System.out.println(request);
        DataAccess.Response response= blockingStub.createUser(request);
        System.out.println("Received and created ==> " + response);

    }
}
