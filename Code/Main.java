import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, String> userCredentials = new HashMap<>();
    private static final Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        User currentUser = null;
        StockExchange stockExchange = new StockExchange();

        while (true) {
            if (!loggedIn) {
                System.out.println("Welcome to the login system!");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (choice == 1) {
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your initial balance: ");
                    double balance = scanner.nextDouble();

                    userCredentials.put(username, password);
                    users.put(username, new User(name, balance));
                    System.out.println("Registration successful! Please login.");
                } else if (choice == 2) {
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
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Welcome, " + currentUser.getName() + "!");
                System.out.println("Choose an option:");
                System.out.println("1. View Wallet");
                System.out.println("2. Enter Stock Market");
                System.out.println("3. Switch Account");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        handleWallet(currentUser, scanner);
                        break;
                    case 2:
                        handleStockMarket(currentUser, stockExchange, scanner);
                        break;
                    case 3:
                        loggedIn = false;
                        currentUser = null;
                        break;
                    case 4:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private static boolean authenticate(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    private static void handleWallet(User user, Scanner scanner) {
        System.out.println("Wallet Menu:");
        System.out.println("1. Deposit Funds");
        System.out.println("2. Withdraw Funds");
        System.out.println("3. View Transaction History");
        System.out.println("4. View Owned Stocks");
        System.out.println("5. Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                System.out.println(user.deposit(depositAmount));
                break;
            case 2:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                System.out.println(user.withdraw(withdrawAmount));
                break;
            case 3:
                System.out.println("Transaction History:");
                for (String transaction : user.viewTransactionHistory()) {
                    System.out.println(transaction);
                }
                break;
            case 4:
                System.out.println("Owned Stocks:");
                for (Stock stock : user.getOwnedStocks()) {
                    System.out.println(stock.getSymbol() + " - Quantity: " + stock.getQuantity() + " - Current Price: $" + stock.getPrice());
                }
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void handleStockMarket(User user, StockExchange stockExchange, Scanner scanner) {
        System.out.println("Stock Market Menu:");
        System.out.println("1. View Available Stocks");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                stockExchange.displayAvailableStocks();
                break;
            case 2:
                System.out.print("Enter stock symbol to buy: ");
                String buySymbol = scanner.next();
                System.out.print("Enter quantity to buy: ");
                int buyQuantity = scanner.nextInt();
                stockExchange.buyStock(buySymbol, buyQuantity, user);
                break;
            case 3:
                System.out.print("Enter stock symbol to sell: ");
                String sellSymbol = scanner.next();
                System.out.print("Enter quantity to sell: ");
                int sellQuantity = scanner.nextInt();
                stockExchange.sellStock(sellSymbol, sellQuantity, user);
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
