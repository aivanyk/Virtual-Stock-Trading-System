package com.stock_test.Controller;

import com.stock_test.Model.*;
import com.stock_test.View.UserStockBuyView;

import javax.swing.*;
import java.util.List;

public class UserStockBuyController {
    private UserStockBuyView buyView;
    private Customer customer;
    List<Stock> stocks;

    public UserStockBuyController(Customer cus){
        this(new UserStockBuyView());
        customer = cus;
        loadData();
        setListener();
    }

    public UserStockBuyController(UserStockBuyView buyView){
        this.buyView = buyView;
    }

    public UserStockBuyView getView(){
        loadData();
        return buyView;
    }

    public void showView(){
        getView().setVisible(true);
    }

    public void cancel(){
        buyView.dispose();
    }

    public void buy(){
        int stockIdx = buyView.getSelectionIdx();
        int amount  = buyView.getAmount();
        double buyMoney = amount * (stocks.get(stockIdx).getPrice());


        if(stockIdx == -1)
            JOptionPane.showMessageDialog(buyView, "Please select a stock!", "Error", JOptionPane.ERROR_MESSAGE);

        if(buyMoney > customer.getAccountBalance()) {
            JOptionPane.showMessageDialog(buyView, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //TODO: Buy and change the balance & ownStock

        buyView.refresh();
        loadData();
    }

    public void loadData(){
        stocks = StockDatabase.getStocks();
        String[] symbols = new String[stocks.size()];
        for(int i=0; i<stocks.size(); i++) symbols[i] = stocks.get(i).getSymbol();
        buyView.setStocksValue(symbols);

        buyView.setMoneyValue(customer.getAccountBalance());
    }

    public void setListener(){
        buyView.setBuyButtonListener(e -> buy());
        buyView.setCancelButtonListener(e -> cancel());
    }
}
