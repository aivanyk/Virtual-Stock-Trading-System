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
        UserInfoController userInfoController = new UserInfoController();
        userInfoController.setCustomerData(currentUser);
        userMainView.changeContentPanel(userInfoController.getView());
    }

    private void showStocks() {
        JPanel stocksPanel = new JPanel();
        userMainView.changeContentPanel(stocksPanel);
    }
}
