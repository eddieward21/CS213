/**
 * Account class represents a bank account with basic functionalities such as
 * deposit, withdrawal, balance updates, and account management. It implements
 * the Comparable interface to allow comparison of accounts based on account numbers.
 *
 * Author: George Seriani
 */
public class Account implements Comparable<Account> {

    private AccountNumber number;
    private Profile holder;
    private double balance;

    private static final int TRUE = 1;
    private static final int FALSE = 0;


    /**
     *  Constructor to initialize the private variables
     * @param number the users account number
     * @param holder the name of the holder
     * @param balance the balance of the account
     */
    public Account(AccountNumber number, Profile holder, double balance) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }

    /**
     * Helper method to downgrade from 03 -> 02 account type
     */
    public void downgrade(){
        this.number.downgradeToSavings();
    }


    /**
     * To withdraw money from the account
     * @param amount the user would like to withdraw
     * @return 1 if deposit was valid, 0 if not valid
     */
    public int withdraw(double amount) {
        if (amount > 0 && amount <= balance){
            balance -= amount; // Done in the database?
            return TRUE;
        }
        else{
            return FALSE;
        }
    }

    /**
     * To deposit money into the account
     * @param amount the user would like to deposit
     * @return 1 if deposit was valid, 0 if not valid
     */
    public int deposit(double amount) {
        if (amount > 0) {
            balance += amount; //Done in the database?
            return TRUE;
        }
        else{
            return FALSE;
        }
    }


    @Override
    public boolean equals(Object obj){
        if(obj instanceof Account){
            Account account = (Account) obj;

            boolean profileComp = account.holder.equals(this.holder);
            if(profileComp){
                return account.number.getAccountTypeCode().equals(this.number.getAccountTypeCode());
            }
        }
        return false;
    }


    @Override
    public String toString(){
        return "Account*[" + this.number.getAccountNumber() + "] Holder[" + this.holder.toString() + "] Balance[$" + this.balance + "] Branch [" + this.number.getBranch() + "]";
    }



    @Override
    public int compareTo(Account account) {
        return Integer.compare(Integer.parseInt(this.number.getNumber()), account.getNumber());
    }

    /**
     * A getter method to get the users account number
     * @return the users acount number
     */
    public int getNumber() {
        return Integer.parseInt(number.getNumber());
    }

    /**
     * A getter method to get the accounts balance
     * @return the accounts balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Make closed account balance $0
     */
    public void updateBalanceClosedAccount(){
        this.balance = 0;
    }

    /**
     * Helper method to get the account number
     * @return the account number
     */
    public AccountNumber getAccountNumber(){
        return this.number;
    }

    /**
     * Helper method to get the account number as a string
     * @return account number as a string
     */
    public String getAccountNumberStr(){
        return this.number.getAccountNumber();
    }

    /**
     * Helper method to get the profile of the account
     * @return the holder of the account
     */
    public Profile getHolder(){
        return this.holder;
    }

    /**
     * Helper method to get the account type
     * @return the account type
     */
    public AccountType getAccountType(){
        return this.number.getType();
    }

    /**
     * Doesn't do anything.
     * @param args command line arguments
     */
    public static void main(String[] args) {


    }

}
