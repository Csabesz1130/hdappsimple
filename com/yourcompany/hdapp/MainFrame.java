package com.yourcompany.hdapp;

import com.yourcompany.hdapp.views.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("HDApp Main Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        addViews();

        add(mainPanel);
    }

    private void addViews() {
        mainPanel.add(new LoginView(this), "LoginView");
        mainPanel.add(new DashboardView(this), "DashboardView");
        mainPanel.add(new TaskView(this), "TaskView");
        mainPanel.add(new LocationView(this), "LocationView");
        mainPanel.add(new SettingsView(this), "SettingsView");
        mainPanel.add(new ReportsView(this), "ReportsView");
        mainPanel.add(new NotificationsView(this), "NotificationsView");
        mainPanel.add(new UserManagementView(this), "UserManagementView");
    }

    public void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
