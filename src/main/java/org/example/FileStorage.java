package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class FileStorage {
    private static final String FILE_EXTENSION = ".json";
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()); // Поддержка Java 8+ типов данных

    // Сохранение данных пользователя в файл
    public static void saveUserToFile(User user) {
        String fileName = user.getLogin() + FILE_EXTENSION;
        try {
            mapper.writeValue(new File(fileName), user);
            System.out.println("Данные пользователя сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    // Загрузка данных пользователя из файла
    public static User loadUserFromFile(String login) {
        String fileName = login + FILE_EXTENSION;
        try {
            User user = mapper.readValue(new File(fileName), User.class);
            System.out.println("Данные пользователя загружены из файла: " + fileName);
            return user;
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
            return null; // Возвращаем null, если файл не найден или произошла ошибка
        }
    }
}