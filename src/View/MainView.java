package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JPanel logoPanel;
    private JPanel buttonPanel;
    private JButton signupButton;
    private JButton loginButton;

    public MainView() {
        setTitle("Stock Trader");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Logo Panel
        logoPanel = new JPanel();
        // System.out.println(System.getProperty("user.dir"));
        ImageIcon logoImage = new ImageIcon("..\\resource\\logo.png");
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel(new GridLayout(1, 2));
        signupButton = new JButton("Signup");
        loginButton = new JButton("Login");

        buttonPanel.add(signupButton);
        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addSignupButtonListener(ActionListener listener) {
        signupButton.addActionListener(listener);
    }

    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void changePanel(JPanel panel) {
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        validate();
        repaint();
    }

    public void changePanelToLogo() {
        changePanel(logoPanel);
    }
}
