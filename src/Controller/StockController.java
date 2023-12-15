package com.stock_test.Controller;

import com.stock_test.Model.Stock;
import com.stock_test.Model.StockDatabase;
import com.stock_test.View.StockView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StockController {
    private final StockView stockView;

    public StockController() {
        this(new StockView());
    }

    public StockController(StockView stockView) {
        this.stockView = stockView;
        setListeners();
    }

    public StockView getStockView() {
        return stockView;
    }

    public void setListeners() {
        stockView.setModifyButtonListener(e -> modifySeries());
        stockView.setUpdateButtonListener(e -> updateStockData());
        stockView.setAddButtonListener(e -> addNewRow());
        stockView.setRemoveButtonListener(e -> removeRow());
        stockView.setCancelButtonListener(e -> cancelModify());
    }

    //Back to the original state
    private void cancelModify() {
        stockView.getStockTable().setEnabled(false);
        stockView.getUpdateButton().setVisible(false);
        stockView.getAddButton().setVisible(false);
        stockView.getRemoveButton().setVisible(false);
        stockView.getBackButton().setVisible(false);
        stockView.getModifyButton().setVisible(true);
        loadStockData();
    }

    private void removeRow() {
        DefaultTableModel model = (DefaultTableModel) stockView.getStockTable().getModel();
        int selectedRow = stockView.getStockTable().getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        }
    }

    private void addNewRow() {
        DefaultTableModel model = (DefaultTableModel) stockView.getStockTable().getModel();
        //add new row that is editable
        model.addRow(new Object[]{"new symbol", "new name", "new price"});
    }

    private void modifySeries() {
        stockView.getModifyButton().setVisible(false);
        stockView.getStockTable().setEnabled(true);
        stockView.getUpdateButton().setVisible(true);
        stockView.getAddButton().setVisible(true);
        stockView.getRemoveButton().setVisible(true);
        stockView.getBackButton().setVisible(true);
    }

    public void loadStockData() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Symbol");
        columnNames.add("Name");
        columnNames.add("Price");

        Vector<Vector<Object>> data = new Vector<>();
        for (Stock stock : StockDatabase.getStocks()) {
            Vector<Object> row = new Vector<>();
            row.add(stock.getSymbol());
            row.add(stock.getName());
            row.add(stock.getPrice());
            data.add(row);
        }

        stockView.setTableData(columnNames, data);
    }


    private void updateStockData() {
        //Ask for confirmation, if no, return
        int result = JOptionPane.showConfirmDialog(null, "Update is a risky operation, are you sure you want to?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            return;
        }

        DefaultTableModel model = stockView.getTableModel();
        List<Stock> updatedStocks = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String symbol = (String) model.getValueAt(i, 0);
            String name = (String) model.getValueAt(i, 1);
            String priceStr = model.getValueAt(i, 2).toString(); // Get price as a String
            int price = isNumeric(priceStr) ? Integer.parseInt(priceStr) : -1; // Convert to int if numeric, otherwise set to 0

            updatedStocks.add(new Stock(symbol, name, price));
        }

        if (checkEligibility(updatedStocks)) {
            StockDatabase.updateStocks(updatedStocks);
            JOptionPane.showMessageDialog(null, "Update successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean isNumeric(String priceStr) {
        try {
            Integer.parseInt(priceStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkEligibility(List<Stock> updatedStocks) {
        boolean isEligible = true;
        boolean isExist = true;
        boolean isUnique = true;
        boolean isValidPrice = true;
        for (Stock stock : updatedStocks) {
            if (stock.getSymbol().equals("new symbol") || stock.getName().equals("new name") || stock.getPrice() == 0) {
                isExist = false;
                isEligible = false;
            }
            //check if the symbol is unique
            for (Stock stock1 : updatedStocks) {
                if (stock.getSymbol().equals(stock1.getSymbol()) && !stock.equals(stock1)) {
                    isUnique = false;
                    isEligible = false;
                    break;
                }
            }
            //check if the price is a number and is positive
            if (stock.getPrice() < 0) {
                isValidPrice = false;
                isEligible = false;
            }
        }
        if (!isExist) {
            JOptionPane.showMessageDialog(null, "Please fill in the blank!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        if (!isUnique) {
            JOptionPane.showMessageDialog(null, "Symbol should be unique!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (!isValidPrice) {
            JOptionPane.showMessageDialog(null, "Please enter a valid price!(Should be a positive number)", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isEligible;
    }

}