package com.stock_test.Model;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class StockDatabase {
    public static List<Stock> getStocks() {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "SELECT * FROM stock_system.stock";

        return dbConnector.executeQuery(query, result -> {
            List<Stock> stocks = new ArrayList<>();
            try {
                while (result.next()) {
                    String symbol = result.getString("symbol");
                    String name = result.getString("name");
                    int price = result.getInt("price");
                    stocks.add(new Stock(symbol, name, price));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return stocks;
        });
    }

    public static void updateStocks(List<Stock> stocks) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "UPDATE stock_system.stock SET price = ? WHERE symbol = ?";

        dbConnector.executeUpdate(query, statement -> {
            try {
                for (Stock stock : stocks) {
                    statement.setInt(1, stock.getPrice());
                    statement.setString(2, stock.getSymbol());
                    statement.addBatch();
                }
                statement.executeBatch();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}