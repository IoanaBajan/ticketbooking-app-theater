package main;

import gui.Login;
import javafx.application.Application;
import model.Theater;

public class Main {
    public static void main(String[] args) {
        Application.launch(Login.class, args);
        Theater T = new Theater();
        T.startApplication();
    }
}
