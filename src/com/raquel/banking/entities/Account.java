package com.raquel.banking.entities;

public class Account {
    private Bank bank;
    private double balance;

    public Account(Bank bank) {
        this.bank = bank;
        this.balance = 0.0;
    }

    public Account(Bank bank, double balance) {
        this.bank = bank;
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }
}
