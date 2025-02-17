package project1;

import java.util.Scanner;
/**
 * This class manages various banking transactions such as opening accounts,
 * making deposits and withdrawals, closing accounts, and printing the account database.
 * Properly displaying the results to the terminal.
 *
 * @author George Seriani
 */
public class TransactionManager {
    private boolean RUNNING = true;

    /**
     * Express True as integer
     */
    private static final int TRUE = 1;
    /**
     * Express False as integer
     */
    private static final int FALSE = 0;

    private AccountDatabase accountDatabase;
    private Archive accountArchive;


    /**
     * Create an instance of the account database and the archive
     */
    public TransactionManager() {
        this.accountDatabase = new AccountDatabase();  // Initialize your project1.AccountDatabase
        this.accountArchive = new Archive();  // Initialize project1.Archive if needed
    }


    /**
     * Method to check if the initial balance is valid for a money market account
     * @param balance the inital deposit
     * @return true (1) or false (0)
     */
    private static int moneyMarketValid(String balance){
        if(Integer.parseInt(balance) < 2500){
            return FALSE;
        }
        return TRUE;
    }

    /**
     * Method to create the date instance using the string version
     * @param dob date as a string
     * @return an instance of project1.Date
     */
    private static Date createDate(String dob){
        String[] parts = dob.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new Date(year, month, day);
    }


    /**
     * Method to open an account with the proper validations
     * @param tokens the information given by the user
     */
    private void openAccount(String[] tokens){
        String accountTypeStr = tokens[1];
        String branchStr = tokens[2];
        String fname = tokens[3];
        String lname = tokens[4];
        String balance = tokens[6];
        String dob = tokens[5];

        Branch branch = getBranch(branchStr);
        if (branch == null) return;  // Exit if invalid branch.

        AccountType accountType = getAccountType(accountTypeStr);
        if (accountType == null) return;  // Exit if invalid account type.

        Date date = createDate(dob);

        if(!date.isValid()){
            System.out.println("DOB invalid: " + date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + " - not a valid calendar date!");
            return;
        }
        if(!date.isFutureDateValid()){
            System.out.println("DOB invalid: " + date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + " cannot be today or a future day.");
            return;
        }
        if(!date.isLegalAgeValid()){
            System.out.println("Not eligible to open: " + date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + " under 18!");
            return;
        }

        Profile profile = new Profile(fname, lname, date);


        if(validInitialDeposit(balance) != TRUE){
            return;
        }
        else{
            if(accountDatabase.lookUp(profile, accountType) != -1){
                System.out.println(fname + " " + lname + " already has a " + accountTypeStr + " account.");
                return;
            }
            else if(Integer.parseInt(balance) <= 0){
                System.out.println("Initial deposit cannot be 0 or negative.");
                return;
            }

        }

        if(accountTypeStr.equalsIgnoreCase("moneymarket")){
            accountTypeStr = "MONEY_MARKET";
            if(moneyMarketValid(balance) == 0){
                System.out.println("Minimum of $2,000 to open a Money Market account.");
                return;
            }
        }

        AccountNumber accountNumber = generateAccountNumber(branch, accountType);
        Account account = new Account(accountNumber, profile, Integer.parseInt(balance));

        if (accountDatabase.add(account)) {
            System.out.println(accountTypeStr.toUpperCase() + " account " + account.getAccountNumberStr() + " has been opened.");
        }
    }

    /**
     * Check if the initial deposit is valid
     * @param balance the initial deposit
     * @return true (1) or false (0)
     */
    private int validInitialDeposit(String balance) {
        try {
            if (Integer.parseInt(balance) > 0) {
                return TRUE;
            }
            else {
                return TRUE;
            }
        } catch (NumberFormatException e) {
            System.out.println("For input string: " + balance + " - not a valid amount.");
        }
        return FALSE;
    }

    /**
     * Get the branch given the string
     * @param branchStr the branch as a string
     * @return the branch
     */
    private Branch getBranch(String branchStr) {
        Branch branch = Branch.getBranchByName(branchStr.toUpperCase());
        if (branch == null) {
            System.out.println(branchStr + " - invalid branch.");
        }
        return branch;
    }

    /**
     * Get the accountType given the string
     * @param accountTypeStr the string of accountType
     * @return the account type
     */
    private AccountType getAccountType(String accountTypeStr) {
        AccountType accountType = AccountType.accountTypeFromName(accountTypeStr);
        if (accountType == null) {
            System.out.println(accountTypeStr + " - invalid account type.");
        }
        return accountType;
    }

    /**
     * Generate the account number given the branch and account type
     * @param branch the branch of the account
     * @param accountType the account type of the account
     * @return the instance of accountNumber
     */
    private AccountNumber generateAccountNumber(Branch branch, AccountType accountType) {
        String branchCode = branch.getBranchCode();
        String accountCode = accountType.getCode();
        return new AccountNumber(branch, accountType);
    }

    /**
     * Close a singular account
     * @param tokens the account number given by user
     */
    private void closeAccount(String[] tokens){
        String accountNumberStr = tokens[1];

        Account[] accounts = accountDatabase.getAccounts();
        for(int i = 0; i < accountDatabase.getSize(); i++){
            if(accounts[i].getAccountNumberStr().equals(accountNumberStr)){
                accountDatabase.remove(accounts[i]);
                System.out.println(accountNumberStr + " is closed and moved to archive; balance set to 0.");
                return;
            }
        }
        System.out.println(accountNumberStr + " account does not exist.");
    }

    /**
     * Close all accounts of a specific holder
     * @param tokens the holders information
     */
    private void closeAllHolderAccounts(String[] tokens){
        String fname = tokens[1];
        String lname = tokens[2];
        String dob = tokens[3];
        Date date = createDate(dob);
        Profile profile = new Profile(fname, lname, date);

        boolean successful = false;

        Account[] accounts = accountDatabase.getAccounts();
        for(int i = 0; i < accountDatabase.getSize(); i++){
            if(accounts[i].getHolder().equals(profile)){
                accountDatabase.remove(accounts[i]);
                successful = true;
            }
        }

        if(successful){
            System.out.println("All accounts for " + fname + " " + lname + " " + dob + " are closed and moved to archive; balance set to 0.");
            return;
        }
        System.out.println(fname + " " + lname + " " + dob + " does not have any accounts in the database.");

    }



    /**
     * Check if the deposit is valid
     * @param balance the deposit
     * @return true (1) or false (0)
     */
    private int validDeposit(String balance) {
        try {
            if (Integer.parseInt(balance) > 0) {
                return TRUE;
            }
            else{
                System.out.println(balance + " - deposit cannot be 0 or negative.");
            }
        } catch (NumberFormatException e) {
            // Handle the case where balance is not a valid integer
            System.out.println("For input string: " + balance + " - not a valid amount.");
        }
        return FALSE;
    }

    /**
     * Preform a deposit into the account
     * @param tokens the information given by the user
     */
    private void depositAccount(String[] tokens){
        String accountNumberStr = tokens[1];
        String amount = tokens[2];

        if(validDeposit(amount) == 1) {
            Account[] accounts = accountDatabase.getAccounts();
            for (int i = 0; i < accountDatabase.getSize(); i++) {
                if (accounts[i].getAccountNumberStr().equals(accountNumberStr)) {
                    AccountNumber accountNumber = accounts[i].getAccountNumber();
                    accountDatabase.deposit(accountNumber, Integer.parseInt(amount));
                    System.out.println("$" + amount + " deposited to " + accountNumberStr + ".");
                    break;
                }
            }
        }

    }

    /**
     * Check if the withdrawal is valid
     * @param balance the withdrawal amount
     * @return true (1) or false (0)
     */
    private int validWithdrawl(String balance) {
        try {
            if (Integer.parseInt(balance) > 0) {
                return TRUE;
            }
            else{
                System.out.println(balance + " - deposit cannot be 0 or negative.");
            }
        } catch (NumberFormatException e) {
            // Handle the case where balance is not a valid integer
            System.out.println("For input string: " + balance + " - not a valid amount.");
        }
        return FALSE;
    }

    /**
     * Preform a withdrawal from the account
     * @param tokens the information given by the user
     */
    private void withdrawAccount(String[] tokens){
        String accountNumberStr = tokens[1];
        String amount = tokens[2];
        String accountTypeStr = accountNumberStr.substring(3,5);

        if(validWithdrawl(amount) == 1){
            Account[] accounts = accountDatabase.getAccounts();
            for(int i = 0; i < accountDatabase.getSize(); i++) {
                if (accounts[i].getAccountNumberStr().equals(accountNumberStr)) {
                    AccountNumber accountNumber = accounts[i].getAccountNumber();
                    if (accountDatabase.withdraw(accountNumber, Integer.parseInt(amount))) {
                        if (accountTypeStr.equals("03") && accounts[i].getBalance() < 2000) {
                            accounts[i].downgrade();
                            System.out.println(accountNumberStr + " is downgraded to SAVINGS - $" + amount + " withdrawn from " + accountNumberStr);
                        } else {
                            System.out.println("$" + amount + " withdrawn from " + accountNumberStr + ".");
                        }
                    }
                    else{
                        System.out.println(accountNumberStr + " - insufficient funds.");
                    }
                    return;
                }
            }
            System.out.println(accountNumberStr + " does not exist.");
        }
    }


    /**
     * Print the current active accounts database
     */
    public void printDatabase(){
        if(accountDatabase.isEmpty()){
            System.out.println("account.project1.Account database is empty!");
            return;
        }
        System.out.println("\n*List of accounts in the account database.");
        Account[] accounts = accountDatabase.getAccounts();
        for(int i = 0; i < accountDatabase.getSize(); i++){
            System.out.println(accounts[i].toString());
        }
        System.out.println("*end of list.\n");
    }


    /**
     * Method to handle all input cases
     * @param command the command given by the user
     */
    private void inputCommands(String command){
        String[] tokens = command.trim().split("\\s+");
        String action = tokens[0];
        switch (action) {
            case "O":
                openAccount(tokens);
                break;
            case "C":
                if(tokens.length > 2){
                    closeAllHolderAccounts(tokens);
                }
                else {
                    closeAccount(tokens);
                }
                break;
            case "D":
                depositAccount(tokens);
                break;
            case "W":
                withdrawAccount(tokens);
                break;
            case "P":
                printDatabase();
                break;
            case "PA":
                accountDatabase.printArchive();
                break;
            case "PB":
                accountDatabase.printByBranch();
                break;
            case "PH":
                accountDatabase.printByHolder();
                break;
            case "PT":
                accountDatabase.printByType();
                break;
            default:
                System.out.println("Invalid command.");
        }
    }


    /**
     * Starts the transaction manager, accepting commands from the user.
     * It keeps running in a loop until the user enters the command 'Q' to quit.
     * This method also handles processing of different commands entered by the user
     */
    public void run() {
        System.out.println("Transaction Manager is running.");
        Scanner scanner = new Scanner(System.in);

        while (RUNNING) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim();
            if ("Q".equals(command)) {
                break;
            }
            if (command.isEmpty()) {
                continue;
            }
            inputCommands(command);
        }
        scanner.close();
        System.out.println("Transaction Manager is terminated.");
    }

    public static void main(String[] args) {
        TransactionManager transactionManager = new TransactionManager();
        transactionManager.run();

    }
}
