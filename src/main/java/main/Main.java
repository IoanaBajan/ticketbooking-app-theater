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
    }
}
