package com.stock_test.Controller;

import java.util.Vector;

import com.stock_test.Model.Customer;
import com.stock_test.Model.CustomerDatabase;
import com.stock_test.View.CustomerView;

public class CustomerController {
    private CustomerView view;

    public CustomerController() {
        this.view = new CustomerView();
    }

    public CustomerController(CustomerView view) {
        this.view = view;
    }

    public void loadCustomerData() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("Name");
        columnNames.add("Email");
        columnNames.add("Phone");

        Vector<Vector<Object>> data = new Vector<>();
        for (Customer customer : CustomerDatabase.getCustomers()) {
            Vector<Object> row = new Vector<>();
            row.add(customer.getId());
            row.add(customer.getName());
            row.add(customer.getEmail());
            row.add(customer.getPhone());
            data.add(row);
        }

        view.setTableData(columnNames, data);
    }

    public CustomerView getView() {
        return view;
    }
}