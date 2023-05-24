package com.example.postgresql.Server;

import com.example.protobuf.DataAccess;
import com.example.protobuf.ProjectAccessGrpc;
import io.grpc.stub.StreamObserver;

public class ProjectAccessService extends ProjectAccessGrpc.ProjectAccessImplBase
{
    @Override
    public void createProject(DataAccess.ProjectCreationDto request, StreamObserver<DataAccess.ResponseWithID> responseObserver) {
        super.createProject(request, responseObserver);
    }

    @Override
    public void getAllCollaborators(DataAccess.Id request, StreamObserver<DataAccess.FilteredUsersResponse> responseObserver) {
        super.getAllCollaborators(request, responseObserver);
    }

    @Override
    public void addCollaborator(DataAccess.AddToProjectDto request, StreamObserver<DataAccess.Response> responseObserver) {
        super.addCollaborator(request, responseObserver);
    }

    @Override
    public void removeCollaborator(DataAccess.AddToProjectDto request, StreamObserver<DataAccess.ResponseWithID> responseObserver) {
        super.removeCollaborator(request, responseObserver);
    }

    @Override
    public void addUserStory(DataAccess.UserStoryMessage request, StreamObserver<DataAccess.ResponseWithID> responseObserver) {
        super.addUserStory(request, responseObserver);
    }

    @Override
    public void getAllProjects(DataAccess.Username request, StreamObserver<DataAccess.ProjectsResponse> responseObserver) {
        super.getAllProjects(request, responseObserver);
    }

    @Override
    public void getProductBacklog(DataAccess.Id request, StreamObserver<DataAccess.ProductBacklogResponse> responseObserver) {
        super.getProductBacklog(request, responseObserver);
    }
}
