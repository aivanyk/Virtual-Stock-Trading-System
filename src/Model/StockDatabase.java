package com.stock_test.Model;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

// Class for bringing and modifying the data from stock databse
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
        //Compare the old stocks and new stocks, update the database
        List<Stock> oldStocks = getStocks();
        for (Stock stock : stocks) {
            boolean isExist = false;
            for (Stock oldStock : oldStocks) {
                if (stock.getSymbol().equals(oldStock.getSymbol())) {
                    isExist = true;
                    if (stock.getPrice() != oldStock.getPrice()) {
                        updateStock(stock);
                    }
                    break;
                }
            }
            if (!isExist) {
                addStock(stock);
            }
        }
        for (Stock oldStock : oldStocks) {
            boolean isExist = false;
            for (Stock stock : stocks) {
                if (stock.getSymbol().equals(oldStock.getSymbol())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                removeStock(oldStock);
            }
        }
    }

    public static void updateStock(Stock stock) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "UPDATE stock_system.stock SET price = ? WHERE symbol = ?";

        dbConnector.executeUpdate(query, statement -> {
            try {
                statement.setInt(1, stock.getPrice());
                statement.setString(2, stock.getSymbol());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void addStock(Stock stock) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "INSERT INTO stock_system.stock (symbol, name, price) VALUES (?, ?, ?)";

        dbConnector.executeUpdate(query, statement -> {
            try {
                statement.setString(1, stock.getSymbol());
                statement.setString(2, stock.getName());
                statement.setInt(3, stock.getPrice());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void removeStock(Stock stock) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "DELETE FROM stock_system.stock WHERE symbol = ?";

        dbConnector.executeUpdate(query, statement -> {
            try {
                statement.setString(1, stock.getSymbol());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
