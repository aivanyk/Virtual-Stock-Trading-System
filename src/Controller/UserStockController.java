package com.stock_test.Controller;

import com.stock_test.Model.Customer;
import com.stock_test.View.UserStockView;
import com.stock_test.Model.Stock;
import com.stock_test.Model.StockDatabase;

import java.util.Vector;

public class UserStockController {
    private UserStockView mainView;
    private UserStockBuyController stockBuyController;
    private UserStockSellController stockSellController;
    private Customer customer;

    public UserStockController(Customer customer) {
        this(new UserStockView(), new UserStockBuyController(customer), new UserStockSellController(customer));
        this.customer = customer;
    }

    public UserStockController(UserStockView mainView, UserStockBuyController stockBuyController, UserStockSellController stockSellController) {
        this.mainView = mainView;
        this.stockBuyController = stockBuyController;
        this.stockSellController = stockSellController;
        mainView.setListener(e->showBuyFrame(), e->showSellFrame());
        loadStockData();
    }

    public void showBuyFrame(){
        stockBuyController.showView();
    }

    public void showSellFrame(){
        stockSellController.showView();
    }

    public void showMainView() {
        mainView.setVisible(true);
    }

    public UserStockView getView() {
        return mainView;
    }

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