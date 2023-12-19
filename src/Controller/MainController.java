package com.stock_test.Controller;

import com.stock_test.View.MainView;

// Controller managing the main view and related functionalities
public class MainController {
    private MainView mainView;
    private SignupController signupController;
    private LoginController loginController;

    // Constructor
    public MainController() {
        this(new MainView());
    }

    public MainController(MainView mainView) {
        this.mainView = mainView;
        this.signupController = new SignupController();
        this.signupController.setCancelListener(e -> {
            cancel();
        });
        this.loginController = new LoginController();
        this.loginController.setCancelListener(e -> {
            cancel();
        });
        setTabPanel();
        setListener();
        BgColorController.getInstance().registerFrame(mainView);
        mainView.addColorPanel();
    }

    // Get the main view
    public MainView getView() {
        return mainView;
    }

    // Action for the cancel button of login and signup
    public void cancel(){
        mainView.changePanelToLogo();
    }

    // Method to set logo listeners for switching between login and signup panels
    private void setListener() {
        mainView.setLogoListener(e -> mainView.changePanelToLogin(), e -> mainView.changePanelToSignup());
    }

    // Method to set the tab panel for login and signup views
    private void setTabPanel(){
        mainView.setTab(loginController.getLoginView(), signupController.getSignupView());
    }
}
