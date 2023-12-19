package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// TThe signup view
public class SignupView extends TransPanel {
    private JTextField emailField;
    private JButton checkDuplicateButton;
    private JLabel duplicateResultLabel;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField phoneField;
    private JButton submitButton;
    private JButton cancelButton;

    // Constructor
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

        add(new JLabel("Email:"));
        add(emailField);

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

    // Reset the inputs
    public void reset() {
        emailField.setEditable(true);
        emailField.setText("");
        phoneField.setText("");
        nameField.setText("");
        passwordField.setText("");
        duplicateResultLabel.setText("");
    }

    // Disable the email input
    public void disableEmail() {
        emailField.setEditable(false);
    }

    // Get the status of email input
    public boolean getEmailDisabled() {
        return !emailField.isEditable();
    }

    // Get the input email
    public String getEmail() {
        return emailField.getText();
    }

    // Get the input password
    public char[] getPassword() {
        return passwordField.getPassword();
    }

    // Get the input name
    public String getName() {
        return nameField.getText();
    }

    // Get the input phone
    public String getPhone() {
        return phoneField.getText();
    }

    // Whether all needed fields are filled
    public boolean fullFields() {
        return !emailField.getText().isEmpty() && passwordField.getPassword().length > 0 &&
                !nameField.getText().isEmpty() && !phoneField.getText().isEmpty();
    }

    // Set submission listener
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    // Set cancel listener
    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    // Set check email duplicate listener
    public void addCheckDuplicateButtonListener(ActionListener listener) {
        checkDuplicateButton.addActionListener(listener);
    }

    // Set warning text
    public void setDuplicateResultText(String text) {
        duplicateResultLabel.setText(text);
    }
}
