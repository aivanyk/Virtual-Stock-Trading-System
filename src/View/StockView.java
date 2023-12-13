package com.stock_test.View;

import com.stock_test.Util.StockTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public class StockView extends TransPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton updateButton;

    public StockView() {
        super();
        this.setLayout(new BorderLayout());
        tableModel = new StockTableModel();
        table = new JTable(tableModel);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        updateButton = new JButton("Update");
        this.add(updateButton, BorderLayout.SOUTH);
    }

    public void setTableData(Vector<String> columnNames, Vector<Vector<Object>> data) {
        tableModel.setDataVector(data, columnNames);
    }

    public void setUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
