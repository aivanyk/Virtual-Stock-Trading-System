package com.stock_test.Controller;

import javax.swing.JOptionPane;

import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.View.LoginView;

import java.awt.event.ActionListener;

// Controller handling user login operations
public class LoginController {
    private LoginView loginView;
    private AdminMainController adminMainController;

    // Constructor
    public LoginController() {
        this.loginView = new LoginView();
        this.adminMainController = new AdminMainController();
        this.loginView.addLoginButtonListener(e -> login());
    }

    // Method to set cancel listener to get to the logo page
    public void setCancelListener(ActionListener lis){
        this.loginView.addCancelButtonListener(lis);
    }

    // Method to get the login view
    public LoginView getLoginView() {
        return loginView;
    }

    // Method to handle login process
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
            loginView.reset();
//            finishListener.finish();
        } else {
            JOptionPane.showMessageDialog(loginView, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to show admin login view
    public void showAdminLogin() {
        adminMainController.showMainView();
    }

    // Method to show user login view and register user as an observer for admin actions
    public void showUserLogin(Customer authenticatedUser) {
        UserMainController userMainController = new UserMainController(authenticatedUser);
        adminMainController.registerAdminObserver(userMainController);
        userMainController.showMainView();
    }

    // Method to authenticate user based on email and password
    private Customer authenticateUser(String email, String password) {
        Customer customer = CustomerDatabase.getCustomer(email);
        if (customer != null) {
            if (customer.getPassword().equals(password)) return customer;
        }
        return null;
    }
}
