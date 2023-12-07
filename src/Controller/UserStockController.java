//package com.stock_test.Controller;
//
//import com.stock_test.Model.Customer;
//import com.stock_test.View.AdminMainView;
//import com.stock_test.View.UserStockView;
//
//public class UserStockController {
//    private UserStockView mainView;
//    private UserStockBuyController stockBuyController;
//    private UserStockSellController stockSellController;
//    private Customer customer;
//
//    public UserStockController(Customer customer) {
//        this(new UserStockView(), new UserStockBuyController(), new UserStockSellController());
//        this.customer = customer;
//    }
//
//    public UserStockController(UserStockView mainView, UserStockBuyController stockBuyController, UserStockSellController stockSellController) {
//        this.mainView = mainView;
//        this.stockBuyController = stockBuyController;
//        this.stockSellController = stockSellController;
//        mainView.setTab();
//    }
//
//    public void showMainView() {
//        mainView.setVisible(true);
//    }
//
//    public UserStockView getView() {
//        return mainView;
//    }
//
//
////    public void buyStock() {
////        stockController.loadStockData();
////        mainView.setContentPanel(stockController.getView());
////    }
////
////    public void sellStock() {
////        customerController.loadCustomerData();
////        mainView.setContentPanel(customerController.getView());
////    }
//}