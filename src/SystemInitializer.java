package com.stock_test;

import com.stock_test.Controller.MainController;

// Initialize the program
public class SystemInitializer {
    public SystemInitializer() {
        MainController mainController = new MainController();
        mainController.getView().setVisible(true);
    }
}
