package com.stock_test.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}
