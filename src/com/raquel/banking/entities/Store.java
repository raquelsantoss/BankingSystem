package com.raquel.banking.entities;

public class Store {

    private String name;
    private Account account;
    private Object monitor;

    public Store(String name, Bank bank, Object monitor) {
        this.name = name;
        this.account = new Account(bank);
        this.monitor = monitor;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }
}
