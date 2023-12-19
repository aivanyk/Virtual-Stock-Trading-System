package com.stock_test.View;

import com.stock_test.Util.StockTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

// Admin stock view
public class StockView extends TransPanel {
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JButton modifyButton;
    private JButton updateButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton backButton;

    // Constructor
    public StockView() {
        super();
        this.setLayout(new BorderLayout());
        tableModel = new StockTableModel();
        stockTable = new JTable(tableModel);
        stockTable.setEnabled(false); // initially disable editing
        this.add(new JScrollPane(stockTable), BorderLayout.CENTER);

        modifyButton = new JButton("Modify");

        // Initialize modification-related buttons (hidden initially)
        updateButton = new JButton("Update");
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        backButton = new JButton("Back");

        // Add modification-related buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modifyButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Hide the buttons initially
        hideModificationButtons();
    }

    // Get the stock table
    public JTable getStockTable() {
        return stockTable;
    }

    // Hide the modification buttons
    private void hideModificationButtons() {
        updateButton.setVisible(false);
        addButton.setVisible(false);
        removeButton.setVisible(false);
        backButton.setVisible(false);
    }

    // Set the data in the table
    public void setTableData(Vector<String> columnNames, Vector<Vector<Object>> data) {
        tableModel.setDataVector(data, columnNames);
    }

    // Setters for the buttons
    public void setModifyButtonListener(ActionListener listener) {
        modifyButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void setAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void setRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    public void setCancelButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    // Getters for the components
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getModifyButton() {
        return modifyButton;
    }
}
