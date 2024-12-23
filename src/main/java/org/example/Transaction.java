package org.example;

import java.time.LocalDate;

public class Transaction {
    private String type; // "income" или "expense"
    private String category;
    private double amount;
    private LocalDate date;

    public Transaction(String type, String category, double amount) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = LocalDate.now(); // Устанавливаем текущую дату
    }

    // Геттеры
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
}