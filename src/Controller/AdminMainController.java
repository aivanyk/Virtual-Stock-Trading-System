package com.stock_test.Controller;

import com.stock_test.Model.Customer;
import com.stock_test.View.AdminMainView;
import com.stock_test.Controller.CustomerController.NotifyCallback;

public class AdminMainController implements NotifyCallback {
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
        this.customerController.setNotifyCallback(this);
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

    public void requestNotify(Customer target) {
        System.out.println("Notify to user!" + target.getName());
        return;
    }
}