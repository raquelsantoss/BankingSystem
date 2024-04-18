package com.raquel.banking.entities;

public class Store {

    private String name;
    private Account account;

    public Store(String name, Bank bank) {
        this.name = name;
        this.account = new Account(bank);
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }
}
