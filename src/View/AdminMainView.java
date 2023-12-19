package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Administrator view
public class AdminMainView extends ColorJFrame {
    private JButton viewStocksButton;
    private JButton viewCustomersButton;
    private JPanel buttonPanel;
    private JPanel contentPanel;

    // Constructor
    public AdminMainView() {
        super();
        setTitle("Stock Administrator System");
        setLayout(new BorderLayout());
        setSize(600, 400);

        buttonPanel = new TransPanel(new GridLayout(1, 2));
        viewStocksButton = new JButton("View Stocks");
        viewCustomersButton = new JButton("View Customers");

        buttonPanel.add(viewStocksButton);
        buttonPanel.add(viewCustomersButton);
        add(buttonPanel, BorderLayout.NORTH);

        contentPanel = new TransPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
    }

    // Set view stocks button listener
    public void setViewStocksButtonListener(ActionListener listener) {
        viewStocksButton.addActionListener(listener);
    }

    // Set view customers button listener
    public void setViewCustomersButtonListener(ActionListener listener) {
        viewCustomersButton.addActionListener(listener);
    }

    // Refresh what to show
    public void setContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}