package repository;

import model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileStudentRepository implements StudentRepository {
    private final String file = "Students.csv";
    @Override
    public void addStudent(Student c) {
        try(PrintWriter out = new PrintWriter(new FileWriter(file,true))){
            out.println(c.getUsername() + "," + c.getPassword()+ "," +c.getFirstName() + "," + c.getStudentIdNo());
        }catch(IOException e ){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Student> findUserByName(String username) {
        Path path = Paths.get(file);
        Student student = new Student();
        try{
            var list = Files.readAllLines(path);
            for (String s:list) {
                String[] attr = s.split(",");
                if (attr[0].equals(username)) {
                    student.setUsername(attr[0]);
                    student.setPassword(attr[1]);
                    student.setFirstName(attr[2]);
                    student.setStudentIdNo(Integer.parseInt(attr[3]));
                    break;
                }
            }
            System.out.println(student.toString());
            return Optional.of(student);
        }catch (IOException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
