package com.stock_test.Controller;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.awt.*;
import java.util.List;

import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.View.ColorJFrame;
import com.stock_test.View.CustomerView;

// Controller handling customer-related operations
public class CustomerController {
    public interface NotifyCallback {
        public void requestNotify(Customer target);
    }

    private CustomerView customerView;
    private NotifyCallback callback;

    // Constructor
    public CustomerController() {
        this(new CustomerView());
    }

    public CustomerController(CustomerView view) {
        this.customerView = view;
        setListeners();
    }

    // Set action listeners for buttons
    private void setListeners() {
        customerView.setApproveButtonListener(e -> approveUser());
        customerView.setRejectButtonListener(e -> rejectUser());
        customerView.setInfoButtonListener(e -> infoUser());
    }

    // Set callback for notification
    public void setNotifyCallback(NotifyCallback callback) {
        this.callback = callback;
    }

    // Method to approve a user
    private void approveUser() {
        Customer selectedCustomer = customerView.getSelectedCustomer();
        selectedCustomer.setIsPending(false);
        CustomerDatabase.updateCustomer(selectedCustomer);
        JOptionPane.showMessageDialog(customerView, "User Approved!", "Approval", JOptionPane.INFORMATION_MESSAGE);
        loadCustomerData();
    }

    // Method to reject a user
    private void rejectUser() {
        Customer selectedCustomer = customerView.getSelectedCustomer();
        CustomerDatabase.deleteCustomer(selectedCustomer);
        JOptionPane.showMessageDialog(customerView, "User Rejected!", "Rejection", JOptionPane.INFORMATION_MESSAGE);
        loadCustomerData();
    }

    // Method to view detailed information about a user
    private void infoUser() {
        Customer selectedCustomer = customerView.getSelectedCustomer();
        UserInfoController userInfoController = new UserInfoController(true);
        userInfoController.setCustomerData(selectedCustomer);
        userInfoController.setNotifyListener(e -> callback.requestNotify(selectedCustomer));

        JFrame frame = new JFrame("User Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.add(userInfoController.getView());
        frame.setVisible(true);
    }

    // Load customer data and set it to the view
    public void loadCustomerData() {
        List<Customer> customers = CustomerDatabase.getCustomers();
        customerView.setListData(customers);
    }

    // Get the customer view
    public CustomerView getCustomerView() {
        return customerView;
    }
}