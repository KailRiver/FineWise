package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {
    private double balance; // Текущий баланс
    private List<Transaction> transactions; // Список всех транзакций
    private Map<String, Category> budgets; // Бюджеты по категориям

    public Wallet() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.budgets = new HashMap<>();
    }

    // Добавление транзакции
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        if (transaction.getType().equals("income")) {
            balance += transaction.getAmount();
        } else if (transaction.getType().equals("expense")) {
            balance -= transaction.getAmount();
            // Увеличиваем расходы в соответствующей категории
            if (budgets.containsKey(transaction.getCategory())) {
                budgets.get(transaction.getCategory()).addExpense(transaction.getAmount());
            }
        }
    }

    // Установка бюджета для категории
    public void setBudget(String category, double budget) {
        budgets.put(category, new Category(category, budget));
    }

    // Получение текущего баланса
    public double getBalance() {
        return balance;
    }

    // Получение всех транзакций
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Получение бюджетов
    public Map<String, Category> getBudgets() {
        return budgets;
    }

    // Вывод статистики
    public void showStats() {
        System.out.println("Текущий баланс: " + balance);
        System.out.println("Общий доход: " + calculateTotalIncome());
        System.out.println("Общие расходы: " + calculateTotalExpenses());
        System.out.println("Бюджет по категориям:");
        for (Category category : budgets.values()) {
            System.out.println(category.getName() + ": Бюджет " + category.getBudget() + ", Оставшийся бюджет: " + category.getRemainingBudget());
        }
    }

    // Подсчет общего дохода
    private double calculateTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType().equals("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Подсчет общих расходов
    private double calculateTotalExpenses() {
        return transactions.stream()
                .filter(t -> t.getType().equals("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}