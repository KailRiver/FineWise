package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileStorage {
    private static final String FILE_EXTENSION = ".json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Сохранение данных кошелька пользователя в файл
    public static void saveWalletToFile(User user) {
        String fileName = user.getLogin() + FILE_EXTENSION;
        try (Writer writer = new FileWriter(fileName)) {
            Wallet wallet = user.getWallet();
            String json = gson.toJson(wallet);
            writer.write(json);
            System.out.println("Данные кошелька сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    // Загрузка данных кошелька пользователя из файла
    public static Wallet loadWalletFromFile(String login) {
        String fileName = login + FILE_EXTENSION;
        try (Reader reader = new FileReader(fileName)) {
            Wallet wallet = gson.fromJson(reader, Wallet.class);
            System.out.println("Данные кошелька загружены из файла: " + fileName);
            return wallet;
        } catch (FileNotFoundException e) {
            System.out.println("Файл с данными для пользователя " + login + " не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }
        return new Wallet(); // Возвращаем пустой кошелёк, если файл не найден
    }
}