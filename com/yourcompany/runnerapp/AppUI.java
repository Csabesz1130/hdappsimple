package com.yourcompany.runnerapp;

import com.yourcompany.runnerapp.models.Task;
import com.yourcompany.runnerapp.services.FirestoreService;

import java.util.List;

public class AppUI {
    private FirestoreService firestoreService;

    public AppUI() {
        firestoreService = new FirestoreService();
    }

    public void displayTasks() {
        List<Task> tasks = firestoreService.getAllTasks();
        if (tasks != null) {
            for (Task task : tasks) {
                System.out.println(task.getName() + ": " + task.getDescription() + " (Status: " + task.getStatus() + ")");
            }
        } else {
            System.out.println("No tasks found.");
        }
    }

    public void addTask(String id, String name, String description, String status) {
        Task task = new Task(id, name, description, status);
        firestoreService.addTasks(List.of(task));
        System.out.println("Task added: " + name);
    }

    // Implement methods to update and delete tasks
    public void updateTask(String id, String name, String description, String status) {
        Task task = new Task(id, name, description, status);
        firestoreService.updateTask(task);
        System.out.println("Task updated: " + name);
    }

    public void deleteTask(String id) {
        firestoreService.deleteTask(id);
        System.out.println("Task deleted: " + id);
    }

    public static void main(String[] args) {
        AppUI appUI = new AppUI();
        appUI.addTask("1", "Sample Task", "This is a sample task", "Pending");
        appUI.displayTasks();
    }
}
