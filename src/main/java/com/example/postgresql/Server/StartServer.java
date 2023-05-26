package com.example.postgresql.Server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class StartServer
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Server server = ServerBuilder.forPort(9090)
                .addService(new UserAccessService())
//                .addService(new ProjectAccessService())
                .build();

        System.out.println("Server Started");
        server.awaitTermination();
    }
}
