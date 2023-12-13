package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends TransPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginView() {
        setPreferredSize(new Dimension(300, 200));
        setLayout(new GridLayout(6, 2));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        add(new JLabel());
        add(new JLabel());
        add(new JLabel("Email:"));
        add(emailField);

        add(new JLabel());
        add(new JLabel());
        add(new JLabel("Password:"));
        add(passwordField);

        add(new JLabel());
        add(new JLabel());
        add(loginButton);
        add(cancelButton);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
//        cancelButton.addActionListener(listener);
    }
}
