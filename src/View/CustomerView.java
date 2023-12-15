package com.stock_test.View;

import com.stock_test.Model.Customer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerView extends TransPanel {
    private JList<Customer> list;
    private DefaultListModel<Customer> listModel;
    private JPanel buttonPanel;
    private JButton approveButton;
    private JButton rejectButton;
    private JButton infoButton;

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
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(infoButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public void setListData(List<Customer> customers) {
        list.setListData(customers.toArray(new Customer[0]));
    }

    public Customer getSelectedCustomer() {
        return list.getSelectedValue();
    }

    public void setApproveButtonListener(ActionListener listener) {
        approveButton.addActionListener(listener);
        list.setSelectedIndex(0);
    }

    public void setRejectButtonListener(ActionListener listener) {
        rejectButton.addActionListener(listener);
    }

    public void setInfoButtonListener(ActionListener listener) {
        infoButton.addActionListener(listener);
    }

    private void selectListener(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            updateButtonPanel();
        }
    }

    private void updateButtonPanel() {
        buttonPanel.removeAll();

        if (getSelectedCustomer() != null) {
            if (getSelectedCustomer().getIsPending()) {
                buttonPanel.add(approveButton);
                buttonPanel.add(rejectButton);
            } else {
                buttonPanel.add(infoButton);
            }
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}