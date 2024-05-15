import java.util.ArrayList;
import java.util.List;

class Wallet implements AccountOverview, SharedFinances {
    private double balance;
    private List<String> transactionHistory;

    public Wallet(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addToHistory("Deposit: $" + amount);
            return "Successfully deposited $" + amount;
        } else {
            return "Invalid amount. Please enter a positive number.";
        }
    }

    public String withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            addToHistory("Withdrawal: $" + amount);
            return "Successfully withdrew $" + amount;
        } else if (amount <= 0) {
            return "Invalid amount. Please enter a positive number.";
        } else {
            return "Insufficient funds.";
        }
    }

    @Override
    public List<String> viewTransactionHistory() {
        return transactionHistory;
    }

    @Override
    public String viewBalance() {
        return "Current balance: $" + balance;
    }

    private void addToHistory(String transaction) {
        transactionHistory.add(transaction);
    }
}