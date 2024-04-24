import java.util.Scanner;

public class Login {
    // Hardcoded username and password for demonstration purposes
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the login system!");

        // Prompt user for username and password
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        // Check if the entered username and password match the hardcoded values
        if (enteredUsername.equals(USERNAME) && enteredPassword.equals(PASSWORD)) {
            System.out.println("Login successful! Welcome, " + USERNAME + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }

        scanner.close();
    }
}