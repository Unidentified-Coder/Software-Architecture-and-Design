import java.util.HashMap;
import java.util.Map;

public class StockExchange {
    private Map<String, Stock> stocksAvailable;

    public StockExchange() {
        stocksAvailable = new HashMap<>();
        // Initialize some stocks
        stocksAvailable.put("AAPL", new Stock("AAPL", 150.00));
        stocksAvailable.put("GOOGL", new Stock("GOOGL", 120.00));
        stocksAvailable.put("MSFT", new Stock("MSFT", 100.00));
    }

    public boolean buyStock(String symbol, User user) {
        Stock stock = stocksAvailable.get(symbol);
        if (stock != null && user.getBalance() >= stock.getPrice()) {
            user.setBalance(user.getBalance() - stock.getPrice());
            user.addStock(new Stock(stock.getSymbol(), stock.getPrice()));  // Ensure separate instances
            System.out.println("Transaction successful: " + user.getName() + " bought " + stock.getSymbol() + " at $" + stock.getPrice());
            return true;
        } else {
            System.out.println("Transaction failed: Insufficient funds or stock not found.");
            return false;
        }
    }

    public boolean sellStock(String symbol, User user) {
        Stock stock = user.getOwnedStocks().stream()
                         .filter(s -> s.getSymbol().equals(symbol))
                         .findFirst()
                         .orElse(null);

        if (stock != null && stocksAvailable.containsKey(symbol)) {
            user.setBalance(user.getBalance() + stock.getPrice());
            user.removeStock(stock);
            System.out.println("Transaction successful: " + user.getName() + " sold " + stock.getSymbol() + " at $" + stock.getPrice());
            return true;
        } else {
            System.out.println("Transaction failed: Stock not owned or not available.");
            return false;
        }
    }

    public void updateStockPrice(String symbol, double newPrice) {
        Stock stock = stocksAvailable.get(symbol);
        if (stock != null) {
            stock.setPrice(newPrice);
            System.out.println("Updated " + symbol + " stock price to $" + newPrice);
        }
    }
}
