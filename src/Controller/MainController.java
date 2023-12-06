package com.stock_test.Controller;

import com.stock_test.View.MainView;

public class MainController {
    private MainView mainView;
    private SignupController signupController;
    private LoginController loginController;

    public MainController() {
        this(new MainView());
    }

    public MainController(MainView mainView) {
        this.mainView = mainView;
        this.signupController = new SignupController();
        this.signupController.setFinishListener(() -> {
            mainView.changePanelToLogo();
        });
        this.loginController = new LoginController();
        this.loginController.setFinishListener(() -> {
            mainView.changePanelToLogo();
        });
        setListeners();
    }

    public MainView getView() {
        return mainView;
    }

    private void setListeners() {
        mainView.addSignupButtonListener(e -> showSignupPanel());
        mainView.addLoginButtonListener(e -> showLoginPanel());
    }

    private void showSignupPanel() {
        mainView.changePanel(signupController.getSignupView());
    }

    private void showLoginPanel() {
        mainView.changePanel(loginController.getLoginView());
    }
}
