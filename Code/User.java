import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class User {
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addToTransactionHistory("Deposited $" + amount);
            return "Successfully deposited $" + amount;
        } else {
            return "Invalid amount. Please enter a positive number.";
        }
    }

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

    public String purchaseStock(Stock stock, int quantity) {
        double totalPrice = stock.getPrice() * quantity;
        if (totalPrice > this.balance) {
            return "Insufficient funds to purchase " + quantity + " shares of " + stock.getSymbol();
        } else {
            this.balance -= totalPrice;
            addStock(stock, quantity); // Access private method via public entry point
            addToTransactionHistory("Purchased " + quantity + " shares of " + stock.getSymbol() + " at $" + stock.getPrice() + " each");
            return "Successfully purchased " + quantity + " shares of " + stock.getSymbol();
        }
    }

    public String sellStock(String symbol, int quantity) {
        Stock ownedStock = ownedStocks.get(symbol);
        if (ownedStock == null || ownedStock.getQuantity() < quantity) {
            return "You do not own enough shares of " + symbol + " to sell.";
        } else {
            double totalPrice = ownedStock.getPrice() * quantity;
            this.balance += totalPrice;
            removeStock(symbol, quantity); // Access private method via public entry point
            addToTransactionHistory("Sold " + quantity + " shares of " + symbol + " at $" + ownedStock.getPrice() + " each");
            return "Successfully sold " + quantity + " shares of " + symbol;
        }
    }

    public List<String> viewTransactionHistory() {
        return transactionHistory;
    }

    private void addStock(Stock stock, int quantity) {
        if (ownedStocks.containsKey(stock.getSymbol())) {
            Stock existingStock = ownedStocks.get(stock.getSymbol());
            existingStock.setQuantity(existingStock.getQuantity() + quantity);
        } else {
            ownedStocks.put(stock.getSymbol(), new Stock(stock.getSymbol(), stock.getPrice(), quantity));
        }
    }

    private void removeStock(String symbol, int quantity) {
        Stock existingStock = ownedStocks.get(symbol);
        if (existingStock != null) {
            if (existingStock.getQuantity() <= quantity) {
                ownedStocks.remove(symbol);
            } else {
                existingStock.setQuantity(existingStock.getQuantity() - quantity);
            }
        }
    }

    private void addToTransactionHistory(String transaction) {
        transactionHistory.add(transaction);
    }

    public List<Stock> getOwnedStocks() {
        return new ArrayList<>(ownedStocks.values());
    }

    public int getStockQuantity(String symbol) {
        Stock stock = ownedStocks.get(symbol);
        return (stock != null) ? stock.getQuantity() : 0;
    }
}
