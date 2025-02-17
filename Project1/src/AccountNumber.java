import java.util.Objects;
import java.util.Random;

/**
 * Class represents a unique account number assigned to a bank account
 * @author Eddie Ward
 */
public class AccountNumber implements Comparable<AccountNumber> {
    private static final int SEED = 9999;  // Fixed: Constant should not be modified
    private static final Random random = new Random(SEED);
    private Branch branch;
    private AccountType type;
    private String number;

    /**
    // Constructor
    @param branch The branch of the acccount
    @param type The type of account
    */
    public AccountNumber(Branch branch, AccountType type) {
        this.branch = branch;
        this.type = type;
        this.number = generateRandomDigits();
    }
    /**
    // Generate a 4-digit random number based on a seed
    @return a 4 digit string representing a randomly generated number
     */
    private String generateRandomDigits() {
        int randomNumber = random.nextInt(9999);
        return String.format("%04d", randomNumber);

    }
    /**
    // Override equals() for object comparison
     @param obj The object to compare with
     @return true if both accounts are equal. false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AccountNumber that = (AccountNumber) obj;
        return Objects.equals(branch, that.branch) &&
                Objects.equals(type, that.type) &&
                Objects.equals(number, that.number);
    }
    /**
    // Override toString() for a readable format
    @return a string representation of AccountNumber object.
     */
    @Override
    public String toString() {
        return this.branch.getBranchCode()+this.type.getCode()+this.number;
    }
    /**
    get branch name as a string
    @return the branch name
     */
    public String getBranch(){
        return branch.toString();
    }

    /**
    Get randomly generated 4 digit number
    @return the 4 digit account number as a string
     **/
    public String getNumber(){
        return this.toString();
    }

    /**
     *
     * Generate the full account number in the format [Branch Code] + [Account Type Code] + [Random Number].
     * @return a formatted account number string
     */
    public String getAccountNumber(){
        return this.branch.getBranchCode() + this.type.getCode() + this.number;
    }

    /**
     * retrieve the account type
     * @return the account type
     */
    public AccountType getType(){
        return this.type;
    }

    /**
     *
     * Gets the code associated with the account type
     * @return the account type code
     */
    public String getAccountTypeCode() {
        return type.getCode();  // Call getCode() method of AccountType enum
    }
    /**
     * Downgrades the account type to a savings account.
     * This is useful if an account type change is needed.
     */
    public void downgradeToSavings(){
        this.type = AccountType.SAVINGS;
    }

    /**
     * Compares this account number to another account number for sorting.
     * The comparison is based on the string representation of the account number.
     *
     * @param other The other account number to compare with.
     * @return A negative integer, zero, or a positive integer if this account is less than, equal to, or greater than the other.
     */
    @Override
    public int compareTo(AccountNumber other) {
        return this.toString().compareTo(other.toString());
    }
    /**
     * Main method to test the AccountNumber class.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Branch princeton = Branch.PRINCETON;
        AccountType checking = AccountType.CHECKING;

        AccountNumber acc1 = new AccountNumber(princeton, checking);
        AccountNumber acc2 = new AccountNumber(princeton, checking);
        AccountNumber acc3 = new AccountNumber(princeton, checking);

        System.out.println("Account 1: " + acc1);
        System.out.println("Account 2: " + acc2);
        System.out.println("Account 3: " + acc3);
        System.out.println("Equal? " + acc1.equals(acc2));
        System.out.println("Comparison: " + acc1.compareTo(acc2));

        AccountNumber account = new AccountNumber(new Branch(), new AccountType(), "123456");

        // Call generateRandomNumber correctly
        int randomNum = account.generateRandomDigits(SEED);
        System.out.println("Generated Account Number: " + randomNum);

        // Print account details
        System.out.println(account);
    }
}
