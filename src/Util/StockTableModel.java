package com.stock_test.Util;

import javax.swing.table.DefaultTableModel;

// Table model for showing stock. It filters price value to a integer varable
public class StockTableModel extends DefaultTableModel {
    // In Stock Table, price cell is only editable.
//    public boolean isCellEditable(int row, int column) {
//        if (column == 2) return true;
//        else return false;
//    }

    // price value should be converted to integer
    public void setValueAt(Object aValue, int row, int column) {
        if (column == 2) {
            try {
                Integer intValue = Integer.parseInt(aValue.toString());
                super.setValueAt(intValue, row, column);
            } catch (NumberFormatException e) {
                super.setValueAt(getValueAt(row, column), row, column);
            }
        } else {
            super.setValueAt(aValue, row, column);
        }
    }
}
