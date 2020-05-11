package model;

public class Student extends Client {
    private int studenId;
    public Student(String username, String password) {
        super(username, password);
    }
    public String toString() {
        return "Student{" + super.toString()+
                '}';
    }
}
