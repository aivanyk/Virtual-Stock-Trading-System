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
        buyView.refresh();
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
        int amount = buyView.getAmount();

        System.out.println("111: " + stockIdx);
        System.out.println("222: " + amount);

        if(stockIdx == -1) {
            JOptionPane.showMessageDialog(buyView, "Please select a stock!", "Error", JOptionPane.ERROR_MESSAGE);
            buyView.refresh();
            return;
        }

        double buyMoney = amount * (stocks.get(stockIdx).getPrice());
        if(buyMoney > customer.getAccountBalance()) {
            JOptionPane.showMessageDialog(buyView, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            buyView.refresh();
            return;
        }

        //TODO: Buy and change the balance & ownStock
        if(amount != 0) {
            customer.setAccountBalance(customer.getAccountBalance() - buyMoney);
            CustomerDatabase.updateCustomer(customer);
            OwnStock boughtStock = findOwn(stocks.get(stockIdx));
            if (boughtStock == null) {
                OwnStock newStock = new OwnStock(stocks.get(stockIdx), amount, stocks.get(stockIdx).getPrice());
                OwnDatabase.addOwnStock(customer.getId(), newStock);
            } else {
                boughtStock.addAmount(amount, stocks.get(stockIdx).getPrice());
                OwnDatabase.updateOwnStock(customer.getId(), boughtStock);
            }
            loadData();
        }

        buyView.refresh();
    }

    private OwnStock findOwn(Stock newStock){
        List<OwnStock> ownStocks = OwnDatabase.getOwnStocks(customer.getId());
        OwnStock res = null;
        for(OwnStock s: ownStocks){
            if(s.getStock().getSymbol().equals(newStock.getSymbol())) {
                res = s;
                break;
            }
        }
        return res;
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
