import java.util.Scanner;

public class TransactionManager {
    private boolean RUNNING = true;
    private AccountDatabase accountDatabase;
    private Archive accountArchive;

    public TransactionManager() {
        this.accountDatabase = new AccountDatabase();  // Initialize your AccountDatabase
        this.accountArchive = new Archive();  // Initialize Archive if needed
    }


    private void openAccount(String[] tokens){
        String accountTypeStr = tokens[1];
        String branchStr = tokens[2];
        String fname = tokens[3];
        String lname = tokens[4];
        String dob = tokens[5];
        String balance = tokens[6];


        Branch branch = Branch.getBranchByName(branchStr.toUpperCase());
        String branchCode = branch.getBranchCode();
        AccountType accountType = AccountType.accountTypeFromName(accountTypeStr);
        String accountCode = accountType.getCode();
        AccountNumber accountNumber = new AccountNumber(branch, accountType, branchCode+accountCode);

        String[] parts = dob.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        Date date = new Date(year, month, day);
        Profile profile = new Profile(fname, lname, date);
        Account account = new Account(accountNumber, profile, Integer.parseInt(balance));

        if(date.isValid()){
            if(accountDatabase.add(account)){
                accountDatabase.add(account);
                System.out.println(accountTypeStr.toUpperCase() + " account " + String.valueOf(account.getNumber()) + " has been opened.");
            }
        }
        else{
            // Invalidation string
            System.out.println("Invalid DOB!!! ");
        }
    }

    private void closeAccount(String[] tokens){
        String accountNumber = tokens[1];

        Account[] accounts = accountDatabase.getAccounts();
        for(int i = 0; i < accountDatabase.getSize(); i++){
            if(accounts[i].getNumber() == Integer.parseInt(accountNumber)){
                accountDatabase.remove(accounts[i]);
                break;
            }
        }
    }

    private void depositAccount(String[] tokens){
        String accountNumberStr = tokens[1];
        String amount = tokens[2];

        Account[] accounts = accountDatabase.getAccounts();
        for(int i = 0; i < accountDatabase.getSize(); i++){
            if(accounts[i].getNumber() == Integer.parseInt(accountNumberStr)){
                AccountNumber accountNumber = accounts[i].getAccountNumber();
                accountDatabase.deposit(accountNumber, Integer.parseInt(amount));
                break;
            }
        }

    }

    private void withdrawAccount(String[] tokens){
        String accountNumberStr = tokens[1];
        String amount = tokens[2];

        Account[] accounts = accountDatabase.getAccounts();
        for(int i = 0; i < accountDatabase.getSize(); i++){
            if(accounts[i].getNumber() == Integer.parseInt(accountNumberStr)){
                AccountNumber accountNumber = accounts[i].getAccountNumber();
                accountDatabase.withdraw(accountNumber, Integer.parseInt(amount));
                break;
            }
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
                closeAccount(tokens);
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

        while (RUNNING) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim();
            inputCommands(command);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        TransactionManager transactionManager = new TransactionManager();
        transactionManager.run();
    }
}
