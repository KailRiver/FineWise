package org.example;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users;
    private User currentUser;

    public AuthService() {
        this.users = new HashMap<>();
        this.currentUser = null;
    }

    public void registerUser(String login, String password) {
        // Проверяем, существует ли файл с данными для пользователя
        File userFile = new File(login + ".json");
        if (userFile.exists()) {
            System.out.println("Пользователь с логином " + login + " уже зарегистрирован.");
            System.out.println("Пожалуйста, войдите в систему с помощью команды 'login'.");
            return;
        }

        // Регистрируем нового пользователя
        User newUser = new User(login, password);
        users.put(login, newUser);
        System.out.println("Пользователь успешно зарегистрирован.");

        // Сохраняем данные пользователя в файл
        FileStorage.saveUserToFile(newUser);
    }

    public boolean loginUser(String login, String password) {
        // Проверяем, существует ли файл с данными для пользователя
        File userFile = new File(login + ".json");
        if (!userFile.exists()) {
            System.out.println("Пользователь с логином " + login + " не зарегистрирован.");
            System.out.println("Пожалуйста, зарегистрируйтесь с помощью команды 'register'.");
            return false;
        }

        // Загружаем данные пользователя из файла
        User user = FileStorage.loadUserFromFile(login);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Неверный логин или пароль.");
            return false;
        }

        // Устанавливаем текущего пользователя
        currentUser = user;
        System.out.println("Пользователь успешно авторизован.");
        return true;
    }

    public void logout() {
        if (currentUser != null) {
            // Сохранение данных кошелька в файл
            FileStorage.saveUserToFile(currentUser);
            currentUser = null;
            System.out.println("Выход выполнен.");
        } else {
            System.out.println("Пользователь не авторизован.");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}