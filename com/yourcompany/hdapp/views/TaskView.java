package com.yourcompany.hdapp.views;

import com.yourcompany.hdapp.MainFrame;
import com.yourcompany.hdapp.models.Task;
import com.yourcompany.hdapp.services.FirestoreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TaskView extends JPanel {
    private MainFrame mainFrame;
    private FirestoreService firestoreService;
    private DefaultListModel<Task> taskListModel;

    public TaskView(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.firestoreService = new FirestoreService();
        this.taskListModel = new DefaultListModel<>();

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Tasks", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JList<Task> taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskRenderer());
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener((ActionEvent e) -> addTask());
        JButton deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener((ActionEvent e) -> deleteTask(taskList.getSelectedValue()));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loadTasks();
    }

    private void loadTasks() {
        taskListModel.clear();
        List<Task> tasks = firestoreService.getAllTasks();
        if (tasks != null) {
            tasks.forEach(taskListModel::addElement);
        }
    }

    private void addTask() {
        String id = JOptionPane.showInputDialog(this, "Enter Task ID:");
        String name = JOptionPane.showInputDialog(this, "Enter Task Name:");
        String description = JOptionPane.showInputDialog(this, "Enter Task Description:");
        String status = JOptionPane.showInputDialog(this, "Enter Task Status:");

        Task task = new Task(id, name, description, status);
        firestoreService.addTask(task);
        loadTasks();
    }

    private void deleteTask(Task task) {
        if (task != null) {
            firestoreService.deleteTask(task.getId());
            loadTasks();
        }
    }

    private class TaskRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (c instanceof JLabel && value instanceof Task) {
                Task task = (Task) value;
                ((JLabel) c).setText(task.getName() + " - " + task.getDescription() + " [" + task.getStatus() + "]");
            }
            return c;
        }
    }
}
