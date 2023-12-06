package com.stock_test.Controller;

import com.stock_test.View.AdminMainView;

public class AdminMainController {
    private AdminMainView mainView;
    private StockController stockController;
    private CustomerController customerController;

    public AdminMainController() {
        this(new AdminMainView(), new StockController(), new CustomerController());
    }

    public AdminMainController(AdminMainView mainView, StockController stockController, CustomerController customerController) {
        this.mainView = mainView;
        this.stockController = stockController;
        this.customerController = customerController;
        setListeners();
    }

    public void showMainView() {
        mainView.setVisible(true);
    }

    public AdminMainView getView() {
        return mainView;
    }

    public void setListeners() {
        this.mainView.setViewStocksButtonListener(e -> viewStocks());
        this.mainView.setViewCustomersButtonListener(e -> viewCustomers());
    }

    public void viewStocks() {
        stockController.loadStockData();
        mainView.setContentPanel(stockController.getView());
    }

    public void viewCustomers() {
        customerController.loadCustomerData();
        mainView.setContentPanel(customerController.getView());
    }
}