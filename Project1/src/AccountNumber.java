import java.util.Objects;
import java.util.Random;

public class AccountNumber implements Comparable<AccountNumber> {
    private static final int SEED = 9999;  // Fixed: Constant should not be modified
    private static final Random random = new Random(SEED);
    private Branch branch;
    private AccountType type;
    private String number;

    // Constructor
    public AccountNumber(Branch branch, AccountType type) {
        this.branch = branch;
        this.type = type;
        this.number = generateRandomDigits();
    }

    // Generate a 4-digit random number based on a seed
    private String generateRandomDigits() {
        int randomNumber = random.nextInt(9999);
        return String.format("%04d", randomNumber);
    }

    // Override equals() for object comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AccountNumber that = (AccountNumber) obj;
        return Objects.equals(branch, that.branch) &&
                Objects.equals(type, that.type) &&
                Objects.equals(number, that.number);
    }

    // Override toString() for a readable format
    @Override
    public String toString() {
        return "AccountNumber{" +
                "branch=" + branch +
                ", type=" + type +
                ", number='" + number + '\'' +
                '}';
    }

    public String getBranch(){
        return branch.toString();
    }

    public String getNumber(){
        return this.number;
    }

    public String getAccountNumber(){
        return this.branch.getBranchCode() + this.type.getCode() + this.number;
    }

    public AccountType getType(){
        return this.type;
    }

    public String getAccountTypeCode() {
        return type.getCode();  // Call getCode() method of AccountType enum
    }

    public void downgradeToSavings(){
        this.type = AccountType.SAVINGS;
    }

    // Implement compareTo() for sorting
    @Override
    public int compareTo(AccountNumber other) {
        return this.toString().compareTo(other.toString());

    }

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
    }
}
