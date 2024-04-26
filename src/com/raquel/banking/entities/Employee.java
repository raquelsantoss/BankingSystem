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

    @Override
    public void run() {
        double salary = 1400.0;
        // Synchronized block to ensure thread safety
        synchronized (store.getAccount().getBank()) {
            if (store.getAccount().getBalance() >= salary) {
                store.getAccount().withdraw(salary);
                getSalaryAccount().deposit(salary);
                // Calculating the investment amount (20% of the salary)
                double investmentAmount = salary * 0.2;
                getInvestmentAccount().deposit(investmentAmount);
                // Calculating the final balance of the salary account after investment
                double finalSalaryBalance = getSalaryAccount().getBalance() - investmentAmount;
                // Printing the transaction details
                System.out.println(name + " recebeu R$" + salary + " da " + store.getName());
                System.out.println(name + " investiu R$" + investmentAmount);
                System.out.println("saldo do " + name + " após investimento: R$" + finalSalaryBalance);
            }
        }
    }

    // Setter for store
    public void setStore(Store store) {
        this.store = store;
    }

    // Getter for salary account
    public Account getSalaryAccount() {
        return salaryAccount;
    }

    // Getter for investment account
    public Account getInvestmentAccount() {
        return investmentAccount;
    }
}
