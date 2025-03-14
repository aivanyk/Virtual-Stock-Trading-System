package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// View to display the logo
public class LogoView extends TransPanel{
    private JButton loginButton;
    private JButton signupButton;
    private TransPanel buttonPanel;

    // Constructor
    public LogoView(){
        super();
        setLayout(new BorderLayout());
        TransPanel contentPanel = new TransPanel(new BorderLayout());
        ImageIcon logoImage = new ImageIcon("../resource/logo.png");
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        contentPanel.add(logoLabel, BorderLayout.CENTER);

        buttonPanel = new TransPanel(new GridLayout(1, 2));
        buttonPanel.setSize(300, 35);
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        buttonPanel.add(signupButton);
        buttonPanel.add(loginButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(contentPanel);
    }

    // Set listeners for login/signup buttons
    public void setListener(ActionListener loginLis, ActionListener signupLis){
        loginButton.addActionListener(loginLis);
        signupButton.addActionListener(signupLis);
    }
}
