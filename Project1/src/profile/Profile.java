package profile;

import date.Date;

/**
 * This class represents a profile.Profile with a first name, last name, and date of birth.
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
        int lnameComp = this.lname.toLowerCase().compareTo(profile.lname.toLowerCase());
        if (lnameComp != 0) {
            return lnameComp;
        }

        int fnameComp = this.fname.toLowerCase().compareTo(profile.fname.toLowerCase());
        if (fnameComp != 0) {
            return fnameComp;
        }

        return this.dob.compareTo(profile.dob);
    }




    public static void main(String[] args) {
        Profile p1 = new Profile("John", "Adams", new Date(1990, 5, 15));
        Profile p2 = new Profile("Mike", "Brown", new Date(1990, 5, 15));
        System.out.println("Test case 1 (Different last names):");
        System.out.println("Comparing: " + p1 + " with " + p2);
        System.out.println("Expected: -1, Actual: " + (p1.compareTo(p2) < 0 ? -1 : 1));
        System.out.println();

        Profile p3 = new Profile("Alice", "Smith", new Date(1990, 5, 15));
        Profile p4 = new Profile("Bob", "Smith", new Date(1990, 5, 15));
        System.out.println("Test case 2 (Same last name, different first names):");
        System.out.println("Comparing: " + p3 + " with " + p4);
        System.out.println("Expected: -1, Actual: " + (p3.compareTo(p4) < 0 ? -1 : 1));
        System.out.println();

        Profile p5 = new Profile("John", "Smith", new Date(1990, 5, 15));
        Profile p6 = new Profile("John", "Smith", new Date(1995, 5, 15));
        System.out.println("Test case 3 (Same names, different DOB):");
        System.out.println("Comparing: " + p5 + " with " + p6);
        System.out.println("Expected: -1, Actual: " + (p5.compareTo(p6) < 0 ? -1 : 1));
        System.out.println();

        Profile p7 = new Profile("John", "Wilson", new Date(1990, 5, 15));
        Profile p8 = new Profile("Mike", "Thompson", new Date(1990, 5, 15));
        System.out.println("Test case 4 (Different last names):");
        System.out.println("Comparing: " + p7 + " with " + p8);
        System.out.println("Expected: 1, Actual: " + (p7.compareTo(p8) > 0 ? 1 : -1));
        System.out.println();

        Profile p9 = new Profile("Mike", "Smith", new Date(1990, 5, 15));
        Profile p10 = new Profile("John", "Smith", new Date(1990, 5, 15));
        System.out.println("Test case 5 (Same last name, different first names):");
        System.out.println("Comparing: " + p9 + " with " + p10);
        System.out.println("Expected: 1, Actual: " + (p9.compareTo(p10) > 0 ? 1 : -1));
        System.out.println();

        Profile p11 = new Profile("John", "Smith", new Date(1995, 5, 15));
        Profile p12 = new Profile("John", "Smith", new Date(1990, 5, 15));
        System.out.println("Test case 6 (Same names, different DOB):");
        System.out.println("Comparing: " + p11 + " with " + p12);
        System.out.println("Expected: 1, Actual: " + (p11.compareTo(p12) > 0 ? 1 : -1));
        System.out.println();

        Profile p13 = new Profile("John", "Smith", new Date(1990, 5, 15));
        Profile p14 = new Profile("John", "Smith", new Date(1990, 5, 15));
        System.out.println("Test case 7 (Identical profiles):");
        System.out.println("Comparing: " + p13 + " with " + p14);
        System.out.println("Expected: 0, Actual: " + p13.compareTo(p14));
        System.out.println();
    }

}
