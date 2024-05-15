import java.util.List;

public interface StockSystem {
    void displayAvailableStocks();
    Stock getStock(String symbol);
    boolean buyStock(String symbol, int quantity, User user);
    boolean sellStock(String symbol, int quantity, User user);
}
