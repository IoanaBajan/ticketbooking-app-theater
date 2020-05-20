package repository;

import model.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.Optional;

public class ArrayStudentRepository implements StudentRepository {
    private static ArrayList<Student> students = new ArrayList<> ();

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
}
