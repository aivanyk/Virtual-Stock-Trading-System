package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminMainView extends JFrame {
    private JButton viewStocksButton;
    private JButton viewCustomersButton;
    private JPanel buttonPanel;
    private JPanel contentPanel;

    public AdminMainView() {
        setTitle("Stock Administrator System");
        setLayout(new BorderLayout());
        setSize(600, 400);

        buttonPanel = new JPanel(new GridLayout(1, 2));
        viewStocksButton = new JButton("View Stocks");
        viewCustomersButton = new JButton("View Customers");

        buttonPanel.add(viewStocksButton);
        buttonPanel.add(viewCustomersButton);
        add(buttonPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
    }

    public void setViewStocksButtonListener(ActionListener listener) {
        viewStocksButton.addActionListener(listener);
    }

    public void setViewCustomersButtonListener(ActionListener listener) {
        viewCustomersButton.addActionListener(listener);
    }

    public void setContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}