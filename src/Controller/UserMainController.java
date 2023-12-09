package com.stock_test.Controller;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import com.stock_test.Model.Customer;
import com.stock_test.View.UserMainView;
import com.stock_test.Controller.AdminMainController.AdminObserver;

public class UserMainController implements AdminObserver {
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
        UserStockController stockController = new UserStockController(currentUser);
        userMainView.changeContentPanel(stockController.getView());
    }

    public void onAdminAction(Customer target) {
        if (target.getId() == currentUser.getId()) {
            String msg = "Dear " + currentUser.getName() + ", From now on, you can be a super user!";
            JOptionPane.showMessageDialog(userMainView, msg, "Congratulation", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
