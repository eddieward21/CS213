import java.util.Scanner;

public class TransactionManager {
    private boolean RUNNING = true;
    private AccountDatabase accountDatabase;
    private Archive accountArchive;

    public TransactionManager() {
        this.accountDatabase = new AccountDatabase();  // Initialize your AccountDatabase
        this.accountArchive = new Archive();  // Initialize Archive if needed
    }


    private static int moneyMarketValid(String balance){
        if(Integer.parseInt(balance) < 2500){
            return 0;
        }
        return 1;
    }

    private static Date createDate(String dob){
        String[] parts = dob.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new Date(year, month, day);
    }


    private void openAccount(String[] tokens){
        String accountTypeStr = tokens[1];
        String branchStr = tokens[2];
        String fname = tokens[3];
        String lname = tokens[4];
        String balance = tokens[6];
        String dob = tokens[5];

        if(accountTypeStr.equalsIgnoreCase("moneymarket")){
            if(moneyMarketValid(balance) == 0){
                System.out.println("Minimum of $2,000 to open a Money Market account.");
                return;
            }
        }

        Branch branch = getBranch(branchStr);
        if (branch == null) return;  // Exit if invalid branch.

        AccountType accountType = getAccountType(accountTypeStr);
        if (accountType == null) return;  // Exit if invalid account type.

        if(validInitialDeposit(balance) != 1){
            return;
        }

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
        if(accountDatabase.lookUp(profile, accountType) != -1) return;

        AccountNumber accountNumber = generateAccountNumber(branch, accountType);
        Account account = new Account(accountNumber, profile, Integer.parseInt(balance));

        if (accountDatabase.add(account)) {
            System.out.println(accountTypeStr.toUpperCase() + " account " + account.getAccountNumberStr() + " has been opened.");
        } else {
            System.out.println(fname + " " + lname + " already has a " + accountTypeStr + " account.");
        }
    }

    private int validInitialDeposit(String balance) {
        try {
            if (Integer.parseInt(balance) > 0) {
                return 1;
            }
            else{
                System.out.println("Initial deposit cannot be 0 or negative.");
            }
        } catch (NumberFormatException e) {
            System.out.println("For input string: " + balance + " - not a valid amount.");
        }
        return 0;
    }

    private Branch getBranch(String branchStr) {
        Branch branch = Branch.getBranchByName(branchStr.toUpperCase());
        if (branch == null) {
            System.out.println(branchStr + " - invalid branch.");
        }
        return branch;
    }

    private AccountType getAccountType(String accountTypeStr) {
        AccountType accountType = AccountType.accountTypeFromName(accountTypeStr);
        if (accountType == null) {
            System.out.println(accountTypeStr + " - invalid account type.");
        }
        return accountType;
    }

    private AccountNumber generateAccountNumber(Branch branch, AccountType accountType) {
        String branchCode = branch.getBranchCode();
        String accountCode = accountType.getCode();
        return new AccountNumber(branch, accountType);
    }

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




    private int validDeposit(String balance) {
        try {
            if (Integer.parseInt(balance) > 0) {
                return 1;
            }
            else{
                System.out.println(balance + " - deposit cannot be 0 or negative.");
            }
        } catch (NumberFormatException e) {
            // Handle the case where balance is not a valid integer
            System.out.println("For input string: " + balance + " - not a valid amount.");
        }
        return 0;
    }

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

    private int validWithdrawl(String balance) {
        try {
            if (Integer.parseInt(balance) > 0) {
                return 1;
            }
            else{
                System.out.println(balance + " - deposit cannot be 0 or negative.");
            }
        } catch (NumberFormatException e) {
            // Handle the case where balance is not a valid integer
            System.out.println("For input string: " + balance + " - not a valid amount.");
        }
        return 0;
    }

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


    public void printDatabase(){
        if(accountDatabase.isEmpty()){
            System.out.println("Account database is empty!");
            return;
        }
        System.out.println("*List of accounts in the account database.");
        Account[] accounts = accountDatabase.getAccounts();
        for(int i = 0; i < accountDatabase.getSize(); i++){
            System.out.println(accounts[i].toString());
        }
        System.out.println("*end of list.");
    }


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
                System.out.println("*List of closed accounts in the archive.");
                accountDatabase.printArchive();
                System.out.println("*end of list.");
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
            case "Q":
                RUNNING = false;
                System.out.println("Transaction Manager is terminated.");
                break;
            default:
                System.out.println("Invalid command.");
        }
    }



    public void run() {
        System.out.println("Transaction Manager is running.");
        Scanner scanner = new Scanner(System.in);

        // Infinite loop to keep reading commands until 'Q' is entered
        while (RUNNING) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim();

            if ("Q".equals(command)) {
                break;
            }

            // Handle different commands (example: transaction processing, etc.)
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
