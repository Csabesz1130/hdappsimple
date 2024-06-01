package com.yourcompany.runnerapp;

import com.yourcompany.runnerapp.models.Task;
import com.yourcompany.runnerapp.services.FirestoreService;

import java.util.List;
import java.util.Scanner;

public class AppUI {
    private FirestoreService firestoreService;
    private Scanner scanner;

    public AppUI() {
        firestoreService = new FirestoreService();
        scanner = new Scanner(System.in);
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

    public void addTask() {
        System.out.print("Enter task ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task status: ");
        String status = scanner.nextLine();

        Task task = new Task(id, name, description, status);
        firestoreService.addTasks(List.of(task));
        System.out.println("Task added: " + name);
    }

    public void updateTask() {
        System.out.print("Enter task ID to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter new task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new task status: ");
        String status = scanner.nextLine();

        Task task = new Task(id, name, description, status);
        firestoreService.updateTask(task);
        System.out.println("Task updated: " + name);
    }

    public void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        String id = scanner.nextLine();
        firestoreService.deleteTask(id);
        System.out.println("Task deleted: " + id);
    }

    public void run() {
        while (true) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Display all tasks");
            System.out.println("2. Add a new task");
            System.out.println("3. Update an existing task");
            System.out.println("4. Delete a task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        AppUI appUI = new AppUI();
        appUI.run();
    }
}

