package com.example.model;

import com.example.exception.InsufficientBalanceException;

public class Balance {
    private double amount;
    private int userId;

    public Balance(int userId) {
        this.userId = userId;
        this.amount = 0.0;
    }

    public Balance(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // увелечение баланса
    public void increaseAmount(double value) {
        this.amount += value;
    }

    // уменьшение баланса
    public void decreaseAmount(double value) {
        if (this.amount >= value) {
            this.amount -= value;
        } else {
            throw new InsufficientBalanceException();
        }
    }

    @Override
    public String toString() {
        return "Balance{" +
                "amount=" + amount +
                ", userId=" + userId +
                '}';
    }

}
