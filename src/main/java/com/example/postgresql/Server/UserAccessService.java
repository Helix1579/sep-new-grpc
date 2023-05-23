package com.example.postgresql.Server;
import com.example.postgresql.DAO.UserDAO;
import com.example.protobuf.DataAccess;
import com.example.protobuf.UserAccessGrpc;
import io.grpc.stub.StreamObserver;

public class UserAccessService extends UserAccessGrpc.UserAccessImplBase
{
    private UserDAO userDAO = new UserDAO();
    @Override
    public void createUser(DataAccess.UserCreationDto request, StreamObserver<DataAccess.Response> responseObserver)
    {
        userDAO.createUser(request.getUsername(), request.getPassword());

        System.out.println("Received request ==> " + request);
        DataAccess.Response response = DataAccess.Response.newBuilder()
                .setCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void userByUsername(DataAccess.Username request, StreamObserver<DataAccess.UserCreationDto> responseObserver) {
        super.userByUsername(request, responseObserver);
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
    }

    @Override
    public void lookForUsers(DataAccess.Username request, StreamObserver<DataAccess.FilteredUsersResponse> responseObserver) {
        super.lookForUsers(request, responseObserver);
    }
}