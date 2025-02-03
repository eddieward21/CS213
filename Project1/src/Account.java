public class Account implements Comparable<Account> {

    private AccountNumber number;
    private Profile holder;
    private double balance;


    /**
     *
     * @param number
     * @param holder
     * @param balance
     */
    public Account(AccountNumber number, Profile holder, double balance) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }


    /** To update the balance
     *
     * @param amount
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount >= balance){
            balance -= amount;
        }
        else{
            //How to throw error?
        }
    }

    /**
     *
     * @param amount
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
        else{
            //How to throw error?
        }
    } //to update the balance


    @Override
    public boolean equals(Object obj){
        if(obj instanceof Account){
            Account account = (Account) obj;
            return account.number.equals(this.number);
        }
        return false;
    }


    //Account#[200017410] Holder[John Doe 2/19/2000] Balance[$600.00] Branch [BRIDGEWATER]
    @Override
    public String toString(){
        return "Account*[" + this.number + "] Holder[" + this.holder + "] Balance[" + this.balance + "] Branch [" + // + "]";
    }



    @Override
    public int compareTo(Account account) {
        //Ask in office hours
    }

    //Getter methods
    public AccountNumber getNumber() {
        return number;
    }

    public Profile getHolder() {
        return holder;
    }

    public double getBalance() {
        return balance;
    }




}
