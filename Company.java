
import java.util.Scanner;

class Company implements Server {
    private static final String User = null;

    public void Username(){
        Scanner User = new Scanner(System.in);
        System.out.println("Enter Username: ");
        
        String Username = User.nextLine();
        System.out.println("Username is: " + Username);

    }
    
    public void StockExchange(){
        System.out.println("Currently the stock exchange is £1 ∼ $3.545");
        
    }

    public void Share(){
        System.out.println("Currently Share Price is ");
        System.out.println("1 Share = £0.3004");

        System.out.println("Currently user " + User);
        System.out.println("Has 5 Shares Valued at £50");

    }

    public void Index(){
        
    }
    
}
