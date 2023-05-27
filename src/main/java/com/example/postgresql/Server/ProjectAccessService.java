package com.example.postgresql.Server;

import com.example.postgresql.DAO.ProjectDAO;
import com.example.postgresql.DAO.TaskDAO;
import com.example.postgresql.model.Projects;
import com.example.postgresql.model.Tasks;
import com.protobuf.*;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;

public class ProjectAccessService extends ProjectAccessGrpc.ProjectAccessImplBase
{
    private ProjectDAO projectDAO = new ProjectDAO();
    private TaskDAO taskDAO =new TaskDAO();

    @Override
    public void createProject(DataAccess.ProjectCreationDto request, StreamObserver<DataAccess.ResponseWithID> responseObserver)
    {
        projectDAO.createProject(request);

        System.out.println("Received request ==> " + request);
        DataAccess.ResponseWithID response = DataAccess.ResponseWithID.newBuilder()
                .setCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllCollaborators(DataAccess.Id request, StreamObserver<DataAccess.FilteredUsersResponse> responseObserver) {
        DataAccess.FilteredUsersResponse.Builder builder = DataAccess.FilteredUsersResponse.newBuilder();
/*
        ArrayList<Projects> projectList = projectDAO.getAllCollaborators(request.getId());

        for (Projects projects : projectList)
        {
            builder.addUsers(DataAccess.UserSearchDto.newBuilder()
                    .setUsername(users.getUsername())
                    .build());
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();

 */
    }

    @Override
    public void addCollaborator(DataAccess.AddToProjectDto request, StreamObserver<DataAccess.Response> responseObserver) {
        projectDAO.addCollaborators(request);

        System.out.println("Received request ==> " + request);

        DataAccess.Response response = DataAccess.Response.newBuilder()
                .setCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void removeCollaborator(DataAccess.AddToProjectDto request, StreamObserver<DataAccess.ResponseWithID> responseObserver) {
        projectDAO.removeCollaborator(request);

        System.out.println("Received request ==> " + request);

        DataAccess.ResponseWithID response = DataAccess.ResponseWithID.newBuilder()
                .setCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addUserStory(DataAccess.UserStoryMessage request, StreamObserver<DataAccess.ResponseWithID> responseObserver) {
        projectDAO.addUserStory(request);

        System.out.println("Received request ==> " + request);

        DataAccess.ResponseWithID response = DataAccess.ResponseWithID.newBuilder()
                .setCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllProjects(DataAccess.Username request, StreamObserver<DataAccess.ProjectsResponse> responseObserver) {
        DataAccess.ProjectsResponse.Builder builder = DataAccess.ProjectsResponse.newBuilder();

        ArrayList<Projects> projectList = projectDAO.getAllProjects(request.getUsername());

        for (Projects projects : projectList)
        {
            builder.addProjects(DataAccess.ProjectMessage.newBuilder()
                            .setId(projects.getId())
                            .setTitle(projects.getProjectName())
                    .build());
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getProductBacklog(DataAccess.Id request, StreamObserver<DataAccess.ProductBacklogResponse> responseObserver)
    {
        DataAccess.ProductBacklogResponse.Builder builder = DataAccess.ProductBacklogResponse.newBuilder();

        ArrayList<Tasks> taskList = projectDAO.getProductBacklog(request.getId());


        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
    @Override
    public void updateUserStoryStatus(DataAccess.StatusUpdate request, StreamObserver<DataAccess.Response> responseObserver)
    {
        taskDAO.updateTask(request.getId(), request.getStatus());

        System.out.println("Received request ==> " + request);

        DataAccess.Response response = DataAccess.Response.newBuilder()
                .setCode(200)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUserStory(DataAccess.Id request, StreamObserver<DataAccess.Response> responseObserver)
    {
        taskDAO.deleteTask(request.getId());

        System.out.println("Request Received ==> " + request);

        DataAccess.Response response =DataAccess.Response.newBuilder()
                .setCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
