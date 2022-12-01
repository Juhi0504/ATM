import java.util.Scanner;

public class ATM {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        Bank bank = new Bank("Bank of Braavos");

        User aUSer = bank.addUser("Arya", "Stark", "1234");

        Account newAccount = new Account("Saving", aUSer, bank);

        aUSer.addAccount(newAccount);
        bank.addAccount(newAccount);

        User curUser;
        while(true)
        {
            // stay in login prompt until successful login
            curUser = ATM.mainMenuPrompt(bank, sc);
            ATM.printUserMenu(curUser, sc);
        }
    }

    public static User mainMenuPrompt(Bank bank, Scanner sc)
    {
        String userID;
        String pin;
        User authUser;

        do{
            System.out.printf("Welcome to %s" , bank.getName());
            System.out.printf("Enter user id: ");
            userID = sc.nextLine();
            System.out.printf("Enter pin: ");
            pin = sc.nextLine();

            authUser = bank.userLogin(userID, pin);
            if(authUser == null)
            {
                System.out.printf("Incorrect user id/pin. Please try again");
            }
        }while(authUser == null);

        return authUser;
    }

    public static void printUserMenu(User authUser, Scanner sc)
    {
        authUser.printAccountSummary();

        int choice;

        do{
           System.out.printf("Welcome %s, what would you liek to do", authUser.getFirstName());
           System.out.println(" 1 transaction history");
            System.out.println(" 1 Withradwal");
            System.out.println(" 1 Deposit");
            System.out.println(" 1 Transfer");
            System.out.println(" 1 Quit");
            System.out.println("Enetr Choice: ");
            choice = sc.nextInt();
            if(choice < 1 || choice > 5)
            {
                System.out.println("Incorrect choice");
            }
        }while(choice < 1 || choice > 5);

        // process the choice
        switch (choice)
        {
            case 1:
                ATM.showTransactionHistory(authUser, sc);
                break;
            case 2:
                ATM.withdrawFunds(authUser, sc);
                break;
            case 3:
                ATM.depositFunds(authUser, sc);
                break;
            case 4:
                ATM.transferFunds(authUser, sc);
                break;
        }

        // redisplay this menu unless they quit
        if(choice != 5)
        {
            ATM.printUserMenu(authUser, sc);
        }
    }

    public static void showTransactionHistory(User user, Scanner sc)
    {
        int theAcct;
        do{
            System.out.printf("Enter the account number ");
            theAcct = sc.nextInt() - 1;

            if(theAcct <0)
            {
                //error
            }
        }while(theAcct < 0);
    }

    public static void depositFunds(User user, Scanner sc)
    {

    }

    public static void withdrawFunds(User user, Scanner sc)
    {

    }

    public static void transferFunds(User user, Scanner sc)
    {

    }
}
