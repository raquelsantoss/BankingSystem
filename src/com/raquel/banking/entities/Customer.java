package com.raquel.banking.entities;

import java.util.List;
import java.util.Random;

public class Customer extends Thread{

    private String name;
    private Account account;
    private List<Store> stores;
    private Object monitor;

    public Customer(String name, Bank bank, Store store1, Store store2, Object monitor) {
        this.name = name;
        this.account = new Account(bank, 1000.0);
        this.stores = List.of(store1, store2);
        this.monitor = monitor;
    }

    public void run() {
        Random random = new Random();
        while (account.getBalance() > 0) {
            synchronized (monitor) {
                Store store = stores.get(random.nextInt(stores.size()));
                double amount = random.nextBoolean() ? 100.0 : 200.0;
                if (account.getBalance() >= amount) {
                    store.getAccount().getBank().transfer(this, name, amount, store);
                }
            }
        }
    }

    public Account getAccount() {
        return account;
    }
}
