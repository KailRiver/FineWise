package org.example;
import java.util.Map;

public class ValidationService {

    // Проверка корректности суммы транзакции
    public boolean validateAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма должна быть положительным числом.");
            return false;
        }
        return true;
    }

    // Проверка существования категории
    public boolean validateCategory(String category, Map<String, Category> categories) {
        if (!categories.containsKey(category)) {
            System.out.println("Ошибка: Категория '" + category + "' не найдена.");
            return false;
        }
        return true;
    }

    // Проверка корректности логина и пароля
    public boolean validateLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            System.out.println("Ошибка: Логин не может быть пустым.");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Ошибка: Пароль не может быть пустым.");
            return false;
        }
        return true;
    }
}