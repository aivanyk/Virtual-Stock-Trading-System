package com.stock_test.Controller;

import com.stock_test.Model.Customer;
import com.stock_test.View.UserStockView;
import com.stock_test.Model.Stock;
import com.stock_test.Model.StockDatabase;

import java.util.Vector;

// Controller for the stock buy/sell page
public class UserStockController {
    private UserStockView mainView;
    private UserStockBuyController stockBuyController;
    private UserStockSellController stockSellController;

    // Constructor
    public UserStockController(Customer customer) {
        this(new UserStockView(), new UserStockBuyController(customer), new UserStockSellController(customer));
    }

    public UserStockController(UserStockView mainView, UserStockBuyController stockBuyController, UserStockSellController stockSellController) {
        this.mainView = mainView;
        this.stockBuyController = stockBuyController;
        this.stockSellController = stockSellController;
        mainView.setListener(e->showBuyFrame(), e->showSellFrame());
        loadStockData();
    }

    // Show the buy frame
    public void showBuyFrame(){
        stockBuyController.showView();
    }

    // Show the sell frame
    public void showSellFrame(){
        stockSellController.showView();
    }

    // Get the view
    public UserStockView getView() {
        return mainView;
    }

    // Load data for display
    public void loadStockData() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Symbol");
        columnNames.add("Name");
        columnNames.add("Price");

        Vector<Vector<Object>> data = new Vector<>();
        for (Stock stock : StockDatabase.getStocks()) {
            Vector<Object> row = new Vector<>();
            row.add(stock.getSymbol());
            row.add(stock.getName());
            row.add(stock.getPrice());
            data.add(row);
        }

        mainView.setTableData(columnNames, data);
    }

}