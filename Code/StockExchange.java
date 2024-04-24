import java.util.HashMap;
import java.util.Map;

public class StockExchange {
    private Map<String, Stock> stocksAvailable;

    public StockExchange() {
        stocksAvailable = new HashMap<>();
        // Initialize some stocks
        stocksAvailable.put("AAPL", new Stock("AAPL", 150.00, 0));
        stocksAvailable.put("GOOGL", new Stock("GOOGL", 120.00, 0));
        stocksAvailable.put("MSFT", new Stock("MSFT", 100.00, 0));
    }

    public void displayAvailableStocks() {
        System.out.println("Available Stocks:");
        for (String symbol : stocksAvailable.keySet()) {
            Stock stock = stocksAvailable.get(symbol);
            System.out.println(symbol + " - Price: $" + stock.getPrice());
        }
    }

    public Stock getStock(String symbol) {
        return stocksAvailable.get(symbol);
    }

    public boolean buyStock(String symbol, int quantity, User user) {
        Stock stock = stocksAvailable.get(symbol);
        if (stock != null && user.getBalance() >= stock.getPrice() * quantity) {
            double totalPrice = stock.getPrice() * quantity;
            user.setBalance(user.getBalance() - totalPrice);
            user.addStock(stock, quantity); // Utilize public method from User class
            System.out.println("Transaction successful: " + user.getName() + " bought " + quantity + " shares of " + stock.getSymbol() + " at $" + stock.getPrice() + " each");
            return true;
        } else {
            System.out.println("Transaction failed: Insufficient funds or stock not found.");
            return false;
        }
    }

    public boolean sellStock(String symbol, int quantity, User user) {
        Stock stock = user.getOwnedStocks().stream()
                            .filter(s -> s.getSymbol().equals(symbol))
                            .findFirst()
                            .orElse(null);

        if (stock != null && stocksAvailable.containsKey(symbol) && user.getStockQuantity(symbol) >= quantity) {
            double totalPrice = stock.getPrice() * quantity;
            user.setBalance(user.getBalance() + totalPrice);
            user.removeStock(symbol, quantity); // Utilize public method from User class
            System.out.println("Transaction successful: " + user.getName() + " sold " + quantity + " shares of " + symbol + " at $" + stock.getPrice() + " each");
            return true;
        } else {
            System.out.println("Transaction failed: Stock not owned or not available.");
            return false;
        }
    }
}
