package repository;

import model.Concert;
import model.Student;

import java.util.ArrayList;
import java.util.Optional;

public interface StudentRepository {
    void addStudent(Student c);
    Optional <Student> findUserByName(String username);
    ArrayList<Student> getStudents();
    void showStudents();
    static StudentRepository build(Type type) {
        switch (type){
            case DB: return new DBStudentRepository();
            case FILE: return new FileStudentRepository();
            case ARRAY: return new ArrayStudentRepository();

        }
        throw  new RuntimeException("no such type");
    }
    enum Type{
        DB, FILE,ARRAY
    }

}
