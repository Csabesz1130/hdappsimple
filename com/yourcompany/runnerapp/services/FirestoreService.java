package com.yourcompany.runnerapp.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.yourcompany.runnerapp.models.Task;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class FirestoreService {
    private Firestore db;

    public FirestoreService() {
        // Initialize Firestore connection (as shown in previous responses)
        // ... (your Firestore initialization code)
    }

    public List<Task> getAllTasks() {
        ApiFuture<QuerySnapshot> future = db.collection("tasks").get(); // Assuming "tasks" collection
        try {
            QuerySnapshot querySnapshot = future.get();
            return querySnapshot.getDocuments().stream()
                    .map(document -> document.toObject(Task.class))
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions appropriately (e.g., log or throw custom exception)
            return null;
        }
    }
    
    public void addTasks(List<Task> tasks) {
        WriteBatch batch = db.batch();
        for (Task task : tasks) {
            DocumentReference docRef = db.collection("tasks").document(task.getId());
            batch.set(docRef, task);
        }
        
        try {
            batch.commit().get();
            System.out.println("Tasks added to Firestore successfully!");
        } catch (Exception e) {
            System.err.println("Error adding tasks to Firestore: " + e.getMessage());
        }
    }

    // ... (other methods for adding, updating, deleting individual tasks)
}
