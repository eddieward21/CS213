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
            System.out.println("empty.");
            return;
        }

        System.out.println("printLL(): ");
        while (current != null) {
            System.out.println(current.account);
            current = current.next;
        }
    }



    public static void main(String[] args) {

    }


}
