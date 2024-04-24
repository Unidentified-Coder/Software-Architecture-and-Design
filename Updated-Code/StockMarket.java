import java.util.Scanner;
import java.util.Random;

interface StockExchange {
    void displayStockInfo(int number);
}

class StockMarket implements StockExchange {
    private Scanner scanner;

    public StockMarket() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayStockInfo(int number) {
        Random random = new Random();
        // Generate a random percentage from -10.0% to 10.0%
        double change = -10.0 + random.nextDouble() * 20.0;
        // Format the change to two decimal places
        String formattedChange = String.format("%.2f%%", change);

        switch (number) {
            case 1:
                System.out.println("Google Stocks: " + formattedChange);
                break;
            case 2:
                System.out.println("Apple Stocks: " + formattedChange);
                break;
            case 3:
                System.out.println("Samsung Stocks: " + formattedChange);
                break;
            case 4:
                System.out.println("Tesla Stocks: " + formattedChange);
                break;
            case 5:
                System.out.println("Microsoft Stocks: " + formattedChange);
                break;
            case 6:
                System.out.println("Amazon Stocks: " + formattedChange);
                break;
            case 7:
                System.out.println("Facebook Stocks: " + formattedChange);
                break;
            case 8:
                System.out.println("Netflix Stocks: " + formattedChange);
                break;
            case 9:
                System.out.println("Intel Stocks: " + formattedChange);
                break;
            case 10:
                System.out.println("AMD Stocks: " + formattedChange);
                break;
            default:
                System.out.println("Invalid stock number. Please enter a number from 1-10.");
                break;
        }
    }

    public void closeScanner() {
        scanner.close();
    }

    public int getStockNumber() {
        int stockNumber = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Please enter a stock number from 1-10:");
                stockNumber = scanner.nextInt();
                if (stockNumber >= 1 && stockNumber <= 10) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number within the valid range (1-10).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // This clears the input buffer
            }
        }
        return stockNumber;
    }

    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        int stockNumber = market.getStockNumber();
        market.displayStockInfo(stockNumber);
        market.closeScanner();
    }
}
