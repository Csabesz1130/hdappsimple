package com.yourcompany.runnerapp.models;

public class Task {
    private String id;
    private String name;
    private String description;
    private String status;

    // Constructors, Getters, and Setters
    public Task() {
        // Empty constructor
    }

    public Task(String id, String name, String description, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}