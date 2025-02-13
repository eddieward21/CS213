public enum AccountType {
    CHECKING("01"),
    REGULARSAVINGS("02"),
    MONEYMARKETSAVINGS("03");

    private final String code;

    AccountType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AccountType accountTypeFromCode(String code) {
        for(AccountType type: AccountType.values() ) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code " + code);
    }

    public static void testAccountType () {
        System.out.println("testAccountType()... ");

        System.out.println("Testing 01: ");
        AccountType type1 = AccountType.accountTypeFromCode("01");
        System.out.println(type1);
    }

    public static void main(String[] args) {
        testAccountType();
    }
    testAccountType();
}