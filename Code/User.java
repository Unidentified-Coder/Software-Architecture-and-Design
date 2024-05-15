import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User implements UserInterface, MoneyManagement {
    private String name;
    private double balance;
    private List<String> transactionHistory;
    private Map<String, Stock> ownedStocks;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        this.ownedStocks = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addToTransactionHistory("Deposited $" + amount);
            return "Successfully deposited $" + amount;
        } else {
            return "Invalid amount. Please enter a positive number.";
        }
    }

    @Override
    public String withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            addToTransactionHistory("Withdrawn $" + amount);
            return "Successfully withdrew $" + amount;
        } else if (amount <= 0) {
            return "Invalid amount. Please enter a positive number.";
        } else {
            return "Insufficient funds.";
        }
    }

    @Override
    public List<Stock> getOwnedStocks() {
        return new ArrayList<>(ownedStocks.values());
    }

    // Implementing viewTransactionHistory method
    public List<String> viewTransactionHistory() {
        return transactionHistory;
    }

    private void addToTransactionHistory(String transaction) {
        transactionHistory.add(transaction);
    }

    public int getStockQuantity(String symbol) {
        Stock stock = ownedStocks.get(symbol);
        return (stock != null) ? stock.getQuantity() : 0;
    }

    public void addStock(Stock stock, int quantity) {
        if (ownedStocks.containsKey(stock.getSymbol())) {
            Stock existingStock = ownedStocks.get(stock.getSymbol());
            existingStock.setQuantity(existingStock.getQuantity() + quantity);
        } else {
            ownedStocks.put(stock.getSymbol(), new Stock(stock.getSymbol(), stock.getPrice(), quantity));
        }
    }

    public void removeStock(String symbol, int quantity) {
        Stock existingStock = ownedStocks.get(symbol);
        if (existingStock != null) {
            if (existingStock.getQuantity() <= quantity) {
                ownedStocks.remove(symbol);
            } else {
                existingStock.setQuantity(existingStock.getQuantity() - quantity);
            }
        }
    }
}