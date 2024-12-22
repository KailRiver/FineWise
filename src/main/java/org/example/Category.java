package org.example;

public class Category {
    private String name;
    private double budget;
    private double expenses;

    public Category(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.expenses = 0;
    }

    // Метод для добавления расходов в категорию
    public void addExpense(double amount) {
        this.expenses += amount;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getRemainingBudget() {
        return budget - expenses;
    }
}