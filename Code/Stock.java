import java.util.Arrays;

public class Stock {
    private String symbol;
    private double price;
    private int quantity;
    private double[] historicalPrices;

    public Stock(String symbol, double price, int quantity) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
        // Initialize historical prices
        this.historicalPrices = new double[2];
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double[] getHistoricalPrices() {
        return historicalPrices;
    }

    public void setHistoricalPrices(double[] historicalPrices) {
        this.historicalPrices = historicalPrices;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + "]";
    }
}

