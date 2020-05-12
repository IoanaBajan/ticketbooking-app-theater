package repository;
import model.Student;

import java.util.ArrayList;
import java.util.Optional;

public class StudentRepository {
    private static ArrayList<Student> students = new ArrayList<> ();
    public ArrayList<Student> getStudents() {
        return students;
    }

    public StudentRepository() {
        this.students.add( new Student("alabala","09876","portocala",23,11299));
    }

    public static void addStudent(Student c) {
        students.add(c);
    }

    public Optional<Student> findUserByUsername(String username) {
        for (Student c : students) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }

        return Optional.empty();
    }

    public static StudentRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static StudentRepository INSTANCE = new StudentRepository();
    }
}
