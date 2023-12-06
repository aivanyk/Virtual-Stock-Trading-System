package com.stock_test.Model;

public class Customer {
    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private boolean isManager;
    private boolean isPending;
    private double accountBalance;
    private double realizedProfit;

    public Customer(int id, String name, String password, String email, String phone,
                    boolean isManager, boolean isPending, double accountBalance, double realizedProfit) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.isManager = isManager;
        this.isPending = isPending;
        this.accountBalance = accountBalance;
        this.realizedProfit = realizedProfit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean getIsPending() {
        return isPending;
    }

    public void setIsPending(boolean isPending) {
        this.isPending = isPending;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getRealizedProfit() {
        return realizedProfit;
    }

    public void setRealizedProfit(double realizedProfit) {
        this.realizedProfit = realizedProfit;
    }
}
