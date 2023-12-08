package com.stock_test.Controller;

import javax.swing.JOptionPane;

import java.util.List;

import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.View.CustomerView;

public class CustomerController {
    private CustomerView view;

    public CustomerController() {
        this(new CustomerView());
    }

    public CustomerController(CustomerView view) {
        this.view = view;
        setListeners();
    }

    private void setListeners() {
        view.setApproveButtonListener(e -> approveUser());
        view.setRejectButtonListener(e -> rejectUser());
    }

    private void approveUser() {
        Customer selectedCustomer = view.getSelectedCustomer();
        selectedCustomer.setIsPending(false);
        CustomerDatabase.updateCustomer(selectedCustomer);
        JOptionPane.showMessageDialog(view, "User Approved!", "Approval", JOptionPane.INFORMATION_MESSAGE);
        loadCustomerData();
    }

    private void rejectUser() {
        Customer selectedCustomer = view.getSelectedCustomer();
        CustomerDatabase.deleteCustomer(selectedCustomer);
        JOptionPane.showMessageDialog(view, "User Rejected!", "Rejection", JOptionPane.INFORMATION_MESSAGE);
        loadCustomerData();
    }

    public void loadCustomerData() {
        List<Customer> customers = CustomerDatabase.getCustomers();
        view.setListData(customers);
    }

    public CustomerView getView() {
        return view;
    }
}