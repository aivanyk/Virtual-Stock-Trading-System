package com.stock_test.View;

import com.stock_test.Controller.BgColorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends ColorJFrame {
    private JPanel logoPanel;
    private JTabbedPane tabbedPane;

    public MainView() {
        super();
        setTitle("Stock Trader");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Logo Panel
        logoPanel = new JPanel();
        ImageIcon logoImage = new ImageIcon("..\\resource\\logo.png");
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.CENTER);

        tabbedPane = new JTabbedPane();

    }

    public void setTab(JPanel loginPanel, JPanel signupPanel){
        tabbedPane.addTab("Signup", signupPanel);
        tabbedPane.addTab("Login", loginPanel);
        add(tabbedPane, BorderLayout.CENTER);
        pack();
        validate();
        repaint();
    }
}
