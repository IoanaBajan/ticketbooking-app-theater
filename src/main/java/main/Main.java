package main;

//import gui*;
import gui.Login;
import javafx.application.Application;
import model.Student;
import repository.StudentRepository;
import service.LoginService;

public class Main {
    public static void main(String[] args) {


        Student s1 = new Student("yo23","locked","Ioana",20,21564);
        Student s2 = new Student("apolo89","locked","Matei",19,22111);
        Student s3 = new Student("suleiman","locked","Lori",22,21564);
        StudentRepository s = new StudentRepository();
        LoginService L = new LoginService();

//        if(StudentRepository.findUserInDB("123")!=null){
//            System.out.println("PRESENT");
//        }else System.out.println("cant be found");
        System.out.println("Hello, this is your project speaking");
        Application.launch(Login.class,args);
    }
}
