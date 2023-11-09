package org.example;

import org.example.exceptions.InsufficientFundsException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double initialBalance = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Введите лимит пополнения и снятия по карте: ");
                initialBalance = scanner.nextDouble();
                if (initialBalance < 0) {
                    System.out.println("Лимит не может быть отрицательным. Попробуйте еще раз.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Попробуйте еще раз.");
                scanner.next();
            }
        }

        Account client = new Account(initialBalance);

        int menuChoosing;
        do {
            System.out.println("Меню:");
            System.out.println("1. Пополнить счет");
            System.out.println("2. Вывести средства");
            System.out.println("3. Текущий баланс");
            System.out.println("0. Выйти");

            System.out.print("Выберите операцию: ");
            menuChoosing = scanner.nextInt();

            switch (menuChoosing) {
                case 1 -> {
                    System.out.print("Введите сумму пополнения: ");
                    double depositAmount = scanner.nextDouble();
                    client.deposit(depositAmount);
                }
                case 2 -> {
                    System.out.print("Введите сумму вывода средств: ");
                    double withdrawAmount = scanner.nextDouble();
                    try {
                        client.withdraw(withdrawAmount);
                        System.out.println("Остаток на счете: " + client.getBalance());
                    } catch (InsufficientFundsException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Остаток на счете: " + client.getBalance());
                }
                case 0 -> System.out.println("Операции завершены.");
                default -> System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        } while (menuChoosing != 0);

        scanner.close();
    }
}