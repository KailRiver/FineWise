package org.example;

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
        if (users.containsKey(login)) {
            System.out.println("Пользователь с таким логином уже существует.");
            return;
        }
        User newUser = new User(login, password);
        users.put(login, newUser);
        System.out.println("Пользователь успешно зарегистрирован.");
    }

    public boolean loginUser(String login, String password) {
        User user = users.get(login);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Неверный логин или пароль.");
            return false;
        }
        currentUser = user;
        System.out.println("Пользователь успешно авторизован.");
        return true;
    }

    public void logout() {
        currentUser = null;
        System.out.println("Выход выполнен.");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}