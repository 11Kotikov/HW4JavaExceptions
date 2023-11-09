package org.example;

import org.example.exceptions.InsufficientFundsException;

public class Account {
    private final double initialBalance;
    private double balance;

    public Account(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным.");
        }
        this.balance = initialBalance;
        this.initialBalance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Введите допустимую сумму пополнения!");
        }
        if (balance + amount > initialBalance) {
            throw new IllegalArgumentException("Превышен лимит пополнения счета!");
        }
        balance += amount;
        System.out.println("Счет пополнен на сумму: " + amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Введите допустимую сумму вывода!");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Нехватает средств! Текущий баланс: " + balance);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
