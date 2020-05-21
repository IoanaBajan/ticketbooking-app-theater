package service;


import model.*;
import repository.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class InformationService {
    AdultRepository adultRepository = AdultRepository.build(AdultRepository.Type.ARRAY);
    StudentRepository studentRepository = StudentRepository.build(StudentRepository.Type.ARRAY);
    ChildRepository childRepository = ChildRepository.build(ChildRepository.Type.DB);
    TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.build(TheaterPlaysRepository.Type.ARRAY);
    ConcertRepository concertRepository = ConcertRepository.build(ConcertRepository.Type.ARRAY);

    public void showInfo(){
        AuditService audit = new AuditService();
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
            if(L.login(u)) {
                System.out.println("Login successful");
                try {
                    audit.addToAuditFile("LOGIN", threadName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Login unsuccessful");
                try {
                    audit.addToAuditFile("Failed try to login", threadName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
                    case 1:{
                        theaterPlaysRepository.showPlays();
                    }
                        break;
                    case 2:
                        concertRepository.showConcerts();
                        break;
                    case 3: {
                        System.out.println("1.Plays  2.Concerts");
                        int c = s.nextInt();
                        if (c == 1) {
                            System.out.println("enter name of the play");
                            Scanner scanner = new Scanner(System.in);
                            String name = scanner.nextLine();
                            System.out.println(name);
                            TheaterPlay result = theaterPlaysRepository.findPlay(name);
                            if(result!=null){
                                System.out.println(result.toString());
                            }else System.out.println("Couldn't find this event");
                        } else if (c == 2) {
                            System.out.println("enter name of the concert");
                            Scanner scanner = new Scanner(System.in);
                            String name = scanner.nextLine();
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
                    default: {
                        System.out.println("Choose a valid number");
                        break;
                    }
                }

            }
        }else if(answer == 3){
            System.out.println("enter username");
            String username = s.next();
            System.out.println("enter password");
            String password = s.next();
            User user = new User(username,password);

            System.out.println("enter passphrase");
            if(s.next().equals("123") && LoginService.getInstance().login(user)) {
                int typeOfService = 1;
                while (typeOfService != 0) {
                System.out.println("1.show all clients");
                System.out.println("2.add event");
                System.out.println("3.delete event");
                System.out.println("4.update event");
                typeOfService = s.nextInt();

                switch (typeOfService) {
                    case 1:{
                        adultRepository.showAdults();
                        studentRepository.showStudents();
                        childRepository.showChildren();
                    }
                    break;
                    case 2:{
                        System.out.println("1.Theater play   2.Concert");
                        int choice = s.nextInt();
                        if(choice == 1){
                            System.out.println("enter play's name");
                            Scanner scanner = new Scanner(System.in).useDelimiter(" ");
                            String name = scanner.nextLine();
                            System.out.println("enter date in this format yyyy-mm-dd(starting when will this play be performed?) ");
                            String date = scanner.nextLine();
                            System.out.println("enter maximum number of seats");
                            Integer maxNumberSeats = Integer.parseInt(scanner.nextLine());
                            System.out.println("enter director's name");
                            String director = scanner.nextLine();
                            System.out.println("enter actors' name");
                            String actors = scanner.nextLine();
                            System.out.println("enter the end date in this format yyyy-mm-dd(until what date will this play be performed?)");
                            String endDate = scanner.nextLine();
                            System.out.println("enter number of representation during the current season");
                            Integer representations =Integer.parseInt(scanner.nextLine());
                            TheaterPlay t = new TheaterPlay(date, name, maxNumberSeats, director, actors, endDate, representations);
                            theaterPlaysRepository.addPlay(t);
                        }else if(choice == 2){
                            System.out.println("enter concert's name");
                            Scanner scanner = new Scanner(System.in).useDelimiter(" ");
                            String name = scanner.nextLine();
                            System.out.println("enter date in this format yyyy-mm-dd(on what day the concert will be performed) ");
                            String date = scanner.nextLine();
                            System.out.println("enter maximum number of seats");
                            Integer maxNumberSeats = Integer.parseInt(scanner.nextLine());
                            System.out.println("enter performers' name");
                            String performers = scanner.nextLine();
                            Concert c = new Concert(date, name, maxNumberSeats,performers);
                            concertRepository.addConcert(c);
                        }else System.out.println("Choose a valid number please");
                    }break;
                    case 3:{
                        System.out.println("1.Theater play   2.Concert");
                        Scanner scanner = new Scanner(System.in).useDelimiter(" ");
                        int choice = s.nextInt();
                        if(choice == 1){
                            System.out.println("enter play's name");
                            String name = scanner.nextLine();
                            theaterPlaysRepository.deletePlay(name);
                        }else if (choice == 2){
                            System.out.println("enter concert's name");
                            String name = scanner.nextLine();
                            concertRepository.deleteConcert(name);
                        }
                    }break;
                    case 4:{
                        Scanner scanner = new Scanner(System.in).useDelimiter(" ");
                            System.out.println("enter play's name");
                            String name = scanner.nextLine();
                            System.out.println("select atribute to be update 1.actors 2.startDate 3.endDate");
                            Integer x = Integer.parseInt(scanner.nextLine());
                            System.out.println("enter new value");
                            String newValue = scanner.nextLine();
                            if(x == 1) theaterPlaysRepository.updatePlay(name,"actors",newValue);
                            else if(x == 2) theaterPlaysRepository.updatePlay(name,"startDate",newValue);
                            else if(x == 3) theaterPlaysRepository.updatePlay(name,"endDate",newValue);
                    }break;
                    default: {
                        System.out.println("Choose a valid number");
                        break;
                    }
                }
                }
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
