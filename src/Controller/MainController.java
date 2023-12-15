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
        this.signupController.setCancelListener(e -> {
            cancel();
        });
        this.loginController = new LoginController();
        this.loginController.setCancelListener(e -> {
            cancel();
        });
//        setListeners();
        setTabPanel();
        setListener();
        BgColorController.getInstance().registerFrame(mainView);
        mainView.addColorPanel();
    }

    public MainView getView() {
        return mainView;
    }

    public void cancel(){
        mainView.changePanelToLogo();
    }

    private void setListener() {
        mainView.setLogoListener(e -> mainView.changePanelToLogin(), e -> mainView.changePanelToSignup());

    }

    private void setTabPanel(){
        mainView.setTab(loginController.getLoginView(), signupController.getSignupView());
    }

}
