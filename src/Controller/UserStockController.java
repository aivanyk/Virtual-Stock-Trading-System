package com.stock_test.Controller;

import com.stock_test.Model.Customer;
import com.stock_test.View.UserStockView;

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

}