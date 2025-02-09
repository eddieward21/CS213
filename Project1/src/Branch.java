public enum Branch {
    EDISON("08817", "100", "Middlesex"),
    BRIDGEWATER("08807", "200", "Somerset"),
    PRINCETON("08540", "300", "Mercer"),
    PISCATAWAY("08854", "400", "Middlesex"),
    WARREN("07059", "500", "Somerset");

    private final String zip;
    private final String branchCode;
    private final String county;

    Branch(String zip, String branchCode, String county) {
        this.zip = zip;
        this.branchCode = branchCode;
        this.county = county;
    }

    public String getZip() {
        return zip;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public String getCounty() {
        return county;
    }


    public static void testBranch() {
        System.out.println("testBranch(): ");

        Branch testBranch = Branch.EDISON;
        System.out.println("Testing EDISON: ");
        System.out.println("Zip: " + testBranch.getZip());
        System.out.println("Branch Code: " + testBranch.getBranchCode());
        System.out.println("County: " + testBranch.getCounty());
    }

    public static void main(String[] args) {
        testBranch();
    }
}
