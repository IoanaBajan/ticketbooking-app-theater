package main;

import gui.LoginFrame;
//import gui*;
import gui.Login;
import javafx.application.Application;
import model.Student;
import repository.StudentRepository;
import service.SortService;

public class Main {
    public static void main(String[] args) {


        Student s1 = new Student("yolo123","locked","Ioana",20,21564);
        Student s2 = new Student("apolo89","locked","Matei",19,22111);
        Student s3 = new Student("suleiman","locked","Lori",22,21564);
        StudentRepository s = new StudentRepository();
        StudentRepository.addStudent(s1);
        StudentRepository.addStudent(s2);
        StudentRepository.addStudent(s3);
        System.out.println("Hello, this is your project speaking");
        Application.launch(Login.class,args);
    }
}
