package model;

import repository.ChildRepository;
import repository.ConcertRepository;
import repository.StudentRepository;
import repository.TheaterPlaysRepository;
import service.BuyService;
import service.InformationService;
import service.LoginService;
import service.SortService;

import java.util.Optional;

public class Theater {


    public void startApplication() {
//      shows objects
        InformationService i = new InformationService();
        i.showAdults();
        i.showKids();
        i.showStudents();
        for(TheaterPlay ev: i.showPlays()){
            System.out.println(ev.toString());
        }
        for(CharityEvent ev: i.showCharityEvents()){
            System.out.println(ev.toString());
        }
        for(Concert ev: i.showConcerts()){
            System.out.println(ev.toString());
        }
        Child c1 = new Child("chandy","locked","Chandler",5,1);
        Child c2 = new Child("joey","locked","Joey",4,0);
        Child c3 = new Child("rossie","12345","Ross",6,1);
        ChildRepository.addChild(c1);
        ChildRepository.addChild(c2);
        ChildRepository.addChild(c3);

        LoginService l = new LoginService();
        Student s1 = new Student("yolo123","locked","Ioana",20,21564);
        Student s2 = new Student("apolo89","locked","Matei",19,22111);
        Student s3 = new Student("suleiman","locked","Lori",22,21564);
        if(l.login(s1)) System.out.println("login reusit");;
        l.register(s2);
        l.register(s3);
        StudentRepository S = new StudentRepository();
        Optional<Student> s = S.findUserByUsername("yolo123");
        System.out.println("User found" + s.toString());

        Concert f1 = new Concert("2020-03-03","Om la luna",1);
        Concert f2 = new Concert("2020-03-02","Alternosfera",50);
        ConcertRepository.addConcert(f1);
        ConcertRepository.addConcert(f2);

//      calculates the price of a ticket depending on the type of client(applies discount if neccessary)
        BuyService b = new BuyService();
        int price1=b.showPrice(s1,f1,1);
        int price2=b.showPrice(s2,f1,1);
        if(price1!=-1)
            System.out.println("PRICE FOR CONCERT" + price1);
        else System.out.println("event is soldout");
        if(price2!=-1)
        System.out.println("PRICE FOR CONCERT" +price2);
        else System.out.println("event soldout");

//      sorts events by date or by name
        SortService sort = new SortService();
        for (Event e  : sort.sortConcerts()) System.out.println(e.toString());
        System.out.println("/////////////////////////////////////////////////");
        for (Event e  : sort.sortConcertsByName()) System.out.println(e.toString());
//      ADD TO DATABASE
        TheaterPlay t1 = new TheaterPlay("2020-03-02","50 De Secunde",60,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime");
        TheaterPlaysRepository.addToDtbase(t1);

    }

}
