/**
 * The AccountType represents different types of bank accounts.
 * Account types include:
 * - CHECKING (code: "01")
 * - SAVINGS (code: "02")
 * - MONEYMARKET (code: "03")
 *
 * This enum provides methods to retrieve an account type based on a code or name.
 *  * @author Eddie Ward
 */
public enum AccountType {
    /**
     * Checking
     */
    CHECKING("01"),
    /**
     * Savings
     */
    SAVINGS("02"),
    /**
     * Moneymarket
     */
    MONEYMARKET("03"),
    /**
     * Regular Savings Account
     */
    REGULARSAVINGS("02"),
    /**
     * Moneymarket Savings
     */
    MONEYMARKETSAVINGS("03");

    private final String code;

    /**
     * Constructor to associate a code with each account type.
     *
     * @param code The string representation of the account type.
     */
    AccountType(String code) {
        this.code = code;
    }

    /**
     * Retrieves the code associated with the account type.
     *
     * @return The string code of the account type.
     */
    public String getCode() {
        return code;
    }

    /**
     * Retrieves the AccountType corresponding to a given code.
     *
     * @param code The string code to search for.
     * @return The matching AccountType.
     * @throws IllegalArgumentException if no matching type is found.
     */
    public static AccountType accountTypeFromCode(String code) {
        for (AccountType type : AccountType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code " + code);
    }

    /**
     * Retrieves the AccountType based on its name.
     *
     * @param name The string name of the account type.
     * @return The matching AccountType or null if the name is invalid.
     */
    public static AccountType accountTypeFromName(String name) {
        try {
            return AccountType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Runs test cases to verify the functionality of the AccountType enum.
     */
    public static void testAccountType() {
        System.out.println("testAccountType()... ");

        System.out.println("Testing 01: ");
        AccountType type1 = AccountType.accountTypeFromCode("01");
        System.out.println(type1);
    }

    /**
     * Main method to execute test cases.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        testAccountType();
    }
}
