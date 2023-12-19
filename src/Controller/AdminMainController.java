package com.stock_test.Controller;

import java.util.List;
import java.util.ArrayList;
import com.stock_test.Model.Customer;
import com.stock_test.View.AdminMainView;
import com.stock_test.Controller.CustomerController.NotifyCallback;

// Controller responsible for managing the admin functionalities
public class AdminMainController implements NotifyCallback {
    public interface AdminObserver {
        void onAdminAction(Customer target);
    }
    private List<AdminObserver> adminObservers = new ArrayList<>();

    private AdminMainView mainView;
    private StockController stockController;
    private CustomerController customerController;

    // Constructor
    public AdminMainController() {
        this(new AdminMainView(), new StockController(), new CustomerController());
    }

    public AdminMainController(AdminMainView mainView, StockController stockController, CustomerController customerController) {
        this.mainView = mainView;
        this.stockController = stockController;
        this.customerController = customerController;
        this.customerController.setNotifyCallback(this);
        setListeners();

        BgColorController.getInstance().registerFrame(mainView);
        mainView.addColorPanel();
    }

    // Display the main view
    public void showMainView() {
        mainView.setVisible(true);
    }

    // Retrieve the main view
    public AdminMainView getView() {
        return mainView;
    }

    // Set listeners for different buttons
    public void setListeners() {
        this.mainView.setViewStocksButtonListener(e -> viewStocks());
        this.mainView.setViewCustomersButtonListener(e -> viewCustomers());
    }

    // Method to view stock data
    public void viewStocks() {
        stockController.loadStockData();
        mainView.setContentPanel(stockController.getStockView());
    }

    // Method to view customer data
    public void viewCustomers() {
        customerController.loadCustomerData();
        mainView.setContentPanel(customerController.getCustomerView());
    }

    // Method to request notifications for admin actions
    public void requestNotify(Customer target) {
        for (AdminObserver observer : adminObservers) {
            observer.onAdminAction(target);
        }
    }

    public void registerAdminObserver(AdminObserver observer) {
        adminObservers.add(observer);
    }
}