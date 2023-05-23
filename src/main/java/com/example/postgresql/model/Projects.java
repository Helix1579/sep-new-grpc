package com.example.postgresql.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Projects
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ProjectName;
    private String  owner;
    private boolean isCompleted;
    @OneToMany
    private List<Users> usersOfProject;

    public Projects() {
    }

    public Projects(int id, String projectName, String owner, boolean isCompleted, List<Users> usersOfProject) {
        this.id = id;
        ProjectName = projectName;
        this.owner = owner;
        this.isCompleted = isCompleted;
        this.usersOfProject = usersOfProject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public List<Users> getUsersOfProject() {
        return usersOfProject;
    }

    public void setUsersOfProject(List<Users> usersOfProject) {
        this.usersOfProject = usersOfProject;
    }

}
