package com.stock_test.View;

import com.stock_test.Util.StockTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

// User view to show buy/sell button
public class UserStockView extends JPanel {
    private static Dimension buttonSize = new Dimension(200, 60);
    private static Dimension tableSize = new Dimension(400, 200);
    private static int[] size = new int[]{500, 300};
    private JButton buyButton;
    private JButton sellButton;
    private JTable stockTable;
    private DefaultTableModel tableModel;

    // Constructor
    public UserStockView() {
        setPreferredSize(new Dimension(size[0], size[1]));
        setLayout(null);

        tableModel = new StockTableModel();
        stockTable = new JTable(tableModel);
        stockTable.setEnabled(false); // READ ONLY
        JScrollPane scroll = new JScrollPane(stockTable);
        scroll.setPreferredSize(tableSize);
        scroll.setBounds((getPreferredSize().width- scroll.getPreferredSize().width)/2, getPreferredSize().height*0/7, scroll.getPreferredSize().width, scroll.getPreferredSize().height);
        add(scroll);

        buyButton = new JButton("Buy");
        buyButton.setPreferredSize(buttonSize);
        buyButton.setBounds((getPreferredSize().width-(buyButton.getPreferredSize().width*2))/2, getPreferredSize().height*5/7, buyButton.getPreferredSize().width, buyButton.getPreferredSize().height);
        add(buyButton);

        sellButton = new JButton("Sell");
        sellButton.setPreferredSize(buttonSize);
        sellButton.setBounds((getPreferredSize().width-(sellButton.getPreferredSize().width*2))/2 + sellButton.getPreferredSize().width, getPreferredSize().height*5/7, sellButton.getPreferredSize().width, sellButton.getPreferredSize().height);
        add(sellButton);
    }

    // Set the table data to show
    public void setTableData(Vector<String> columnNames, Vector<Vector<Object>> data) {
        tableModel.setDataVector(data, columnNames);
    }

    // Set the listeners
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