import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user = new User("John Doe", 1000.00);
        StockExchange exchange = new StockExchange();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Current Balance: $" + user.getBalance());
            System.out.println("1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. Update Stock Price");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next();
                    exchange.buyStock(buySymbol, user);
                    break;
                case 2:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next();
                    exchange.sellStock(sellSymbol, user);
                    break;
                case 3:
                    System.out.print("Enter stock symbol to update: ");
                    String updateSymbol = scanner.next();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    exchange.updateStockPrice(updateSymbol, newPrice);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
