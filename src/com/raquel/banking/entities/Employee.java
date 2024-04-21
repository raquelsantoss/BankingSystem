package com.raquel.banking.entities;

public class Employee extends Thread{

    private String name;
    private Store store;
    private Account salaryAccount;
    private Account investmentAccount;

    public Employee(String name, Store store) {
        this.name = name;
        this.store = store;
        this.salaryAccount = new Account(store.getAccount().getBank());
        this.investmentAccount = new Account(store.getAccount().getBank());
    }

    public void run() {
        double salary = 1400.0;
        store.getAccount().getBank().payEmployee(this,name, salary);
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
