import java.util.ArrayList;
import java.util.Random;

public class Bank {

    String name;
    ArrayList<User> users;
    ArrayList<Account> accounts;

    public Bank(String name)
    {
        this.name = name;
        this.users =new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
    public String getNewUserUUID()
    {
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        do{
            uuid = "";
            for(int c=0; c<len; c++)
            {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            nonUnique = false;
            // check if it is unique;
            for(User u : this.users)
            {
                if(uuid.compareTo(u.getUUID()) == 0)
                {
                    nonUnique = true;
                    break;
                }
            }
        }while(nonUnique);

        return uuid;
    }

    public String getNewAccountUUID()
    {
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        do{
            uuid = "";
            for(int c=0; c<len; c++)
            {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            nonUnique = false;
            // check if it is unique;
            for(Account a : this.accounts)
            {
                if(uuid.compareTo(a.getUUID()) == 0)
                {
                    nonUnique = true;
                    break;
                }
            }
        }while(nonUnique);

        return uuid;
    }

    public User addUser(String firstName, String lastName, String pin)
    {
        User newUser = new User(firstName, lastName, pin, this);

        Account newAccount = new Account("Savings", newUser, this);
        accounts.add(newAccount);
        newUser.addAccount(newAccount);
        users.add(newUser);

        return newUser;
    }

    public void addAccount(Account account)
    {
        this.accounts.add(account);
    }
    public User userLogin(String userID, String pin)
    {
        for(User u: this.users)
        {
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin))
            {
                return u;
            }
        }
        return null;
    }

    public String getName()
    {
        return name;
    }
}
