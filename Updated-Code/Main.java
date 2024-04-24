public class Main {
    public static void main(String[] args) {
        User user = new User("John Doe", 500.00);
        StockExchange exchange = new StockExchange();

        exchange.buyStock("AAPL", user);
        exchange.buyStock("GOOGL", user);

        System.out.println("User Balance: $" + user.getBalance());
        for (Stock stock : user.getOwnedStocks()) {
            System.out.println("Owned Stock: " + stock.getSymbol() + " at $" + stock.getPrice());
        }
    }
}
