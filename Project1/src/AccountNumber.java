import java.util.Objects;
import java.util.Random;

public class AccountNumber implements Comparable<AccountNumber> {
    private static final int SEED = 9999;  // Fixed: Constant should not be modified
    private Branch branch;
    private AccountType type;
    private String number;

    // Constructor
    public AccountNumber(Branch branch, AccountType type, String number) {
        this.branch = branch;
        this.type = type;
        this.number = number + generateRandomDigits();
    }

    // Generate a 4-digit random number based on a seed
    private String generateRandomDigits() {
        Random random = new Random(SEED);
        int randomNumber = random.nextInt(9000) + 1000;
        return String.valueOf(randomNumber);
        this.number = number;
    }

    // Generate a 4-digit random number based on a seed
    public int generateRandomNumber(int seed) {
        Random random = new Random(seed);
        return 1000 + random.nextInt(9000);  // Fixed: Corrected nextInt()
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
        return Branch.getBranchByCode(this.number.substring(0, 3));
    }

    public String getNumber(){
        return this.number;
    }

    public AccountType getType(){
        return this.type;
    }

    public String getAccountTypeCode() {
        return type.getCode();  // Call getCode() method of AccountType enum
    }


    // Implement compareTo() for sorting
    @Override
    public int compareTo(AccountNumber other) {
        return this.number.compareTo(other.number);
    }

    public static void main(String[] args) {

        AccountNumber account = new AccountNumber(new Branch(), new AccountType(), "123456");

        // Call generateRandomNumber correctly
        int randomNum = account.generateRandomNumber(SEED);
        System.out.println("Generated Account Number: " + randomNum);

        // Print account details
        System.out.println(account);
    }
}
