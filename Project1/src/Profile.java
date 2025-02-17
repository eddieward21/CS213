/**
 * This class represents a Profile with a first name, last name, and date of birth.
 * It implements the Comparable interface to allow profile comparisons.
 *
 * @author George Seriani
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     *  Constructor to initialize the private variables
     * @param fname the users first name
     * @param lname the users last name
     * @param dob the users dob
     */
    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * A getter method to return the users first name
     * @return the users first name
     */
    public String getFName(){
        return fname;
    }

    /**
     * A getter method to return the users last name
     * @return the users last name
     */
    public String getLName(){
        return lname;
    }

    /**
     * A getter method to return the users dob
     * @return the users dob
     */
    public Date getDOB() {
        return dob;
    }

    @Override
    public String toString(){
        return fname + " " + lname + " " + dob.toString(); // Must be implemented?
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Profile){
            Profile person = (Profile) obj;
            return person.fname.toLowerCase().equals(this.fname.toLowerCase()) && person.lname.toLowerCase().equals(this.lname.toLowerCase()) && person.dob.equals(this.dob);
        }
        return false;
    }

    @Override
    public int compareTo(Profile profile) {
        int lnameComp = this.lname.compareTo(profile.lname);
        if (lnameComp != 0) {
            return lnameComp;
        }

        int fnameComp = this.fname.compareTo(profile.fname);
        if (fnameComp != 0) {
            return fnameComp;
        }

        return this.dob.compareTo(profile.dob);
    }


    /**
     * Tests profiles
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //Need testbed for compare functions
        Date date1 = new Date(2000, 1, 1);
        Date date2 = new Date(1999, 10, 25);
        Date date3 = new Date(2000, 1, 1);

        Profile p1 = new Profile("John", "Doe", date1);
        Profile p2 = new Profile("Alice", "Smith", date2);
        Profile p3 = new Profile("John", "Doe", date3);

        // Testing toString()
        System.out.println("Testing toString():");
        System.out.println("p1: " + p1.toString());
        System.out.println("p2: " + p2.toString());
        System.out.println("p3: " + p3.toString());

        // Testing equals()
        System.out.println("\nTesting equals():");
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1.equals(p3): " + p1.equals(p3));

        // Testing compareTo()
        System.out.println("\nTesting compareTo():");
        System.out.println("p1.compareTo(p2): " + p1.compareTo(p2)); // Expected: positive (Doe > Smith)
        System.out.println("p2.compareTo(p1): " + p2.compareTo(p1)); // Expected: negative (Smith < Doe)
        System.out.println("p1.compareTo(p3): " + p1.compareTo(p3)); // Expected: 0

        System.out.println("\nAll tests completed.");

    }
}
