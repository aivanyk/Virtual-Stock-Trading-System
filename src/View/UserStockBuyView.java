package com.stock_test.View;

import com.stock_test.Controller.UserStockBuyController;

import javax.swing.*;
import java.awt.*;

public class UserStockBuyView extends JPanel {
    private JScrollPane scrollPanel;
    private Button buyButton;
    private Button sellButton;
    private static Dimension buttonSize = new Dimension(200, 200);

    public UserStockBuyView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 200));

        setMoney();
        setStocks();
        setAmount();

        buyButton = new Button("Buy");
        buyButton.setMaximumSize(buttonSize);
        sellButton = new Button("Sell");
        sellButton.setMaximumSize(buttonSize);
        add(buyButton);
        add(sellButton);
    }

    private void setMoney(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        double moneyAmount = 1000.50;

        JLabel moneyLabel = new JLabel("Money: $" + moneyAmount);

        panel.add(moneyLabel);
        add(panel);
    }

    private void setStocks(){
        String[] data = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};
        JComboBox stockList = new JComboBox(data);
        stockList.setEditable(true);
        stockList.setMaximumSize( stockList.getPreferredSize() );

        add(stockList);
    }

    private void setAmount(){
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Amount: ");
        JTextField textField = new JTextField(10);

        panel.add(label);
        panel.add(textField);

        add(panel);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("TEST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new UserStockBuyView());
        frame.setVisible(true);
    }
}
