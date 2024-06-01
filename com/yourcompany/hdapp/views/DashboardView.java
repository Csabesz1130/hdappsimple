package com.yourcompany.hdapp.views;

import com.yourcompany.hdapp.MainFrame;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JPanel {
    private MainFrame mainFrame;

    public DashboardView(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton tasksButton = new JButton("Tasks");
        tasksButton.addActionListener(e -> mainFrame.showView("TaskView"));
        JButton locationsButton = new JButton("Locations");
        locationsButton.addActionListener(e -> mainFrame.showView("LocationView"));
        JButton reportsButton = new JButton("Reports");
        reportsButton.addActionListener(e -> mainFrame.showView("ReportsView"));
        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> mainFrame.showView("SettingsView"));

        buttonPanel.add(tasksButton);
        buttonPanel.add(locationsButton);
        buttonPanel.add(reportsButton);
        buttonPanel.add(settingsButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
