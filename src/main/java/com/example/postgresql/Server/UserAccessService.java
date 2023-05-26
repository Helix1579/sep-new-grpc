package com.example.postgresql.Server;
import com.example.postgresql.DAO.DatabaseConnection;
import com.example.postgresql.DAO.UserDAO;
import com.example.postgresql.model.Users;
import com.example.protobuf.DataAccess;
import com.example.protobuf.UserAccessGrpc;
import io.grpc.stub.StreamObserver;

import java.sql.*;
import java.util.ArrayList;

public class UserAccessService extends UserAccessGrpc.UserAccessImplBase
{
    private UserDAO userDAO = new UserDAO();

    @Override
    public void createUser(DataAccess.UserCreationDto request, StreamObserver<DataAccess.Response> responseObserver)
    {
        userDAO.createUser(request);

        System.out.println("Received request ==> " + request);
        DataAccess.Response response = DataAccess.Response.newBuilder()
                .setCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void userByUsername(DataAccess.Username request, StreamObserver<DataAccess.UserCreationDto> responseObserver) {
        Users user =  userDAO.getByUsername(request.getUsername());

        System.out.println("Received request ==> " + request);
        DataAccess.UserCreationDto response = DataAccess.UserCreationDto.newBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(DataAccess.UserCreationDto request, StreamObserver<DataAccess.UpdatedUserResponse> responseObserver) {
        super.updateUser(request, responseObserver);
    }

    @Override
    public void deleteUser(DataAccess.Username request, StreamObserver<DataAccess.Response> responseObserver) {
        super.deleteUser(request, responseObserver);
    }

    @Override
    public void getPassword(DataAccess.Username request, StreamObserver<DataAccess.Password> responseObserver) {
        super.getPassword(request, responseObserver);
        //get by username
    }

    @Override
    public void lookForUsers(DataAccess.Username request, StreamObserver<DataAccess.FilteredUsersResponse> responseObserver)
    {
        DataAccess.FilteredUsersResponse.Builder builder = DataAccess.FilteredUsersResponse.newBuilder();

        ArrayList<Users> userList = userDAO.lookForUser(request.getUsername());

        for (Users users : userList)
        {
            builder.addUsers(DataAccess.UserSearchDto.newBuilder()
                            .setUsername(users.getUsername())
                    .build());
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
