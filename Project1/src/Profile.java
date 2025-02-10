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
            String personDOB = dob.toString();
            return person.fname.equals(this.fname) && person.lname.equals(this.lname) && personDOB.equals(this.dob.toString());
        }
        return false;
    }

    @Override
    public int compareTo(Profile profile){
        int lnameComp = this.lname.compareTo(profile.lname);
        if (lnameComp != 0) {
            return lnameComp;
        }

        int fnamComp = this.fname.compareTo(profile.fname);
        if (fnamComp != 0) {
            return fnamComp;
        }

        return this.dob.compareTo(profile.dob);
    }



    public static void main(String[] args) {
        //Need testbed for compare functions

    }
}
