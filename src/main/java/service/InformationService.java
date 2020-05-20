package service;


import gui.Login;
import model.*;
import repository.*;

import javax.sound.midi.Soundbank;
import java.util.Optional;
import java.util.Scanner;

public class InformationService {
    AdultRepository adultRepository = AdultRepository.build(AdultRepository.Type.DB);
    StudentRepository studentRepository = StudentRepository.build(StudentRepository.Type.DB);
    ChildRepository childRepository = ChildRepository.build(ChildRepository.Type.DB);
    TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.build(TheaterPlaysRepository.Type.DB);
    ConcertRepository concertRepository = ConcertRepository.build(ConcertRepository.Type.DB);

    public void showInfo(){
        String threadName = Thread.currentThread().getName();
        Scanner s = new Scanner(System.in);
        System.out.println("Meniu");
        System.out.println("Do you have an account?");
        System.out.println("1.Register");
        System.out.println("2.Login");
        System.out.println("3.Login as admin");
        int answer = s.nextInt();
        if(answer == 1){
            System.out.println("Register");
            System.out.println("enter username");
            String username = s.next();
            System.out.println("enter password");
            String password = s.next();
            System.out.println("enter first name");
            String first_name = s.next();
            System.out.println("Choose between 1.Adult 2.Student 3.Child");
            int type = s.nextInt();
            if(type == 1){
                Adult a = new Adult(username,password,first_name);
                adultRepository.addAdult(a);
                System.out.println("you've been registered");
            }
            else if(type == 2){
                System.out.println("enter student id");
                int stid = s.nextInt();
                Student st = new Student(username,password,first_name,stid);
                studentRepository.addStudent(st);
                System.out.println("you've been registered");

            }
            else if (type == 3){
                System.out.println("are you accompanied by an adult? \n 1.Yes 2.No");
                if(s.nextInt() == 1){
                    Child c = new Child(username,password,first_name,1);
                    childRepository.addChild(c);
                    System.out.println("you've been registered");

                }else System.out.println("you can't visit us unaccompanied");

            }

        }else if(answer == 2) {
            System.out.println("enter username");
            String username = s.next();
            System.out.println("enter password");
            String password = s.next();
            User u = new User(username,password);
            LoginService L = new LoginService();
            if(L.login(u)) System.out.println("Login successful");
            else System.out.println("Login unsuccessful");
            int typeOfService = 1;
            while (typeOfService != 0) {
                System.out.println("Select service:");
                System.out.println("0.Back");
                System.out.println("1.Show all Theater plays ");
                System.out.println("2.Show all Concerts ");
                System.out.println("3.Find an event by name");
                System.out.println("4.Sort events by date");
                System.out.println("5.Sort events by name");
                typeOfService = s.nextInt();

                switch (typeOfService) {
                    case 0:
                        break;
                    case 1:
                        theaterPlaysRepository.showPlays();
                        break;
                    case 2:
                        concertRepository.showConcerts();
                        break;
                    case 3: {
                        System.out.println("1.Plays  2.Concerts");
                        if (s.nextInt() == 1) {
                            System.out.println("enter name of the play");
                            String name = s.next();
                            Optional<TheaterPlay> result = theaterPlaysRepository.findPlay(name);
                            if(result.isPresent()){
                                System.out.println(result.toString());
                            }else System.out.println("Couldn't find this event");
                        } else if (s.nextInt() == 2) {
                            System.out.println("enter name of the concert");
                            String name = s.next();
                            Optional<Concert> result = concertRepository.findConcert(name);
                            if(result.isPresent()){
                                System.out.println(result.toString());
                            }else {
                                System.out.println("Couldn't find this event");
                                break;
                            }
                        } else System.out.println("enter a valid number :)");
                    }
                    break;
                    case 4:{
                        System.out.println("1.Plays  2.Concerts");
                        SortService sort = new SortService();
                        if (s.nextInt() == 1) {
                            for(Event e : sort.sortPlays()) {
                                System.out.println(e.toString());
                            }
                        } else if (s.nextInt() == 2) {
                            for(Event e : sort.sortConcerts()) {
                                System.out.println(e.toString());
                            }
                        } else System.out.println("enter a valid number :)");

                    }break;
                    case 5:{
                        System.out.println("1.Plays  2.Concerts");
                        SortService sort = new SortService();
                        if (s.nextInt() == 1) {
                            for(Event e : sort.sortPlaysByName()) {
                                System.out.println(e.toString());
                            }
                        } else if (s.nextInt() == 2) {
                            for(Event e : sort.sortConcertsByName()) {
                                System.out.println(e.toString());
                            }
                        } else System.out.println("enter a valid number :)");
                    }
                    break;
                }

            }
        }else if(answer == 3){
            System.out.println("enter username");
            String username = s.next();
            System.out.println("enter password");
            String password = s.next();
            System.out.println("enter passphrase");
            if(s.next().equals(123)) {
                System.out.println("1.show all clients");
                System.out.println("2.add client");
                System.out.println("3.delete event");
                System.out.println("4.add event");
                System.out.println("5.delete event");
                System.out.println("6.update event");

            }

        }
    }
    public static InformationService getInstance() {
        return InformationService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static InformationService INSTANCE = new InformationService();
    }
}
