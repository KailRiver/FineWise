package org.example;

public class Category {
    private String name;
    private double budget;
    private double expenses;

    // Пустой конструктор для Jackson
    public Category() {}

    public Category(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.expenses = 0;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    // Оставшийся бюджет
    public double getRemainingBudget() {
        return budget - expenses;
    }
}