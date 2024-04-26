package com.raquel.banking;

import com.raquel.banking.entities.Bank;
import com.raquel.banking.entities.Customer;
import com.raquel.banking.entities.Employee;
import com.raquel.banking.entities.Store;

import java.util.ArrayList;
import java.util.List;

public class BankingSystem {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Object monitor = new Object();

        Store store1 = new Store("Loja A", bank, monitor);
        Store store2 = new Store("Loja B", bank, monitor);

        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Customer customer = new Customer("Cliente " + i, bank, store1, store2, monitor);
            customers.add(customer);
        }

        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Employee employee = new Employee("Funcionário " + i, store1, monitor);
            employees.add(employee);
            if (i % 2 == 0) {
                employee.setStore(store2);
            }
        }

        bank.setStores(List.of(store1, store2));

        for (Customer customer : customers) {
            customer.start();
        }

        for (Customer customer : customers) { // Iterates over each customer thread
            try {
                customer.join(); // Waits for the customer thread to finish
                synchronized (monitor) { // Synchronizes access to the monitor
                    monitor.notifyAll(); // Notifies all threads waiting on the monitor
                }
            } catch (InterruptedException e) { // Catches InterruptedException if the thread is interrupted while waiting
                e.printStackTrace(); // Prints the stack trace of the exception
            }
        }

        for (Employee employee : employees) {
            employee.start();
        }

        for (Employee employee : employees) {
            try {
                employee.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        bank.printFinalBalances();
    }
}
