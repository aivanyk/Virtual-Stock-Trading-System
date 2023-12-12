package com.stock_test.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserStockView extends JPanel {
    private static Dimension buttonSize = new Dimension(200, 60);
    private static int[] size = new int[]{500, 300};
    private JButton buyButton;
    private JButton sellButton;

    public UserStockView() {
        setPreferredSize(new Dimension(size[0], size[1]));
        setLayout(null);

        buyButton = new JButton("Buy");
        buyButton.setPreferredSize(buttonSize);
        buyButton.setBounds((getPreferredSize().width- buyButton.getPreferredSize().width)/2, getPreferredSize().height*2/7, buyButton.getPreferredSize().width, buyButton.getPreferredSize().height);
        add(buyButton);

        sellButton = new JButton("Sell");
        sellButton.setPreferredSize(buttonSize);
        sellButton.setBounds((getPreferredSize().width-sellButton.getPreferredSize().width)/2, getPreferredSize().height*4/7, sellButton.getPreferredSize().width, sellButton.getPreferredSize().height);
        add(sellButton);
    }


    public void setListener(ActionListener buyLis, ActionListener sellLis) {
        buyButton.addActionListener(buyLis);
        sellButton.addActionListener(sellLis);
    }

    public static void main(String[] args){
        JFrame jf = new JFrame();
        jf.setSize(500, 400);
        UserStockView us = new UserStockView();

        jf.add(us);
        jf.setVisible(true);
    }
}