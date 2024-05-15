interface MoneyManagement {
    String deposit(double amount);

    String withdraw(double amount);

    void setBalance(double balance); // Added setBalance method
}