package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserStockView extends JFrame {
    private JTabbedPane tabbedPane;

    public UserStockView() {
        setTitle("User Stock Administrator System");
//        setLayout(new BorderLayout());
        setSize(600, 400);
    }


    public void setTab(JPanel buyPanel, JPanel sellPanel) {
        tabbedPane.addTab("Buy", buyPanel);
        tabbedPane.addTab("Sell", sellPanel);
        add(tabbedPane);
        pack();
        validate();
        repaint();
    }
}