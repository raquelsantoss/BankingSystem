package com.raquel.banking.entities;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Store> stores;

    public Bank() {
        this.stores = new ArrayList<>();
    }

    public synchronized void transfer(Customer customer, String name, double amount, Store store) {
        customer.getAccount().withdraw(amount); // Withdraws the amount from the customer's account
        store.getAccount().deposit(amount); // Deposits the amount into the store's account
        System.out.println(name + " comprou na " + store.getName() + ": " + "R$" + amount); // Prints the purchase information
    }

    public synchronized void printFinalBalances() {
        for (Store store : stores) {
            System.out.println("Saldo da loja "+ store.getName() +  " com desconto do salário do(s) funcionário(s)" + ": " + "R$" + store.getAccount().getBalance());
        }
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
