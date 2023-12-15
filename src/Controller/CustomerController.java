package com.stock_test.Controller;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.util.List;

import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.View.CustomerView;

public class CustomerController {
    public interface NotifyCallback {
        public void requestNotify(Customer target);
    }

    private CustomerView customerView;
    private NotifyCallback callback;

    public CustomerController() {
        this(new CustomerView());
    }

    public CustomerController(CustomerView view) {
        this.customerView = view;
        setListeners();
    }

    private void setListeners() {
        customerView.setApproveButtonListener(e -> approveUser());
        customerView.setRejectButtonListener(e -> rejectUser());
        customerView.setInfoButtonListener(e -> infoUser());
    }

    public void setNotifyCallback(NotifyCallback callback) {
        this.callback = callback;
    }

    private void approveUser() {
        Customer selectedCustomer = customerView.getSelectedCustomer();
        selectedCustomer.setIsPending(false);
        CustomerDatabase.updateCustomer(selectedCustomer);
        JOptionPane.showMessageDialog(customerView, "User Approved!", "Approval", JOptionPane.INFORMATION_MESSAGE);
        loadCustomerData();
    }

    private void rejectUser() {
        Customer selectedCustomer = customerView.getSelectedCustomer();
        CustomerDatabase.deleteCustomer(selectedCustomer);
        JOptionPane.showMessageDialog(customerView, "User Rejected!", "Rejection", JOptionPane.INFORMATION_MESSAGE);
        loadCustomerData();
    }

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

    public void loadCustomerData() {
        List<Customer> customers = CustomerDatabase.getCustomers();
        customerView.setListData(customers);
    }

    public CustomerView getCustomerView() {
        return customerView;
    }
}