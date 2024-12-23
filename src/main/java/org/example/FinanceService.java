package org.example;

public class FinanceService {
    private AuthService authService;
    private ValidationService validationService;
    private NotificationService notificationService;

    public FinanceService(AuthService authService) {
        this.authService = authService;
        this.validationService = new ValidationService();
        this.notificationService = new NotificationService();
    }

    // Метод для добавления дохода
    public void addIncome(double amount, String category) {
        User currentUser = authService.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Пожалуйста, авторизуйтесь.");
            return;
        }

        // Валидация суммы
        if (!validationService.validateAmount(amount)) {
            return;
        }

        Transaction transaction = new Transaction("income", category, amount); // Убрали "current_date"
        currentUser.getWallet().addTransaction(transaction);
        System.out.println("Доход в размере " + amount + " добавлен в категорию " + category);
    }

    public void addExpense(double amount, String category) {
        User currentUser = authService.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Пожалуйста, авторизуйтесь.");
            return;
        }

        // Валидация суммы
        if (!validationService.validateAmount(amount)) {
            return;
        }

        // Валидация категории
        if (!validationService.validateCategory(category, currentUser.getWallet().getBudgets())) {
            return;
        }

        Transaction transaction = new Transaction("expense", category, amount); // Убрали "current_date"
        currentUser.getWallet().addTransaction(transaction);
        System.out.println("Расход в размере " + amount + " добавлен в категорию " + category);

        // Уведомление о превышении бюджета
        Category cat = currentUser.getWallet().getBudgets().get(category);
        notificationService.notifyBudgetExceeded(category, cat.getBudget(), cat.getExpenses());

        // Уведомление о отрицательном балансе
        notificationService.notifyBalanceNegative(currentUser.getWallet().getBalance());
    }

    public void transferMoney(String toUser, double amount) {
        User currentUser = authService.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Пожалуйста, авторизуйтесь.");
            return;
        }

        // Проверка суммы
        if (!validationService.validateAmount(amount)) {
            return;
        }

        // Проверка существования получателя
        User recipient = authService.getUsers().get(toUser);
        if (recipient == null) {
            System.out.println("Пользователь " + toUser + " не найден.");
            return;
        }

        // Проверка баланса отправителя
        if (currentUser.getWallet().getBalance() < amount) {
            System.out.println("Недостаточно средств для перевода.");
            return;
        }

        // Выполнение перевода
        currentUser.getWallet().addTransaction(new Transaction("expense", "Перевод", amount)); // Убрали "current_date"
        recipient.getWallet().addTransaction(new Transaction("income", "Перевод", amount)); // Убрали "current_date"

        System.out.println("Перевод в размере " + amount + " выполнен пользователю " + toUser);
    }

    // Метод для вывода статистики
    public void showStats() {
        User currentUser = authService.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Пожалуйста, авторизуйтесь.");
            return;
        }

        currentUser.getWallet().showStats();
    }
    public void setBudget(String category, double budget) {
        User currentUser = authService.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Пожалуйста, авторизуйтесь.");
            return;
        }

        // Валидация суммы
        if (!validationService.validateAmount(budget)) {
            return;
        }

        currentUser.getWallet().setBudget(category, budget);
        System.out.println("Бюджет для категории " + category + " установлен в размере " + budget);
    }
}