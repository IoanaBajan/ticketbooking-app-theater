package model;

public class Student extends Client {
    private int studentIdNo;
    public Student(String username, String password,String firstName, int age,int studentIdNo) {
        super(username, password,firstName, age);
        this.studentIdNo = studentIdNo;
    }
    public String toString() {
        return "Student " + super.toString()+
                "  Numar id " +studentIdNo;
    }
}
