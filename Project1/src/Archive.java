/**
 * The Archive class stores accounts in a linked list.
 * @author Eddie Ward
 */
public class Archive {
    private AccountNode first; // Head of the linked list

    /**
     * The AccountNode class represents a node in the linked list.
     */
    private static class AccountNode {
        Account account; // The account stored in this node
        AccountNode next; // Pointer to the next node

        /**
         * Constructor for AccountNode.
         * @param account The account to store.
         */
        public AccountNode(Account account) {
            this.account = account;
            this.next = null;
        }
    }

    /**
     * Adds a new account to the list.
     * @param account The account to add.
     */
    public void add(Account account) {
        AccountNode newNode = new AccountNode(account);
        newNode.next = first;
        first = newNode;
    }


    /**
     * Prints all accounts in the linked list.
     */
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

    /**
     * Tests the Archive class.
     */
    public static void testArchive() {
        System.out.println("testArchive(): ");
        Archive archive = new Archive();

        Account account1 = new Account("12345", "Eddie Ward", 725);
        archive.add(account1);
        archive.printLL();
    }

    /**
     * Main method to run tests.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        testArchive();
    }
}
