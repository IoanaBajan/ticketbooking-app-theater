package main;

//import gui.LoginFrame;
import gui.LoginFrame;
import model.*;
import repository.AdultRepository;
import repository.CharityEventRepository;
import repository.ChildRepository;
import repository.StudentRepository;
import service.BuyService;
import service.InformationService;
import service.LoginService;
import service.SortService;
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
//        StudentRepository.addStudent(s1);
//        StudentRepository.addStudent(s2);
//        StudentRepository.addStudent(s3);
//        Adult a1 = new Adult("pheobs","locked","Pheobe",40);
//        Adult a2 = new Adult("mon","locked","Monica",39);
//        AdultRepository.addAdult(a1);
//        AdultRepository.addAdult(a2);
        Child c1 = new Child("chandy","locked","Chandler",5,1);
        Child c2 = new Child("joey","locked","Joey",4,0);
        Child c3 = new Child("rossie","12345","Ross",6,1);
        ChildRepository.addChild(c1);
        ChildRepository.addChild(c2);
        InformationService i = new InformationService();
        i.showStudents();
        i.showAdults();
        i.showKids();
//        i.showCharityEvents();
//        i.showConcerts();
//        i.showPlays();
//        CharityEvent e1 = new CharityEvent("01/01/2020","Piesa de teatru2",100);
//        CharityEventRepository.addCharityEvent(e1);
//        i.showCharityEvents();
//        System.out.println("STERGE");
//        CharityEventRepository.removeCharityEvent(e1);
//        i.showCharityEvents();
        SortService s = new SortService();
        for (Event e  : s.sortConcerts()) System.out.println(e.toString());
        LoginService l = new LoginService();

        Student s1 = new Student("yolo123","locked","Ioana",20,21564);
        Student s2 = new Student("apolo89","locked","Matei",19,22111);
        Student s3 = new Student("suleiman","locked","Lori",22,21564);
        if(l.login(s1)) System.out.println("login reusit");;
        l.register(s2);
        l.register(s3);
        i.showStudents();
        Concert f1 = new Concert("03/03/2020","Om la luna",1);
        Concert f2 = new Concert("03/03/2020","Alternosfera",1);
        Concert f3 = new Concert("03/03/2020","Luna Amara",2);
//        TheaterPlay f1 = new TheaterPlay("03/03/2020","50 De Secunde",1,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime");
//        TheaterPlay f2 = new TheaterPlay("08/03/2020","50 De Secunde",2,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime");
//        TheaterPlay f3 = new TheaterPlay("01/03/2020","50 De Secunde",2,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime");
//        CharityEvent f1 = new CharityEvent("03/03/2020","Piesa de teatru1",2);

        BuyService b = new BuyService();
        b.showPrice(s1,f1,1);
        b.showPrice(s1,f1,1);
//        b.showPrice(s2,f2,76);
//        b.showPrice(s3,f3,88);
        b.showPrice(s3,f1,1);
        b.showPrice(s3,f1,1);
        b.showPrice(s3,f1,1);
//        System.out.println(BuyService.soldout);
        StudentRepository x = new StudentRepository();
        ChildRepository x1 = new ChildRepository();
        Adult a4 = new Adult("pheobs","locked","Pheobe",40);
        Adult a5 = new Adult("rach","locked","Rachel",39);
//        x1.addToDtbase(c1);
//        x1.addToDtbase(c3);
        if(l.loginDB(s1)) System.out.println("login reusit");;
//        l.register(a5);
        l.register(s3);
//        x.addToDtbase(s1);
//        x.addToDtbase(s2);
//        x.addToDtbase(s3);

    }
}
