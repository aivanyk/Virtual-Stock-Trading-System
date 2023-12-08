package com.stock_test.Model;

public class OwnStock {
    private Stock stock;
    private int amount;
    private double averagePrice;

    public OwnStock(Stock stock, int amount, double averagePrice) {
        this.stock = stock;
        this.amount = amount;
        this.averagePrice = averagePrice;
    }

    public Stock getStock() {
        return stock;
    }

    public int getAmount() {
        return amount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }
}
