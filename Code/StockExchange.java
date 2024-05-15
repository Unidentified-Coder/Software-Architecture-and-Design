import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StockExchange implements StockExchangeInterface {
    private Map<String, Stock> stocksAvailable;

    public StockExchange() {
        stocksAvailable = new HashMap<>();
        initializeStocks();
    }

    private void initializeStocks() {
        Stock apple = new Stock("AAPL", 150.00, 0);
        apple.setHistoricalPrices(generateHistoricalPrices());
        stocksAvailable.put("AAPL", apple);

        Stock google = new Stock("GOOGL", 120.00, 0);
        google.setHistoricalPrices(generateHistoricalPrices());
        stocksAvailable.put("GOOGL", google);

        Stock microsoft = new Stock("MSFT", 100.00, 0);
        microsoft.setHistoricalPrices(generateHistoricalPrices());
        stocksAvailable.put("MSFT", microsoft);
    }

    @Override
    public void displayAvailableStocks() {
        System.out.println("Available Stocks:");
        for (String symbol : stocksAvailable.keySet()) {
            Stock stock = stocksAvailable.get(symbol);
            System.out.println(symbol + " - Current Price: $" + stock.getPrice());
            displaySimpleGraph(stock.getHistoricalPrices());
        }
    }

    private void displaySimpleGraph(double[] prices) {
        System.out.println("Historical Prices:");
        for (int i = 0; i < prices.length; i++) {
            int starCount = (int) (prices[i] / 10); // Scale down for simplicity
            System.out.printf("%d: $%.2f - ", i + 1, prices[i]); // Print the period number and price
            for (int j = 0; j < starCount; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    

    @Override
    public Stock getStock(String symbol) {
        return stocksAvailable.get(symbol);
    }

    @Override
    public boolean buyStock(String symbol, int quantity, User user) {
        // Implement buyStock method
        return false;
    }

    @Override
    public boolean sellStock(String symbol, int quantity, User user) {
        // Implement sellStock method
        return false;
    }

    private double[] generateHistoricalPrices() {
        Random rand = new Random();
        double[] prices = new double[10];
        // Generating historical prices for a period of 2 years
        double minPrice = 50.00;
        double maxPrice = 200.00;
        for (int i = 0; i < 10; i++) {
            prices[i] = minPrice + (maxPrice - minPrice) * rand.nextDouble();
        }
        return prices;
    }
}
