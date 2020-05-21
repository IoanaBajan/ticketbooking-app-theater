package repository;

import model.Concert;
import model.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.Optional;

public class ArrayStudentRepository implements StudentRepository {
    private ArrayList<Student> students = new ArrayList<> ();

    @Override
    public void addStudent(Student c) {
        students.add(c);
    }

    @Override
    public Optional<Student> findUserByName(String username) {
        for (Student c : students) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();

    }

    @Override
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void showStudents() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).toString());
        }
    }
}
