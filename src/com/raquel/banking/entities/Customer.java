package com.raquel.banking.entities;

import java.util.List;
import java.util.Random;

public class Customer extends Thread{

    private String name;
    private Account account;
    private List<Store> stores;

    public Customer(String name, Bank bank, Store store1, Store store2) {
        this.name = name;
        this.account = new Account(bank, 1000.0);
        this.stores = List.of(store1, store2);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (account.getBalance() > 0) {
            Store store = stores.get(random.nextInt(stores.size()));
            double amount = random.nextBoolean() ? 100.0 : 200.0;
            if (account.getBalance() >= amount) {
                store.getAccount().getBank().transfer(this,name, amount, store);
            }
        }
    }

    public Account getAccount() {
        return account;
    }
}
