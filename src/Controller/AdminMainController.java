package com.stock_test.Controller;

import java.util.List;
import java.util.ArrayList;
import com.stock_test.Model.Customer;
import com.stock_test.View.AdminMainView;
import com.stock_test.Controller.CustomerController.NotifyCallback;

public class AdminMainController implements NotifyCallback {
    public interface AdminObserver {
        void onAdminAction(Customer target);
    }
    private List<AdminObserver> adminObservers = new ArrayList<>();

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

        BgColorController.getInstance().registerFrame(mainView);
        mainView.addColorPanel();
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
        mainView.setContentPanel(stockController.getStockView());
    }

    public void viewCustomers() {
        customerController.loadCustomerData();
        mainView.setContentPanel(customerController.getCustomerView());
    }

    public void requestNotify(Customer target) {
        for (AdminObserver observer : adminObservers) {
            observer.onAdminAction(target);
        }
    }

    public void registerAdminObserver(AdminObserver observer) {
        adminObservers.add(observer);
    }
}