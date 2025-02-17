package project1;

/**
 * This class represents an account.project1.Account Database that manages accounts.
 * It includes functionalities to add, remove, and search the database.
 *
 * @author George Seriani
 */
public class AccountDatabase {
    private Account[] accounts;
    private int size;
    private Archive archive; //a linked list of closed account

    /**
     * Capacity of array database
     */
    private static final int CAPACITY = 4;
    /**
     * To express account not found
     */
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
     * @return true if the account is added successfully, false otherwise
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
     * @return true if account removed successfully, false otherwise
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
     * Find the account that would like to withdraw money and call the withdrawal method created in account.project1.Account()
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
     * Find the account that would like to deposit money and call the deposit method created in account.project1.Account()
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
     * Print the project1.Archive database
     */
    public void printArchive() {
        archive.printLL();
    }

    /**
     * Helper method to sort accounts by branch location.
     * Sorts first by county, then by branch name within each county.
     * @return Array of accounts sorted by branch location
     */
    private Account[] sortByBranch(){
        Account[] sortedAccounts = new Account[size];
        for (int i = 0; i < size; i++) {
            sortedAccounts[i] = accounts[i];
        }

        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                Branch currentBranch = Branch.getBranchByName(sortedAccounts[j].getAccountNumber().getBranch());
                Branch minBranch = Branch.getBranchByName(sortedAccounts[minIndex].getAccountNumber().getBranch());

                int countyCompare = currentBranch.getCounty().compareTo(minBranch.getCounty());

                if (countyCompare == 0) {
                    int branchCompare = currentBranch.toString().compareTo(minBranch.toString());
                    if (branchCompare < 0) {
                        minIndex = j;
                    }
                }

                else if (countyCompare < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Account temp = sortedAccounts[i];
                sortedAccounts[i] = sortedAccounts[minIndex];
                sortedAccounts[minIndex] = temp;
            }
        }
        return sortedAccounts;
    }

    /**
     * Prints all accounts in the database ordered by branch location.
     *
     */
    public void printByBranch() {
        Account[] sortedAccounts = sortByBranch();

        System.out.println("\n*List of accounts ordered by branch location (county, city).");

        String currentCounty = null;
        for (int i = 0; i < size; i++) {
            Branch branch = Branch.getBranchByName(sortedAccounts[i].getAccountNumber().getBranch());
            String county = branch.getCounty();

            if (!county.equals(currentCounty)) {
                System.out.println("County: " + county);
                currentCounty = county;
            }

            System.out.println(sortedAccounts[i].toString());
        }

        System.out.println("*end of list.\n");

    }

    /**
     * Prints all accounts in the database ordered by account holder name and account number.
     * For holders with multiple accounts, those accounts are sorted by account number.
     * Uses selection sort to maintain the order of accounts.
     */
    public void printByHolder() {
        Account[] sortedAccounts = new Account[size];
        for (int i = 0; i < size; i++) {
            sortedAccounts[i] = accounts[i];
        }
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                int holderCompare = sortedAccounts[j].getHolder().compareTo(sortedAccounts[minIndex].getHolder());

                if (holderCompare == 0) {
                    if (sortedAccounts[j].getNumber() < sortedAccounts[minIndex].getNumber()) {
                        minIndex = j;
                    }
                }
                else if (holderCompare < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Account temp = sortedAccounts[i];
                sortedAccounts[i] = sortedAccounts[minIndex];
                sortedAccounts[minIndex] = temp;
            }
        }

        System.out.println("\n*List of accounts ordered by account holder and number.");
        for(int i = 0; i < size; i++){
            System.out.println(sortedAccounts[i].toString());
        }
        System.out.println("*end of list.\n");
    }

    /**
     * Helper method to sort accounts by their type and account number.
     * Sorts first by account type code, then by account number within each type.
     * @return Array of accounts sorted by type and number
     */
    private Account[] sortByType(){
        Account[] sortedAccounts = new Account[size];
        for (int i = 0; i < size; i++) {
            sortedAccounts[i] = accounts[i];
        }

        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                AccountType currentAccountType = sortedAccounts[j].getAccountType();
                AccountType minAccountType = sortedAccounts[minIndex].getAccountType();

                // Compare account types first
                int accountTypeCompare = currentAccountType.getCode().compareTo(minAccountType.getCode());

                if (accountTypeCompare == 0) {
                    if (sortedAccounts[j].getNumber() < sortedAccounts[minIndex].getNumber()) {
                        minIndex = j;
                    }
                } else if (accountTypeCompare < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Account temp = sortedAccounts[i];
                sortedAccounts[i] = sortedAccounts[minIndex];
                sortedAccounts[minIndex] = temp;
            }

        }

        return sortedAccounts;
    }

    /**
     * Prints all accounts in the database ordered by account type.
     * Displays accounts grouped by type, with a header for each type.
     * Within each type, accounts are sorted by account number.
     */
    public void printByType() {
        Account[] sortedAccounts = sortByType();

        System.out.println("\n*List of accounts ordered by account type and number.");

        String currentAccountType = null;
        for (int i = 0; i < size; i++) {
            String accountType = AccountType.accountTypeFromCode(sortedAccounts[i].getAccountType().getCode()).toString();

            if (!accountType.equals(currentAccountType)) {
                String accountTypeFixed = accountType;
                if(accountType.equalsIgnoreCase("moneymarket")){
                    accountTypeFixed = "MONEY_MARKET";
                }
                System.out.println("account.project1.Account Type: " + accountTypeFixed);
                currentAccountType = accountType;
            }

            System.out.println(sortedAccounts[i].toString());
        }

        System.out.println("*end of list.\n");
    }

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
