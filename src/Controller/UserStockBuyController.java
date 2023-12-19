package com.stock_test.Controller;

import com.stock_test.Model.*;
import com.stock_test.View.UserStockBuyView;

import javax.swing.*;
import java.util.List;

// Controller for buying stocks
public class UserStockBuyController {
    private UserStockBuyView buyView;
    private Customer customer;
    private List<Stock> stocks;
    private double priceRatio = 0.01;

    // Constructor
    public UserStockBuyController(Customer cus){
        this(new UserStockBuyView());
        customer = cus;
        loadData();
        setListener();

        BgColorController.getInstance().registerFrame(buyView);
        buyView.addColorPanel();
    }

    public UserStockBuyController(UserStockBuyView buyView){
        this.buyView = buyView;
    }

    // Get the view
    public UserStockBuyView getView(){
        loadData();
        buyView.refresh();
        return buyView;
    }

    // Show the view
    public void showView(){
        getView().setVisible(true);
    }

    // Cancel action
    public void cancel(){
        buyView.dispose();
    }

    // Method for buy action
    public void buy(){
        int stockIdx = buyView.getSelectionIdx();
        int amount = buyView.getAmount();

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
            changeStockPrice(stocks.get(stockIdx), amount);
            loadData();
        }

        buyView.refresh();
    }

    // Change the stock price after buying
    private void changeStockPrice(Stock s, int amount){
        int price = s.getPrice();
        for(int i=0; i<amount; i++){
            price += (int) (price * priceRatio);
        }
        s.setPrice(price);
        StockDatabase.updateStock(s);
    }

    // Get the stock that the user owns given the stock symbol
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

    // Load stock data
    public void loadData(){
        stocks = StockDatabase.getStocks();
        String[] symbols = new String[stocks.size()];
        for(int i=0; i<stocks.size(); i++) symbols[i] = stocks.get(i).getSymbol();
        buyView.setStocksValue(symbols);

        buyView.setMoneyValue(customer.getAccountBalance());
    }

    // Set listeners for the buttons
    public void setListener(){
        buyView.setBuyButtonListener(e -> buy());
        buyView.setCancelButtonListener(e -> cancel());
    }
}
