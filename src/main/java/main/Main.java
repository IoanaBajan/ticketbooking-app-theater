package main;

import gui.Login;
import javafx.application.Application;
import model.*;
import repository.ConcertRepository;
import repository.TheaterPlaysRepository;
import service.BuyService;
import service.InformationService;
import service.LoginService;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("How do you want to acces the application\n 1.Interface 2.Console\n **you can buy tickets only through interface");
        int choice = s.nextInt();

        if(choice == 1){
            Application.launch(Login.class, args);
        }else if(choice == 2){
            InformationService i = new InformationService();
            i.showInfo();
        }else System.out.println("Please choose a valid number");

    }
}