import java.util.HashMap;
import java.util.Map;

public class StockExchange {
    private Map<String, Stock> stocksAvailable;

    public StockExchange() {
        stocksAvailable = new HashMap<>();
        // Initialize some stocks with current prices and historical prices
        Stock apple = new Stock("AAPL", 250.00, 0);
        apple.setHistoricalPrices(new double[]{210.00, 120.00}); // Example historical prices for AAPL
        stocksAvailable.put("AAPL", apple);

        Stock google = new Stock("GOOGL", 220.00, 0);
        google.setHistoricalPrices(new double[]{170.00, 100.00}); // Example historical prices for GOOGL
        stocksAvailable.put("GOOGL", google);

        Stock microsoft = new Stock("MSFT", 200.00, 0);
        microsoft.setHistoricalPrices(new double[]{150.00, 80.00}); // Example historical prices for MSFT
        stocksAvailable.put("MSFT", microsoft);
    }

    public void displayAvailableStocks() {
        System.out.println("Available Stocks:");
        for (String symbol : stocksAvailable.keySet()) {
            Stock stock = stocksAvailable.get(symbol);
            System.out.println(symbol + " - Current Price: $" + stock.getPrice());
            System.out.println("   Price 6 months ago: $" + stock.getHistoricalPrices()[0]);
            System.out.println("   Price 1 year ago: $" + stock.getHistoricalPrices()[1]);
        }
    }

    public Stock getStock(String symbol) {
        return stocksAvailable.get(symbol);
    }

    private double[] getHistoricalPrices(String symbol) {
        // In a real application, this method would fetch historical prices from a database or external API
        // For simplicity, I'll just generate some random historical prices
        double currentPrice = stocksAvailable.get(symbol).getPrice();
        double sixMonthsAgoPrice = currentPrice * 0.9; // Assuming 10% decrease in 6 months
        double oneYearAgoPrice = currentPrice * 0.8; // Assuming 20% decrease in 1 year
        return new double[] { sixMonthsAgoPrice, oneYearAgoPrice };
    }
    public boolean buyStock(String symbol, int quantity, User user) {
        Stock stock = stocksAvailable.get(symbol);
        if (stock != null && user.getBalance() >= stock.getPrice() * quantity) {
            double totalPrice = stock.getPrice() * quantity;
            user.setBalance(user.getBalance() - totalPrice);
            user.addStock(stock, quantity);  // Utilize public method from User class
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
    public void displayPriceHistory(String symbol) {
        Stock stock = stocksAvailable.get(symbol);
        if (stock != null) {
            double[] prices = stock.getHistoricalPrices();
            System.out.println("Price History for " + symbol + ":");
            System.out.println("1 Year Ago: $" + prices[1]);
            System.out.println("6 Months Ago: $" + prices[0]);
            System.out.println("Current Price: $" + stock.getPrice());
            System.out.println("Graph:");
            for (double price : prices) {
                System.out.print(drawGraph(price, stock.getPrice()) + "  $" + price + "\n");
            }
            System.out.print(drawGraph(stock.getPrice(), stock.getPrice()) + "  $" + stock.getPrice() + "\n");
        } else {
            System.out.println("Stock symbol '" + symbol + "' not found.");
        }
    }

    private String drawGraph(double historicalPrice, double currentPrice) {
        StringBuilder graph = new StringBuilder();
        double priceDifference = currentPrice - historicalPrice;
        int numStars = (int) (priceDifference / 2); // Each $2 difference represented by a star
        for (int i = 0; i < numStars; i++) {
            graph.append("*");
        }
        return graph.toString();
    }
}