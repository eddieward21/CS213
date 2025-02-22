/**
 * Enum represents different branches of the bank.
 * Contains information such as the branch's zipcode, branch code, and county.
 * The enum also includes methods to retrieve branch information by branch code or branch name.
 *
 * @author George Seriani
 */
public enum Branch {
    /**
     * Edison, Bridgewater, Princeton, Piscataway, Warren locations for Branch. Zipcode, Branch code, County.
     */
    EDISON("08817", "100", "Middlesex"),
    /**
     * Bridgewater
     */
    BRIDGEWATER("08807", "200", "Somerset"),
    /**
     * Princeton
     */
    PRINCETON("08540", "300", "Mercer"),
    /**
     * Piscataway
     */
    PISCATAWAY("08854", "400", "Middlesex"),
    /**
     * Warren
     */
    WARREN("07059", "500", "Somerset");

    private final String zip;
    private final String branchCode;
    private final String county;

    /**
     *  Constructor to initialize the private variables
     * @param zip the branch zipcode
     * @param branchCode the branch code
     * @param county the branch county
     */
    Branch(String zip, String branchCode, String county) {
        this.zip = zip;
        this.branchCode = branchCode;
        this.county = county;
    }

    /**
     * A getter method to get the value of Zip
     * @return the zipcode of the branch
     */
    public String getZip() {
        return zip;
    }

    /**
     *  A getter method to ge the value of branch code
     * @return the branch code of the branch
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * A getter method to get the value of the county
     * @return the county of the branch
     */
    public String getCounty() {
        return county;
    }




    /**
     * Matching the Branch code to the correct branch
     * @param branchCode the branch code of the current account
     * @return the branch name of the branch code we are searching
     */
    public static String getBranchByCode(String branchCode) {
        Branch[] branches = Branch.values();
        for (Branch branch : branches) {
            if (branch.getBranchCode().equals(branchCode)) {
                return branch.toString();
            }
        }
        return null;
    }

    /**
     * Getting the Branch name given the String
     * @param branchName the String literal of the Brnch name
     * @return The Branch name
     */
    public static Branch getBranchByName(String branchName) {
        try {
            return Branch.valueOf(branchName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Testing method for the branch
     */
    public static void testBranch() {
        System.out.println("testBranch(): ");

        Branch testBranch = Branch.EDISON;
        System.out.println("Testing EDISON: ");
        System.out.println("Zip: " + testBranch.getZip());
        System.out.println("Branch Code: " + testBranch.getBranchCode());
        System.out.println("County: " + testBranch.getCounty());
    }

    /**
     * calls the testBranch() method.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        testBranch();
    }
}
