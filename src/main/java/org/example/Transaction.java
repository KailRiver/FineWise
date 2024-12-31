package org.example;

import java.time.LocalDate;

public class Transaction {
    private String type; // "income" или "expense"
    private String category;
    private double amount;
    private LocalDate date;

    // Пустой конструктор для Jackson
    public Transaction() {}

    public Transaction(String type, String category, double amount) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = LocalDate.now(); // Устанавливаем текущую дату
    }

    // Геттеры и сеттеры
    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}