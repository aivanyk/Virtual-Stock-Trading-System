package com.stock_test.Controller;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.stock_test.Model.Customer;
import com.stock_test.View.UserMainView;

public class UserMainController {
    private UserMainView userMainView;
    private Customer currentUser;

    public UserMainController(Customer currentUser) {
        this.currentUser = currentUser;
        this.userMainView = new UserMainView();
        setListeners();
    }

    public void showMainView() {
        userMainView.setVisible(true);
    }

    private void setListeners() {
        userMainView.addShowInfoButtonListener(e -> showInformation());
        userMainView.addShowStocksButtonListener(e -> showStocks());
    }

    private void showInformation() {
        // Need to separate into different view
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 2));
        infoPanel.add(new JLabel("Email:"));
        infoPanel.add(new JLabel(currentUser.getEmail()));
        infoPanel.add(new JLabel("Name:"));
        infoPanel.add(new JLabel(currentUser.getName()));
        infoPanel.add(new JLabel("Phone:"));
        infoPanel.add(new JLabel(currentUser.getPhone()));
        infoPanel.add(new JLabel("Balance"));
        infoPanel.add(new JLabel("" + currentUser.getAccountBalance()));
        userMainView.changeContentPanel(infoPanel);
    }

    private void showStocks() {
        JPanel stocksPanel = new JPanel();
        userMainView.changeContentPanel(stocksPanel);
    }
}
