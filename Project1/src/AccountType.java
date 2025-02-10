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

    public static AccountType accountTypeFromName(String name) {
        try {
            return AccountType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid account type name: " + name);
        }
    }



    public static void main(String[] args) {

    }

}
