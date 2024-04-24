import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, String> userCredentials = new HashMap<>(); // Stores username-password pairs
    private static final Map<String, User> users = new HashMap<>(); // Stores username-User object pairs

    public static void main(String[] args) {
        // Add user credentials
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
        userCredentials.put("user3", "password3");

        // Add users
        users.put("user1", new User("John Doe", 1000.00));
        users.put("user2", new User("Jane Smith", 1500.00));
        users.put("user3", new User("Alice Johnson", 2000.00));

        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        User currentUser = null;
        StockExchange stockExchange = new StockExchange(); // Instantiate StockExchange

        while (true) {
            if (!loggedIn) {
                // Login
                System.out.println("Welcome to the login system!");
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                if (authenticate(username, password)) {
                    System.out.println("Login successful!");
                    currentUser = users.get(username);
                    loggedIn = true;
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
            } else {
                System.out.println("Welcome, " + currentUser.getName() + "!");
                System.out.println("Choose an option:");
                System.out.println("1. View Wallet");
                System.out.println("2. Enter Stock Market");
                System.out.println("3. Switch Account");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Wallet operations
                        handleWallet(currentUser, scanner);
                        break;
                    case 2:
                        // Stock market operations
                        handleStockMarket(currentUser, stockExchange, scanner); // Pass stockExchange instance
                        break;
                    case 3:
                        loggedIn = false;
                        currentUser = null;
                        scanner.nextLine(); // Clear the newline character
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private static boolean authenticate(String username, String password) {
        // Check if the provided username exists and the password matches
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
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
                    System.out.println(exchange.buyStock(buySymbol, quantityToBuy, user) ?
                                       "Transaction successful!" : "Transaction failed.");
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
                    System.out.println(exchange.sellStock(sellSymbol, quantityToSell, user) ?
                                       "Transaction successful!" : "Transaction failed.");
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
