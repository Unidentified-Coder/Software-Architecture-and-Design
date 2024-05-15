public interface Market {
    void displayAvailableStocks();
    boolean buyStock(String symbol, int quantity, User user);
    boolean sellStock(String symbol, int quantity, User user);
}
