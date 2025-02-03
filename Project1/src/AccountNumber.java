import java.util.Objects;
import java.util.Random;

public class AccountNumber implements Comparable<AccountNumber> {
    private static final int SEED = 9999;
    private Branch branch;
    private AccountType type;
    private String number;

    public AccountNumber(int SEED, Branch branch, AccountType type, String number) {
        this.SEED = SEED;
        this.branch = branch;
        this.type = type;
        this.number = number;
    }

    public int generateRandomNumber(int seed) {
        Random random = new Random(SEED);
        int fourDigitNumber = 1000 + random.NextInt(9000);
    }

    @Override
    public boolean equals(Object obj) {

    }

    @Override
    public String toString() {

    }

    @Override
    public String compareTo() {

    }

    public static void main(String[] args) {
        System.out.println(generateRandomNumber(9999));
    }
}