package com.raquel.banking.entities;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Store> stores;

    public Bank() {
        this.stores = new ArrayList<>();
    }

    public synchronized void transfer(Customer customer, double amount, Store store) {
        customer.getAccount().withdraw(amount);
        store.getAccount().deposit(amount);
        System.out.println(customer.getName() + " comprou em " + store.getName() + ": " + "R$" + amount);
    }

    public synchronized void payEmployee(Employee employee, double salary) {
        employee.getSalaryAccount().withdraw(salary);
        employee.getInvestmentAccount().deposit(salary * 0.2);
        System.out.println(employee.getName() + " salário recebido: " + "R$" + salary);
    }

    public synchronized void printFinalBalances() {
        for (Store store : stores) {
            System.out.println(store.getName() + " saldo: " + "R$" + store.getAccount().getBalance());
        }
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
