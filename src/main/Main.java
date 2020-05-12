package main;

//import gui.LoginFrame;
import gui.LoginFrame;
import model.*;
import repository.AdultRepository;
import repository.CharityEventRepository;
import repository.ChildRepository;
import repository.StudentRepository;
import service.InformationService;
//import service.SortService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is your project speaking");
//        new LoginFrame();
//        Theater t = new Theater();
//        t.showInformationUser();
//        SortService e = new SortService();
//        for(Event i :e.sortName())  {
//            System.out.println(i.toString());
//        }
        Student s1 = new Student("yolo123","locked","Ioana",20,21564);
        Student s2 = new Student("apolo89","locked","Matei",19,22111);
        Student s3 = new Student("suleiman","locked","Lori",22,21564);
        StudentRepository.addStudent(s1);
        StudentRepository.addStudent(s2);
        StudentRepository.addStudent(s3);
        Adult a1 = new Adult("pheobs","locked","Pheobe",40);
        Adult a2 = new Adult("mon","locked","Monica",39);
        AdultRepository.addAdult(a1);
        AdultRepository.addAdult(a2);
        Child c1 = new Child("chandy","locked","Chandler",5,true);
        Child c2 = new Child("joey","locked","Joey",4,false);
        ChildRepository.addChild(c1);
        ChildRepository.addChild(c2);
        InformationService i = new InformationService();
        i.showStudents();
        i.showAdults();
        i.showKids();
        i.showCharityEvents();
        i.showConcerts();
        i.showPlays();
        CharityEvent e = new CharityEvent("01/01/2020","Piesa de teatru2",100);
        CharityEventRepository.addCharityEvent(e);
        i.showCharityEvents();
        System.out.println("STERGE");
        CharityEventRepository.removeCharityEvent(e);
        i.showCharityEvents();

    }
}
