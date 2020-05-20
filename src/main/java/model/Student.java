package model;

public class Student extends Client {
    private int studentIdNo;

    public Student() {

    }

    public int getStudentIdNo() {
        return studentIdNo;
    }

    public void setStudentIdNo(int studentIdNo) {
        this.studentIdNo = studentIdNo;
    }

    public Student(String username, String password, String firstName, int studentIdNo) {
        super(username, password,firstName);
        this.studentIdNo = studentIdNo;
    }
    public String toString() {
        return "Student " + super.toString()+
                "  Numar id " +studentIdNo;
    }
}
