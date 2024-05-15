import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Stock {
    private String symbol;
    private double price;
    private int quantity;
    private double[] historicalPrices;

    public Stock(String symbol, double price, int quantity) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
        this.historicalPrices = new double[0]; // Default to an empty array
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double[] getHistoricalPrices() {
        return historicalPrices;
    }

    public void setHistoricalPrices(double[] historicalPrices) {
        this.historicalPrices = historicalPrices;
    }
}