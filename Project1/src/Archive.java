public class Archive {
    private AccountNode first;

    private static class AccountNode {
        Account account;
        AccountNode next;

        public AccountNode(Account account) {
            this.account = account;
            this.next = null;
        }
    }

    public void add(Account account) {
        AccountNode newNode = new AccountNode(account);
        newNode.next = first;
        first = newNode;
    }


    public void printLL() {
        AccountNode current = first;
        if (current == null) {
            System.out.println("No Accounts in the Archive.");
            return;
        }

        System.out.println("\n*List of closed accounts in the archive.");
        while (current != null) {
            System.out.println(current.account);
            current = current.next;
        }
        System.out.println("*end of list.\n");

    }



    public static void main(String[] args) {

    }


}
