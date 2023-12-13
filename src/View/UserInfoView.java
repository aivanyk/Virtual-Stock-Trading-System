package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserInfoView extends JPanel {
    private JLabel VIPLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel balanceLabel;
    private JLabel realizedProfitLabel;
    private JLabel unrealizedProfitLabel;
    private JTextField moneyAmountField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton notifyButton;
    private static int[] size = new int[]{500, 320};

    public UserInfoView(boolean fromAdmin) {
        setPreferredSize(new Dimension(size[0], size[1]));
        setLayout(new GridLayout(0, 2));

        VIPLabel = new JLabel();
        VIPLabel.setForeground(Color.RED);
        nameLabel = new JLabel();
        emailLabel = new JLabel();
        phoneLabel = new JLabel();
        balanceLabel = new JLabel();
        realizedProfitLabel = new JLabel();
        unrealizedProfitLabel = new JLabel();
        moneyAmountField = new JTextField();
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        notifyButton = new JButton("Notify");

        add(VIPLabel);
        add(new TransPanel());
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

        if (fromAdmin) {
            add(notifyButton);
        } else {
            add(new JLabel("Deposit/Withdraw Amount"));
            add(moneyAmountField);
            add(depositButton);
            add(withdrawButton);
        }
        add(new TransPanel());
        add(new TransPanel());
    }

    public void addDepositButtonListener(ActionListener listener) {
        depositButton.addActionListener(listener);
    }

    public void addWithdrawButtonListener(ActionListener listener) {
        withdrawButton.addActionListener(listener);
    }

    public void addNotifyButtonListener(ActionListener listener) {
        notifyButton.addActionListener(listener);
    }

    public void setUserInfo(String name, String email, String phone, double balance, double realizedProfit, double unrealizedProfit, boolean isVIP) {
        VIPLabel.setText(isVIP? "You are a VIP customer!": "");
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