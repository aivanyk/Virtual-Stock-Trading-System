package com.stock_test.Controller;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import com.stock_test.Model.Customer;
import com.stock_test.View.UserMainView;
import com.stock_test.Controller.AdminMainController.AdminObserver;

// Controller for user main view
public class UserMainController implements AdminObserver {
    private UserMainView userMainView;
    private Customer currentUser;

    // Constructor
    public UserMainController(Customer currentUser) {
        this.currentUser = currentUser;
        this.userMainView = new UserMainView();
        setListeners();
        BgColorController.getInstance().registerFrame(userMainView);
        userMainView.addColorPanel();
    }

    // Show the view
    public void showMainView() {
        userMainView.setVisible(true);
        showInformation();
    }

    // Set listeners for the buttons
    private void setListeners() {
        userMainView.addShowInfoButtonListener(e -> showInformation());
        userMainView.addShowStocksButtonListener(e -> showStocks());
    }

    // Show user information on the page
    private void showInformation() {
        UserInfoController userInfoController = new UserInfoController();
        userInfoController.setCustomerData(currentUser);
        userMainView.changeContentPanel(userInfoController.getView());
    }

    // Show stock buttons on the page
    private void showStocks() {
        UserStockController stockController = new UserStockController(currentUser);
        userMainView.changeContentPanel(stockController.getView());
    }

    // Observer action, notify super user
    public void onAdminAction(Customer target) {
        if (target.getId() == currentUser.getId()) {
            String msg = "Dear " + currentUser.getName() + ", From now on, you can be a super user!";
            JOptionPane.showMessageDialog(userMainView, msg, "Congratulation", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
