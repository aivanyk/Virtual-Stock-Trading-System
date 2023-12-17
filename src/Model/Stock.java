package com.stock_test.Model;

// Data Model for Stock
// It is matched to the schema of corresponding database table
public class Stock {
    private String symbol;
    private String name;
    private int price;

    public Stock(String symbol, String name, int price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}