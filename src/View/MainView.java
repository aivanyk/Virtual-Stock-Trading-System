package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Main view that switch content between logo/login/signup
public class MainView extends ColorJFrame {
    private LogoView logoPanel;
    private JTabbedPane tabbedPane;

    // Constructor
    public MainView() {
        super();
        setTitle("Stock Trader");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logoPanel = new LogoView();
        add(logoPanel, BorderLayout.CENTER);
        tabbedPane = new JTabbedPane();

    }

    // Set listeners for logo page
    public void setLogoListener(ActionListener loginLis, ActionListener signupLis){
        logoPanel.setListener(loginLis, signupLis);
    }

    // Change the display content to logo
    public void changePanelToLogo() {
        getContentPane().remove(tabbedPane);
        add(logoPanel, BorderLayout.CENTER);
        pack();
        validate();
        repaint();
    }

    // Change the display content to login
    public void changePanelToLogin() {
        getContentPane().remove(logoPanel);
        tabbedPane.setSelectedIndex(1);
        add(tabbedPane, BorderLayout.CENTER);
        pack();
        validate();
        repaint();
    }

    // Change the display content to signup
    public void changePanelToSignup() {
        getContentPane().remove(logoPanel);
        tabbedPane.setSelectedIndex(0);
        add(tabbedPane, BorderLayout.CENTER);
        pack();
        validate();
        repaint();
    }

    // Set up the tabbed panel
    public void setTab(JPanel loginPanel, JPanel signupPanel){
        tabbedPane.addTab("Signup", signupPanel);
        tabbedPane.addTab("Login", loginPanel);
    }
}
