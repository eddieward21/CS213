package account_type;

/**
 * @author Eddie Ward
 * The account_type.AccountType represents different types of bank accounts.
 * account.account.Account types include:
 * - CHECKING (code: "01")
 * - SAVINGS (code: "02")
 * - MONEYMARKET (code: "03")
 *
 * This enum provides methods to retrieve an account type based on a code or name.
 */
public enum AccountType {
    CHECKING("01"),
    SAVINGS("02"),
    MONEYMARKET("03");

    /**
     * code for the accountType
     */
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
     * Retrieves the account_type.AccountType corresponding to a given code.
     *
     * @param code The string code to search for.
     * @return The matching account_type.AccountType.
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
     * Retrieves the account_type.AccountType based on its name.
     *
     * @param name The string name of the account type.
     * @return The matching account_type.AccountType or null if the name is invalid.
     */
    public static AccountType accountTypeFromName(String name) {
        try {
            return AccountType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Runs test cases to verify the functionality of the account_type.AccountType enum.
     */
    public static void testAccountType() {
        System.out.println("testAccountType()... ");

        System.out.println("Testing 01: ");
        AccountType type1 = AccountType.accountTypeFromCode("01");
        System.out.println(type1);
    }


    public static void main(String[] args) {
        testAccountType();
    }
}
