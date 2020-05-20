package repository;

import model.Student;

import java.util.Optional;

public interface StudentRepository {
    void addStudent(Student c);
    Optional <Student> findUserByName(String username);

    static StudentRepository build(Type type) {
        switch (type){
            case DB: return new DBStudentRepository();
            case FILE: return new FileStudentRepository();

        }
        throw  new RuntimeException("no such type");
    }
    enum Type{
        DB, FILE
    }

}
