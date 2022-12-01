import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    String firstName;
    String lastName;
    String uuid;
    // md5 hash of the user's pin
    byte pinHash[];
    ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank bank)
    {
        this.firstName = firstName;
        this.lastName = lastName;

        // store the pin's md5 hahs
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new, unique universal ID for the user
        this.uuid = bank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log
        System.out.printf("New user %s, %s  with ID %s is created", firstName, lastName, uuid);
    }

    public void addAccount(Account account)
    {
        this.accounts.add(account);
    }

    public String getUUID()
    {
        return uuid;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public boolean validatePin(String aPin)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public void printAccountSummary()
    {
        System.out.printf("\n\n%s's accounts Summary", this.firstName);

        for(int i=0; i<this.accounts.size(); i++)
        {
            System.out.printf("%d) %s\n",i+1,
                    this.accounts.get(i).getSummaryLine());
        }
        System.out.println();
    }
}
