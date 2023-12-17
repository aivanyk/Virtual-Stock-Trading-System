package com.stock_test.View;

import com.stock_test.Model.Customer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

// Admin customer view
public class CustomerView extends TransPanel {
    private JList<Customer> list;
    private DefaultListModel<Customer> listModel;
    private JPanel buttonPanel;
    private JButton approveButton;
    private JButton rejectButton;
    private JButton infoButton;
    private JButton deleteButton;

    // Constructor
    public CustomerView() {
        super();
        this.setLayout(new BorderLayout());

        listModel = new DefaultListModel<Customer>();
        list = new JList<Customer>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> selectListener(e));
        add(new JScrollPane(list), BorderLayout.CENTER);

        buttonPanel = new JPanel();

        approveButton = new JButton("Approve");
        rejectButton = new JButton("Reject");
        infoButton = new JButton("Show Information");
        deleteButton = new JButton("Delete");
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    // Set the data to display
    public void setListData(List<Customer> customers) {
        list.setListData(customers.toArray(new Customer[0]));
    }

    // Get the customer
    public Customer getSelectedCustomer() {
        return list.getSelectedValue();
    }

    // Set approve button listener
    public void setApproveButtonListener(ActionListener listener) {
        approveButton.addActionListener(listener);
        list.setSelectedIndex(0);
    }

    // Set reject button listener
    public void setRejectButtonListener(ActionListener listener) {
        rejectButton.addActionListener(listener);
    }

    // Set information button listener
    public void setInfoButtonListener(ActionListener listener) {
        infoButton.addActionListener(listener);
    }

    // Set reject button listener
    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    //Set list selection listener
    private void selectListener(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            updateButtonPanel();
        }
    }

    // Update the buttons
    private void updateButtonPanel() {
        buttonPanel.removeAll();

        if (getSelectedCustomer() != null) {
            if (getSelectedCustomer().getIsPending()) {
                buttonPanel.add(approveButton);
                buttonPanel.add(rejectButton);
            } else {
                buttonPanel.add(infoButton);
                buttonPanel.add(deleteButton);
            }
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}