import java.util.Date;

public class Transaction {

    double amount;
    Date timestamp;
    String memo;
    Account inAccount;

    public Transaction(double amount, Account inAccount)
    {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    public Transaction(double amount, String memo, Account inAccount)
    {
        this(amount, inAccount);
        this.memo = memo;
    }

    public double getAmount()
    {
        return this.amount;
    }
}
