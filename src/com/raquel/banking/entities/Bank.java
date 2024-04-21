package com.raquel.banking.entities;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Store> stores;

    public Bank() {
        this.stores = new ArrayList<>();
    }

    public synchronized void transfer(Customer customer,String name, double amount, Store store) {
        customer.getAccount().withdraw(amount);
        store.getAccount().deposit(amount);
        System.out.println(name + " comprou na " + store.getName() + ": " + "R$" + amount);
    }

    public synchronized void payEmployee(Employee employee,String name, double salary) {
        employee.getSalaryAccount().withdraw(salary);
        employee.getInvestmentAccount().deposit(salary * 0.2);

        double finalInvestmentBalance = employee.getInvestmentAccount().getBalance();

        System.out.println(name + " recebeu: R$" + salary);
        System.out.println(name + " investiu R$" + finalInvestmentBalance);
    }


    public synchronized void printFinalBalances() {
        for (Store store : stores) {
            System.out.println("valor arrecadado pela loja "+ store.getName() + ": " + "R$" + store.getAccount().getBalance());
        }
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
