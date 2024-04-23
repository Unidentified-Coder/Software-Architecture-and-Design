import java.util.Scanner;

interface StockExchange {
    void displayStockInfo();
}

class StockMarket implements StockExchange {
    private Scanner scanner;

    public StockMarket() {
        scanner = new Scanner(System.in);
        int number = 0; // Initialize variable

        try {
            System.out.println("Please enter a number from 1-4:");
            number = scanner.nextInt();
            System.out.println("You entered: " + number);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } finally {
            scanner.close(); // Always close the scanner
        }

        displayStockInfo(number);
    }

    public void displayStockInfo(int number) {
        // The 'number' variable is now accessible here
        switch (number) {
            case 1:
                System.out.println("Google Stocks");
                break;
            case 2:
                System.out.println("Apple Stocks");
                break;
            case 3:
                System.out.println("Samsung Stocks");
                break;
            case 4:
                System.out.println("Tesla Stocks");
                break;
            default:
                // Handle case where number is not between 1 and 4
                if (number < 1 || number > 4) {
                    System.out.println("Number is not within the valid range (1-4).");
                }
                break;
        }
    }

    public static void main(String[] args) {
        new StockMarket();
    }
}

// public void SharePrice(){
    
//     System.out.println("Currently Share Price is ");
//     System.out.println("Has 5 Shares Valued at £50");
// }

// public void StockData(){

//     System.out.println("Currently the stocks that are up are : Google\\n");
// }