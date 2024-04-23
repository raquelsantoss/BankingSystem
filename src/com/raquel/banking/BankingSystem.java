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

        for (Customer customer : customers) {
            try {
                customer.join();
                synchronized (monitor) {
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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
