import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private double balance;
    private List<Stock> ownedStocks;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.ownedStocks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addStock(Stock stock) {
        ownedStocks.add(stock);
    }

    public void removeStock(Stock stock) {
        ownedStocks.remove(stock);
    }

    public List<Stock> getOwnedStocks() {
        return ownedStocks;
    }
}
