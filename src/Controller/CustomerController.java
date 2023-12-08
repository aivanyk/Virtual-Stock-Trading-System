package com.stock_test.Controller;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

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
        view.setInfoButtonListener(e -> infoUser());
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

    private void infoUser() {
        Customer selectedCustomer = view.getSelectedCustomer();
        UserInfoController userInfoController = new UserInfoController(true);
        userInfoController.setCustomerData(selectedCustomer);

        JFrame frame = new JFrame("User Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.add(userInfoController.getView());
        frame.setVisible(true);
    }

    public void loadCustomerData() {
        List<Customer> customers = CustomerDatabase.getCustomers();
        view.setListData(customers);
    }

    public CustomerView getView() {
        return view;
    }
}