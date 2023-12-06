package com.stock_test.Controller;

import com.stock_test.Model.Stock;
import com.stock_test.Model.StockDatabase;
import com.stock_test.View.StockView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class StockController {
    private StockView view;

    public StockController() {
        this(new StockView());
    }

    public StockController(StockView view) {
        this.view = view;
        setListeners();
    }

    public StockView getView() {
        return view;
    }

    public void setListeners() {
        view.setUpdateButtonListener(e -> updateStockData());
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

        view.setTableData(columnNames, data);
    }

    private void updateStockData() {
        DefaultTableModel model = view.getTableModel();
        List<Stock> updatedStocks = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            String symbol = (String) model.getValueAt(i, 0);
            int price = (Integer) model.getValueAt(i, 2);
            updatedStocks.add(new Stock(symbol, null, price));
        }

        StockDatabase.updateStocks(updatedStocks);
    }
}