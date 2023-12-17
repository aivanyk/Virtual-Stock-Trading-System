package com.stock_test.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Class for bringing and modifying the data from own stock databse
public class OwnDatabase {
    public static List<OwnStock> getOwnStocks(int customerId) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "SELECT own.*, stock.* " +
                        "FROM stock_system.own " +
                        "JOIN stock_system.stock ON own.stock_id = stock.id " +
                        "WHERE own.customer_id = " + customerId;
        return dbConnector.executeQuery(query, rs -> {
            List<OwnStock> ownStocks = new ArrayList<>();
            try {
                while (rs.next()) {
                    int stockId = rs.getInt("stock.id");
                    String symbol = rs.getString("symbol");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");

                    Stock stock = new Stock(symbol, name, price);
                    int amount = rs.getInt("amount");
                    double averagePrice = rs.getDouble("average_price");
                    ownStocks.add(new OwnStock(stock, amount, averagePrice));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return ownStocks;
        });
    }

    public static void updateOwnStock(int customerId, OwnStock ownStock) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "UPDATE stock_system.own SET amount = ?, average_price = ? " +
                        "WHERE stock_id = (SELECT id FROM stock_system.stock WHERE symbol = ?) AND customer_id = ?";

        dbConnector.executeUpdate(query, statement -> {
            try {
                statement.setInt(1, ownStock.getAmount());
                statement.setDouble(2, ownStock.getAveragePrice());
                statement.setString(3, ownStock.getStock().getSymbol());
                statement.setInt(4, customerId);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void addOwnStock(int customerId, OwnStock ownStock) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "INSERT INTO stock_system.own (customer_id, stock_id, amount, average_price) " +
                       "VALUES (?, (SELECT id FROM stock_system.stock WHERE symbol = ?), ?, ?)";

        dbConnector.executeUpdate(query, statement -> {
            try {
                statement.setInt(1, customerId);
                statement.setString(2, ownStock.getStock().getSymbol());
                statement.setInt(3, ownStock.getAmount());
                statement.setDouble(4, ownStock.getAveragePrice());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void removeOwnStock(int customerId, OwnStock ownStock) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "DELETE FROM stock_system.own " +
                       "WHERE stock_id = (SELECT id FROM stock_system.stock WHERE symbol = ?) AND customer_id = ?";

        dbConnector.executeUpdate(query, statement -> {
            try {
                statement.setString(1, ownStock.getStock().getSymbol());
                statement.setInt(2, customerId);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
