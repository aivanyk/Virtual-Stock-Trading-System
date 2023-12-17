package com.stock_test.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Class for bringing and modifying the data from customer databse
public class CustomerDatabase {
    public static Customer getCustomer(String email) {
        String sql = "SELECT * FROM stock_system.customer WHERE email = '" + email + "'";
        return DatabaseConnector.getInstance().executeSingleResultQuery(sql, rs -> {
            try {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("password"),
                    email,
                    rs.getString("phone"),
                    rs.getBoolean("is_manager"),
                    rs.getBoolean("is_pending"),
                    rs.getDouble("account_balance"),
                    rs.getDouble("realized_profit")
                );
            } catch (SQLException e) {
                System.out.println("Error creating customer: " + e.getMessage());
                return null;
            }
        });
    }

    public static List<Customer> getCustomers() {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "SELECT * FROM stock_system.customer WHERE is_manager=0";
        return dbConnector.executeQuery(query, rs -> {
            List<Customer> customers = new ArrayList<>();
            try {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    boolean isManager = rs.getBoolean("is_manager");
                    boolean isPending = rs.getBoolean("is_pending");
                    double accountBalance = rs.getDouble("account_balance");
                    double realizedProfit = rs.getDouble("realized_profit");
                    customers.add(new Customer(id, name, password, email, phone, isManager, isPending, accountBalance, realizedProfit));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return customers;
        });
    }

    // TODO: Separate logic to DatabaseConnector
    public static void addCustomer(Customer customer) {
        String sql = "INSERT INTO stock_system.customer (name, password, email, phone, is_manager, is_pending, account_balance, realized_profit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnector.getInstance().connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhone());
            pstmt.setBoolean(5, customer.getIsManager());
            pstmt.setBoolean(6, customer.getIsPending());
            pstmt.setDouble(7, customer.getAccountBalance());
            pstmt.setDouble(8, customer.getRealizedProfit());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    // Can update only pending state, balance, profit
    public static void updateCustomer(Customer customer) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        String query = "UPDATE stock_system.customer SET is_pending = ?, account_balance = ?, realized_profit = ? WHERE id = ?";

        dbConnector.executeUpdate(query, statement -> {
            try {
                statement.setBoolean(1, customer.getIsPending());
                statement.setDouble(2, customer.getAccountBalance());
                statement.setDouble(3, customer.getRealizedProfit());
                statement.setInt(4, customer.getId());
                statement.addBatch();
                statement.executeBatch();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void deleteCustomer(Customer customer) {
        String query = "DELETE FROM stock_system.customer WHERE id = ?";

        DatabaseConnector.getInstance().executeUpdate(query, statement -> {
            try {
                statement.setInt(1, customer.getId());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
