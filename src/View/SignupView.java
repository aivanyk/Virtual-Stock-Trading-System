package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignupView extends TransPanel {
    private JTextField emailField;
    private JButton checkDuplicateButton;
    private JLabel duplicateResultLabel;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField phoneField;
    private JButton submitButton;
    private JButton cancelButton;
    private static Dimension textFieldSize = new Dimension(200, 60);

    public SignupView() {
        setPreferredSize(new Dimension(300, 200));
        setLayout(new GridLayout(0, 2));

        emailField = new JTextField(20);
        checkDuplicateButton = new JButton("Check Duplicate");
        duplicateResultLabel = new JLabel();
        passwordField = new JPasswordField(20);
        nameField = new JTextField(20);
        phoneField = new JTextField(20);
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");


//        emailField.setMaximumSize(textFieldSize);
//        JPanel emailPanel = new JPanel();
//        emailPanel.add(emailField);
        add(new JLabel("Email:"));
        add(emailField);
//        add(emailPanel);

        add(checkDuplicateButton);
        add(duplicateResultLabel);

        add(new JLabel("Password:"));
        add(passwordField);

        add(new JLabel("Name:"));
        add(nameField);

        add(new JLabel("Phone:"));
        add(phoneField);

        add(submitButton);
        add(cancelButton);
    }

    public void reset() {
        emailField.setEditable(true);
        emailField.setText("");
        phoneField.setText("");
        nameField.setText("");
        passwordField.setText("");
        duplicateResultLabel.setText("");
    }

    public void disableEmail() {
        emailField.setEditable(false);
    }

    public boolean getEmailDisabled() {
        return !emailField.isEditable();
    }

    public String getEmail() {
        return emailField.getText();
    }
    
    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public String getName() {
        return nameField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public boolean fullFields() {
        return !emailField.getText().isEmpty() && passwordField.getPassword().length > 0 &&
                !nameField.getText().isEmpty() && !phoneField.getText().isEmpty();
    }
    
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
//        cancelButton.addActionListener(listener);
    }

    public void addCheckDuplicateButtonListener(ActionListener listener) {
        checkDuplicateButton.addActionListener(listener);
    }

    public void setDuplicateResultText(String text) {
        duplicateResultLabel.setText(text);
    }
}
