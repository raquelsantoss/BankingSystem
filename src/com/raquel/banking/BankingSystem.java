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

        Store store1 = new Store("Loja 1", bank);
        Store store2 = new Store("Loja 2", bank);

        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Customer customer = new Customer("Customer " + i, bank, store1, store2);
            customers.add(customer);
        }

        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Employee employee = new Employee("Employee " + i, store1);
            employees.add(employee);
            if (i % 2 == 0) {
                employee.setStore(store2);
            }
        }

        bank.setStores(List.of(store1, store2));

        for (Customer customer : customers) {
            customer.start();
        }

        for (Employee employee : employees) {
            employee.start();
        }

        try {
            for (Customer customer : customers) {
                customer.join();
            }

            for (Employee employee : employees) {
                employee.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bank.printFinalBalances();
    }
}
