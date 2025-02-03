public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;


    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public String getFName(){
        return fname;
    }

    public String getLName(){
        return lname;
    }

    public Date getDOB() {
        return dob;
    }

    @Override
    public String toString(){
        return fname + lname + dob.toString(); // Must be implemented?
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
    public int compareTo(){
        //What are we comparing?
    }



    public static void main(String[] args) {
        //Need testbed for compare functions

    }
}
