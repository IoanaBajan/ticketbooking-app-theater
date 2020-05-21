package repository;

import exceptions.InexistentFileException;
import model.Child;
import model.Student;
import service.AuditService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile(c.getUsername()+" registered",t.getName());
        } catch (IOException e) {
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
            return Optional.of(student);
        }catch (IOException e){
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("searched for "+ username,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Student> getStudents() {
        Path path = Paths.get(file);
        ArrayList<Student> students = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                Student student = new Student();
                student.setUsername(attr[0]);
                student.setPassword(attr[1]);
                student.setFirstName(attr[2]);
                student.setStudentIdNo(Integer.parseInt(attr[3]));

                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
    @Override
    public void showStudents() {
        for (int i = 0; i < getStudents().size(); i++) {
            System.out.println(getStudents().get(i).toString());
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("printed all students ",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
