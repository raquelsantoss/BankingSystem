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
        Random random = new Random(); // Creates a Random object to generate random values
        while (account.getBalance() > 0) { // While the account balance is greater than zero
            synchronized (monitor) { // Synchronizes access to the monitor to ensure mutual exclusion
                Store store = stores.get(random.nextInt(stores.size())); // Selects a random store
                double amount = random.nextBoolean() ? 100.0 : 200.0; // Generates a random amount of either 100.0 or 200.0
                if (account.getBalance() >= amount) { // Checks if the balance is sufficient for the purchase
                    store.getAccount().getBank().transfer(this, name, amount, store); // Transfers the amount to the store
                    System.out.println("Saldo atual: R$" + account.getBalance()); // Prints the current balance of the account
                }
            }
        }
    }

    public Account getAccount() {
        return account;
    }
}
