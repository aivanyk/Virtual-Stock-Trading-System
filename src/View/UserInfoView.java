package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserInfoView extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel balanceLabel;
    private JLabel realizedProfitLabel;
    private JLabel unrealizedProfitLabel;
    private JTextField moneyAmountField;
    private JButton depositButton;
    private JButton withdrawButton;

    public UserInfoView() {
        setLayout(new GridLayout(0, 2));

        nameLabel = new JLabel();
        emailLabel = new JLabel();
        phoneLabel = new JLabel();
        balanceLabel = new JLabel();
        realizedProfitLabel = new JLabel();
        unrealizedProfitLabel = new JLabel();
        moneyAmountField = new JTextField();
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        add(new JLabel("Name:"));
        add(nameLabel);
        add(new JLabel("Email:"));
        add(emailLabel);
        add(new JLabel("Phone:"));
        add(phoneLabel);
        add(new JLabel("Account Balance:"));
        add(balanceLabel);
        add(new JLabel("Realized Profit:"));
        add(realizedProfitLabel);
        add(new JLabel("Unrealized Profit:"));
        add(unrealizedProfitLabel);
        add(new JLabel("Deposit/Withdraw Amount"));
        add(moneyAmountField);
        add(depositButton);
        add(withdrawButton);
    }

    public void addDepositButtonListener(ActionListener listener) {
        depositButton.addActionListener(listener);
    }

    public void addWithdrawButtonListener(ActionListener listener) {
        withdrawButton.addActionListener(listener);
    }

    public void setUserInfo(String name, String email, String phone, double balance, double realizedProfit, double unrealizedProfit) {
        nameLabel.setText(name);
        emailLabel.setText(email);
        phoneLabel.setText(phone);
        balanceLabel.setText(String.format("%.2f", balance));
        realizedProfitLabel.setText(String.format("%.2f", realizedProfit));
        unrealizedProfitLabel.setText(String.format("%.2f", unrealizedProfit));
    }

    public double getMoneyAmount() {
        double amountValue = -1;
        try {
            amountValue = Double.parseDouble(moneyAmountField.getText());
            if (amountValue <= 0 || amountValue > 1000000000) amountValue = -1;
        } catch (NumberFormatException e) {
            amountValue = -1;
        }
        return amountValue;
    }
}