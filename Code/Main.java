import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User("John Doe", 1000.00);
        StockExchange exchange = new StockExchange();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome, " + user.getName() + "!");
            System.out.println("Choose an option:");
            System.out.println("1. View Wallet");
            System.out.println("2. Enter Stock Market");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Wallet operations
                    handleWallet(user, scanner);
                    break;
                case 2:
                    // Stock market operations
                    handleStockMarket(user, exchange, scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void handleWallet(User user, Scanner scanner) {
        while (true) {
            System.out.println("\nWallet Menu:");
            System.out.println("Current Balance: $" + user.getBalance());
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. View Purchase History");
            System.out.println("4. Exit Wallet");
    
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    System.out.println(user.deposit(depositAmount));
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    System.out.println(user.withdraw(withdrawAmount));
                    break;
                case 3:
                    System.out.println("Purchase History:");
                    List<String> transactions = user.viewTransactionHistory();
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (String transaction : transactions) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting Wallet...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void handleStockMarket(User user, StockExchange exchange, Scanner scanner) {
        while (true) {
            System.out.println("\nStock Market Menu:");
            System.out.println("1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. Exit Stock Market");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Buy stock
                    System.out.println("Available Stocks:");
                    exchange.displayAvailableStocks();
                    System.out.println("Enter stock symbol to buy (or 'B' to exit):");
                    String buySymbol = scanner.next();
                    if (buySymbol.equalsIgnoreCase("B")) {
                        break;
                    }
                    System.out.print("Enter quantity to buy: ");
                    int quantityToBuy = scanner.nextInt();
                    Stock stockToBuy = exchange.getStock(buySymbol);
                    if (stockToBuy == null) {
                        System.out.println("Invalid stock symbol.");
                        break;
                    }
                    System.out.println(user.purchaseStock(stockToBuy, quantityToBuy));
                    break;
                case 2:
                    // Sell stock
                    System.out.println("Your Stocks:");
                    for (Stock stock : user.getOwnedStocks()) {
                        System.out.println(stock.getSymbol() + " - Quantity: " + stock.getQuantity());
                    }
                    System.out.println("Enter stock symbol to sell (or 'B' to go back to stock market):");
                    String sellSymbol = scanner.next();
                    if (sellSymbol.equalsIgnoreCase("B")) {
                        break;
                    }
                    System.out.print("Enter quantity to sell: ");
                    int quantityToSell = scanner.nextInt();
                    System.out.println(user.sellStock(sellSymbol, quantityToSell));
                    break;
                case 3:
                    System.out.println("Exiting Stock Market...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
