package main;

import gui.Login;
import javafx.application.Application;
import model.*;
import repository.ConcertRepository;
import repository.TheaterPlaysRepository;
import service.InformationService;
import service.LoginService;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application.launch(Login.class, args);
        InformationService i = new InformationService();
        i.showInfo();
//        TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.build(TheaterPlaysRepository.Type.FILE);
//        TheaterPlay t = new TheaterPlay("2020-06-20","50 De Secunde",40,"Eugen Gyemant","Alexandru Voicu Diana Dumbrava Lucian Iftime","2020-09-30",55);
//        theaterPlaysRepository.addPlay(t);
//        TheaterPlay p = theaterPlaysRepository.findPlay("50 De Secunde");
//        if(p!=null){
//            System.out.println("found");
////          TheaterPlay x = new TheaterPlay(p.)
//        };

    }
}