package org.example;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        FinanceService financeService = new FinanceService(authService);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в приложение для управления личными финансами!");
        System.out.println("Введите команду (help для справки):");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();
            String[] parts = command.split(" ");

            if (parts.length == 0) {
                System.out.println("Неверный формат команды. Попробуйте снова.");
                continue;
            }

            switch (parts[0].toLowerCase()) {
                case "register":
                    if (parts.length != 3) {
                        System.out.println("Неверный формат команды. Используйте: register <login> <password>");
                        break;
                    }
                    authService.registerUser(parts[1], parts[2]);
                    break;

                case "login":
                    if (parts.length != 3) {
                        System.out.println("Неверный формат команды. Используйте: login <login> <password>");
                        break;
                    }
                    authService.loginUser(parts[1], parts[2]);
                    break;

                case "addincome":
                    if (parts.length != 3) {
                        System.out.println("Неверный формат команды. Используйте: addIncome <amount> <category>");
                        break;
                    }
                    try {
                        double amount = Double.parseDouble(parts[1]);
                        financeService.addIncome(amount, parts[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Сумма должна быть числом.");
                    }
                    break;

                case "addexpense":
                    if (parts.length != 3) {
                        System.out.println("Неверный формат команды. Используйте: addExpense <amount> <category>");
                        break;
                    }
                    try {
                        double amount = Double.parseDouble(parts[1]);
                        financeService.addExpense(amount, parts[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Сумма должна быть числом.");
                    }
                    break;

                case "setbudget":
                    if (parts.length != 3) {
                        System.out.println("Неверный формат команды. Используйте: setBudget <category> <budget>");
                        break;
                    }
                    try {
                        double budget = Double.parseDouble(parts[2]);
                        financeService.setBudget(parts[1], budget);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Бюджет должен быть числом.");
                    }
                    break;

                case "transfer":
                    if (parts.length != 3) {
                        System.out.println("Неверный формат команды. Используйте: transfer <toUser> <amount>");
                        break;
                    }
                    try {
                        double amount = Double.parseDouble(parts[2]);
                        financeService.transferMoney(parts[1], amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Сумма должна быть числом.");
                    }
                    break;

                case "showstats":
                    financeService.showStats();
                    break;

                case "logout":
                    authService.logout();
                    break;

                case "exit":
                    System.out.println("Выход из приложения.");
                    return;

                case "help":
                    printHelp();
                    break;

                default:
                    System.out.println("Неизвестная команда. Введите 'help' для справки.");
            }
        }
    }

    // Метод для вывода справки по командам
    private static void printHelp() {
        System.out.println("Доступные команды:");
        System.out.println("register <login> <password> — регистрация нового пользователя.");
        System.out.println("login <login> <password> — вход в систему.");
        System.out.println("addIncome <amount> <category> — добавить доход.");
        System.out.println("addExpense <amount> <category> — добавить расход.");
        System.out.println("setBudget <category> <budget> — установить бюджет для категории.");
        System.out.println("transfer <toUser> <amount> — перевод другому пользователю.");
        System.out.println("showStats — вывод статистики по финансам.");
        System.out.println("logout — выход из текущего аккаунта.");
        System.out.println("exit — выход из приложения.");
        System.out.println("help — вывод справки по командам.");
    }
}