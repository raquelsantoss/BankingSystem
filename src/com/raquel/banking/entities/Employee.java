package com.raquel.banking.entities;

public class Employee extends Thread{

    private String name;
    private Store store;
    private Account salaryAccount;
    private Account investmentAccount;
    private Object monitor;

    public Employee(String name, Store store, Object monitor) {
        this.name = name;
        this.store = store;
        this.salaryAccount = new Account(store.getAccount().getBank());
        this.investmentAccount = new Account(store.getAccount().getBank());
        this.monitor = monitor;
    }

    public void run() {
        double salary = 1400.0;
        synchronized (monitor) {
            store.getAccount().getBank().payEmployee(this, name, salary);
        }
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Account getSalaryAccount() {
        return salaryAccount;
    }

    public Account getInvestmentAccount() {
        return investmentAccount;
    }
}
