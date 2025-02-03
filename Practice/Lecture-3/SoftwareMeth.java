package examples;


public class SoftwareMeth {
    private Faculty faculty;
    private Student [] ta;
    private Student [] students;
    private Location room;
    private String courseid;
}

//parameterized constructor
public SoftwareMeth(Faculty faculty, Location room, String courseid, String time) {
    this.faculty = faculty;
    this.room = room;
    this.courseid = courseid;
    this.time = time;
}

//copy constructor:
public SoftwareMeth(SoftwareMeth softwareMeth) {
    this.room = softwareMeth.room;
    this.courseid = softwareMeth.courseid;
    this.time = softwareMeth.time;
}