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

    public void setAmount(int am) {amount = am;}

    public void addAmount(int am, double price){
        averagePrice = (amount * averagePrice + price * am) / (amount + am);
        amount += am;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public double getProfit() {
        double profit = ((double)stock.getPrice() - averagePrice) * amount;
        return profit;
    }
}
