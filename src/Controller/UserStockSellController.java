package com.stock_test.Controller;

import com.stock_test.Model.*;
import com.stock_test.View.UserStockSellView;

import javax.swing.*;
import java.util.List;

public class UserStockSellController {
    private UserStockSellView sellView;
    private Customer customer;

    public UserStockSellController(Customer cus){
        this(new UserStockSellView());
        customer = cus;
        loadData();
        setListener();
    }

    public UserStockSellController(UserStockSellView sellView){
        this.sellView = sellView;
    }

    public UserStockSellView getView(){
        loadData();
        return sellView;
    }

    public void showView(){
        getView().setVisible(true);
    }

    public void sell(){
        int stockIdx = sellView.getSelectionIdx();
        int amount  = sellView.getAmount();


        if(stockIdx == -1) {
            JOptionPane.showMessageDialog(sellView, "Please select a stock!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(amount > OwnDatabase.getOwnStocks(customer.getId()).get(stockIdx).getAmount()) {
            JOptionPane.showMessageDialog(sellView, "Not enough amount in your account!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //TODO: Sell and change the profit && OwnDatabase
        double sellMoney = amount * (OwnDatabase.getOwnStocks(customer.getId()).get(stockIdx).getStock().getPrice());
        customer.setAccountBalance(customer.getAccountBalance() + sellMoney);
        CustomerDatabase.updateCustomer(customer);

        sellView.refresh();
        loadData();
    }

    public void cancel(){
        sellView.dispose();
    }

    public void loadData(){
        List<OwnStock> stocks = OwnDatabase.getOwnStocks(customer.getId());
        String[] symbols = new String[stocks.size()];
        for(int i=0; i<stocks.size(); i++) symbols[i] = stocks.get(i).getStock().getSymbol();
        sellView.setStocksValue(symbols);

        sellView.setMoneyValue(customer.getAccountBalance());
    }

    public void setListener(){
        sellView.setSellButtonListener(e -> sell());
        sellView.setCancelButtonListener(e -> cancel());
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("TEST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
    }
}
