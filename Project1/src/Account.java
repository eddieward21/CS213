public class Account implements Comparable<Account> {

    private AccountNumber number;
    private Profile holder;
    private double balance;


    /**
     *  Constructor to initialize the private variables
     * @param number the users account number
     * @param holder the name of the holder
     * @param balance the balance of the account
     */
    public Account(AccountNumber number, Profile holder, double balance) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }



    /**
     * To withdraw money from the account
     * @param amount the user would like to withdraw
     * @return 1 if deposit was valid, 0 if not valid
     */
    public int withdraw(double amount) {
        if (amount > 0 && amount >= balance){
            balance -= amount; // Done in the database?
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * To deposit money into the account
     * @param amount the user would like to deposit
     * @return 1 if deposit was valid, 0 if not valid
     */
    public int deposit(double amount) {
        if (amount > 0) {
            balance += amount; //Done in the database?
            return 1;
        }
        else{
            return 0;
        }
    }


    @Override
    public boolean equals(Object obj){
        if(obj instanceof Account){
            Account account = (Account) obj;

            boolean profileComp = account.holder.equals(this.holder);
            if(profileComp){
                return account.number.getAccountTypeCode().equals(this.number.getAccountTypeCode());
            }
        }
        return false;
    }


    @Override
    public String toString(){
        return "Account*[" + this.number.getNumber() + "] Holder[" + this.holder.toString() + "] Balance[$" + this.balance + "] Branch [" + this.number.getBranch() + "]";
    }



    @Override
    public int compareTo(Account account) {
        return Integer.compare(Integer.parseInt(this.number.getNumber()), account.getNumber());
    }

    /**
     * A getter method to get the users account number
     * @return the users acount number
     */
    public int getNumber() {
        return Integer.parseInt(number.getNumber());
    }




    /**
     * A getter method to get the accounts balance
     * @return the accounts balance
     */
    public double getBalance() {
        return balance;
    }

    public AccountNumber getAccountNumber(){
        return this.number;
    }


    public static void main(String[] args) {


    }

}
