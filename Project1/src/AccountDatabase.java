/**
 * This class represents an Account Database that manages accounts.
 * It includes functionalities to add, remove, and search the database.
 *
 * @author George Seriani
 */
public class AccountDatabase {
    private Account [] accounts;
    private int size;
    private Archive archive; //a linked list of closed account

    private static final int CAPACITY = 4;
    private static final int NOT_FOUND = -1;

    /**
     * Constructor to initialize the accounts array with the fixed capacity length, the size to 0, and the archive as a new archive object
     */
    public AccountDatabase() {
        this.accounts = new Account[CAPACITY];
        this.size = 0;
        this.archive = new Archive();
    }

    /**
     * Simple private method to find whether an account exists in the active system or not
     * @param account the account we are looking for
     * @return either the index of the account in the database, or -1 if not found
     */
    private int find(Account account) {
        for(int i = 0; i < size; i++){
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return NOT_FOUND;
    }


    /**
     * Check if the Holder already has a account of the type they are trying to open
     * @param profile the Holders information
     * @param accountType The account type
     * @return the index of their account if it exits, and NOT_FOUND otherwise
     */
    public int lookUp(Profile profile, AccountType accountType){
        for(int i = 0; i < size; i++){
            if (accounts[i].getHolder().equals(profile) && accounts[i].getAccountType().equals(accountType)) {
                return i;
            }
        }
        return NOT_FOUND;
    }



    /**
     * Increasing the size of the database another fixed capacity length, 4
     */
    private void grow() {
        Account[] newAccounts = new Account[accounts.length + CAPACITY];
        for(int i = 0; i < accounts.length; i++) {
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts;
    }

    /**
     * Check if an account exits in the system
     * @param account the account we are looking for
     * @return true if found, false otherwise
     */
    public boolean contains(Account account) {
        return find(account) != NOT_FOUND;
    }

    /**
     * Add an account to the database, only if it doesn't already exist in the account
     * @param account the account we would like to add
     */
    public boolean add(Account account) {
        if(contains(account)){
            return false;
        }
        if(accounts.length == size){
            grow();
        }
        accounts[size++] = account;
        return true;
    }

    /**
     * Remove an account if it exists in the database, and add to the archive
     * @param account the account we want to remove
     */
    public boolean remove(Account account) {
        if(contains(account)){
            int idx = find(account);
            accounts[idx].updateBalanceClosedAccount();
            accounts[idx] = accounts[size-1];
            accounts[size-1] = null;
            size -= 1;
            archive.add(account);
            return true;
        }
        return false;
    }

    /**
     * Find the account that would like to withdraw money and call the withdrawal method created in Account()
     * @param number the account number of the account that would like to withdraw
     * @param amount the amount the user would like to withdraw
     * @return true if successful, false otherwise
     */
    public boolean withdraw(AccountNumber number, double amount) {
        for(int i = 0; i < size; i ++){
            if (accounts[i].getAccountNumber() == number){
                return accounts[i].withdraw(amount) == 1; //Successful withdrawal
            }
        }
        return false; // unsuccessful withdrawal
    }

    /**
     * Find the account that would like to deposit money and call the deposit method created in Account()
     * @param number the account number of the account that would like to deposit
     * @param amount the amount the user would like to deposit
     */
    public void deposit(AccountNumber number, double amount) {

        for(int i = 0; i < size; i ++){
            if (accounts[i].getAccountNumber() == number){
                accounts[i].deposit(amount);
                break;
            }
        }
    }

    /**
     * Print the Archive database
     */
    public void printArchive() {
        archive.printLL();
    }

    public void printByBranch() {


    }
    public void printByHolder() {}
    public void printByType() {}

    /**
     * Helper Method to get the size of the database
     * @return size of the database
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Helper method to get the accounts of the database
     * @return the accounts in the database
     */
    public Account[] getAccounts(){
        return accounts;
    }

    /**
     * Helper method to check if database is empty
     * @return true or false
     */
    public boolean isEmpty(){
        return size == 0;
    }





    public static void main(String[] args) {

    }
}
