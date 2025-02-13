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

    public static void testArchive() {
        System.out.println("testArchive(): ");
        Archive archive = new Archive();

        Account account1 = new Account("12345", "Eddie Ward", 725);

        archive.add(account1);

        archive.printLL();
    }

    public static void main(String[] args) {
        testArchive();
    }


}
