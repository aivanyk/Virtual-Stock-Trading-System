package com.stock_test.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;
import java.util.function.Function;

public class DatabaseConnector {
    private static DatabaseConnector instance;
    private String url = "jdbc:mysql://172.174.214.13:34306";
    private String user = "stock_user";
    private String password = "StockPassword!@#$";

    public DatabaseConnector() {}

    public DatabaseConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("[+] Connect Success.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public <T> T executeQuery(String query, Function<ResultSet, T> handler) {
        try (Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query)) {
            return handler.apply(results);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public <T> T executeSingleResultQuery(String query, Function<ResultSet, T> handler) {
        T result = null;

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(query)) {

            if (results.next()) {
                result = handler.apply(results);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public void executeUpdate(String query, Consumer<PreparedStatement> statementSetter) {
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(query)) {            
            statementSetter.accept(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}