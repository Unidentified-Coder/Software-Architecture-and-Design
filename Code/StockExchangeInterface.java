// StockExchange interface
interface StockExchangeInterface {
    void displayAvailableStocks();

    Stock getStock(String symbol);

    boolean buyStock(String symbol, int quantity, User user);

    boolean sellStock(String symbol, int quantity, User user);
}

