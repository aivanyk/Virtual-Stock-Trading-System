package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// The login view
public class LoginView extends TransPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    // Constructor
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

    // Get the input email
    public String getEmail() {
        return emailField.getText();
    }

    // Get the input password
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    // Set login listener
    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    // Set cancel listener
    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    // Reset the inputs
    public void reset(){
        emailField.setText("");
        passwordField.setText("");
    }
}
