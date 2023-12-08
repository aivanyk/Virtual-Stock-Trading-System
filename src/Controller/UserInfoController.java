package com.stock_test.Controller;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import com.stock_test.View.UserInfoView;
import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.Model.OwnStock;
import com.stock_test.Model.OwnDatabase;

public class UserInfoController {
    private UserInfoView userInfoView;
    private Customer currentCustomer;

    public UserInfoController(boolean fromAdmin) {
        this.userInfoView = new UserInfoView(fromAdmin);
        setListeners();
    }

    public UserInfoController() {
        this(false);
    }

    private void setListeners() {
        this.userInfoView.addDepositButtonListener(e -> deposit());
        this.userInfoView.addWithdrawButtonListener(e -> withdraw());
    }

    public void setCustomerData(Customer customer) {
        currentCustomer = customer;
        List<OwnStock> stocks = OwnDatabase.getOwnStocks(customer.getId());
        double unrealizedProfit = 0.0;
        for (OwnStock stock : stocks) {
            unrealizedProfit += stock.getProfit();
        }

        userInfoView.setUserInfo(
            customer.getName(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getAccountBalance(),
            customer.getRealizedProfit(),
            unrealizedProfit
        );
    }

    public UserInfoView getView() {
        return userInfoView;
    }

    private void deposit() {
        double amount = userInfoView.getMoneyAmount();
        if (amount > 0) {
            currentCustomer.setAccountBalance(currentCustomer.getAccountBalance() + amount);
            CustomerDatabase.updateCustomer(currentCustomer);
            JOptionPane.showMessageDialog(userInfoView, "Deposit Success!", "Deposit", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(userInfoView, "Invalid Number", "Error", JOptionPane.ERROR_MESSAGE);
        }

        setCustomerData(currentCustomer);
    }

    private void withdraw() {
        double amount = userInfoView.getMoneyAmount();
        if (amount > 0) {
            if (amount > currentCustomer.getAccountBalance()) {
                JOptionPane.showMessageDialog(userInfoView, "Insufficient Balance", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentCustomer.setAccountBalance(currentCustomer.getAccountBalance() - amount);
            CustomerDatabase.updateCustomer(currentCustomer);
            JOptionPane.showMessageDialog(userInfoView, "Withdraw Success!", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(userInfoView, "Invalid Number", "Error", JOptionPane.ERROR_MESSAGE);
        }

        setCustomerData(currentCustomer);
    }
}