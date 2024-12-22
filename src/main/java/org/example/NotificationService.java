package org.example;
public class NotificationService {

    // Уведомление о превышении бюджета
    public void notifyBudgetExceeded(String category, double budget, double expenses) {
        if (expenses > budget) {
            System.out.println("Внимание: Бюджет категории '" + category + "' превышен на " + (expenses - budget) + ".");
        }
    }

    // Уведомление о превышении расходов над доходами
    public void notifyBalanceNegative(double balance) {
        if (balance < 0) {
            System.out.println("Внимание: Ваш баланс отрицательный (" + balance + "). Пожалуйста, проверьте свои расходы.");
        }
    }
}