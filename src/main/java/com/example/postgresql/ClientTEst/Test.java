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
                .forAddress("localhost",9090)
                .usePlaintext().build();

        UserAccessGrpc.UserAccessBlockingStub stub = UserAccessGrpc.newBlockingStub(managedChannel);

        DataAccess.UserCreationDto dto = DataAccess.UserCreationDto.newBuilder()
                .setUsername("Yash")
                .setPassword("12345")
                .build();
        System.out.println(dto);

        DataAccess.Response response = stub.createUser(dto);
        System.out.println(response);

    }
}
