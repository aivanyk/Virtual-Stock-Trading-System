package com.stock_test.Controller;

import javax.swing.JOptionPane;

import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.View.LoginView;

public class LoginController {
    private LoginView loginView;
    private FinishListener finishListener;
    private AdminMainController adminMainController;

    public LoginController() {
        this.loginView = new LoginView();
        this.adminMainController = new AdminMainController();
        setListeners();
    }

    public void setListeners() {
        this.loginView.addLoginButtonListener(e -> login());
        this.loginView.addCancelButtonListener(e -> cancel());
    }

    public interface FinishListener {
        void finish();
    }

    public void setFinishListener(FinishListener listener) {
        this.finishListener = listener;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    private void cancel() {
        finishListener.finish();
    }

    private void login() {
        String email = loginView.getEmail();
        String password = loginView.getPassword();

        Customer authenticatedUser = authenticateUser(email, password);
        if (authenticatedUser != null) {
            if (authenticatedUser.getIsPending()) {
                JOptionPane.showMessageDialog(loginView, "Need administrator approval.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (authenticatedUser.getIsManager()) {
                showAdminLogin();
            } else {
                showUserLogin(authenticatedUser);
            }
            
//            finishListener.finish();
        } else {
            JOptionPane.showMessageDialog(loginView, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showAdminLogin() {
        adminMainController.showMainView();
    }

    public void showUserLogin(Customer authenticatedUser) {
        UserMainController userMainController = new UserMainController(authenticatedUser);
        adminMainController.registerAdminObserver(userMainController);
        userMainController.showMainView();
    }

    private Customer authenticateUser(String email, String password) {
        Customer customer = CustomerDatabase.getCustomer(email);
        if (customer != null) {
            if (customer.getPassword().equals(password)) return customer;
        }
        return null;
    }
}
