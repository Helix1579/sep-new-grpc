package com.example.postgresql.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tasks
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private boolean isCompleted;
    @OneToMany
    private List<Projects> tasksOfProject;

    public Tasks() {
    }

    public Tasks(int id, String title, boolean isCompleted, List<Projects> tasksOfProject) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
        this.tasksOfProject = tasksOfProject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public List<Projects> getTasksOfProject() {
    return tasksOfProject;
}

    public void setTasksOfProject(List<Projects> tasksOfProject) {
        this.tasksOfProject = tasksOfProject;
    }
}
