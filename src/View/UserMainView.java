package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//User main view to switch between info/stock view
public class UserMainView extends ColorJFrame {
    private JPanel buttonPanel;
    private JPanel contentPanel;
    private JButton showInfoButton;
    private JButton showStocksButton;

    // Constructor
    public UserMainView() {
        super();
        setTitle("User Main Page");
        setSize(500, 400);
        setLayout(new BorderLayout());

        buttonPanel = new TransPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        showInfoButton = new JButton("Show Information");
        showStocksButton = new JButton("Show Stocks");
        buttonPanel.add(showInfoButton);
        buttonPanel.add(showStocksButton);
        add(buttonPanel, BorderLayout.NORTH);

        contentPanel = new TransPanel();
        add(contentPanel, BorderLayout.CENTER);
    }

    // Setters for listeners
    public void addShowInfoButtonListener(ActionListener listener) {
        showInfoButton.addActionListener(listener);
    }

    public void addShowStocksButtonListener(ActionListener listener) {
        showStocksButton.addActionListener(listener);
    }

    // Switch panels to show
    public void changeContentPanel(JPanel panel) {
        panel.setOpaque(false);
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
