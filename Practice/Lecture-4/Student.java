package webreg;

public class Student {
    private String id;
    private String name;

}

public Student(String id, String name) {
    this.id= id;
    this.name = name;
}

public String toString() {
    return id + " " + name;
}

@Override
public boolean equals(Object obj) {
    if(obj instanceof Student) {
        Student student = (Student) obj;
        return student.id.equals(this.id)
    }
}

public static void main(String[] args) {
    Student s1 = new Student("1", "Lily");
    Student s2 = new Student("1", "Lily");
    System.out.println(s1.equals(s2));
}

@Override
public int compreTo(Student student) {
    if (id.compareTo(student.id) < 0) {

    }
}