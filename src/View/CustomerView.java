package com.stock_test.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CustomerView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public CustomerView() {
        this.setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setTableData(Vector<String> columnNames, Vector<Vector<Object>> data) {
        tableModel.setDataVector(data, columnNames);
    }
}