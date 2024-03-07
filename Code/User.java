import java.util.Scanner;
Class User implements Server {

    public void Username(){
        Scanner User = new Scanner(System.in);
        System.out.println("Enter Username: ");
        
        String Username = User.nextLine();
        System.out.println("Username is: " + Username);

    }
}