package main;

import gui.LoginFrame;
import model.*;
import service.InformationService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is your project speaking");
//        new LoginFrame();
//        Theater t = new Theater();
//        t.showInformationUser();
        Student i = new Student("ioana","www");
        System.out.println(i.toString());
//        Concert c = new Concert("2020/05/20","alternosfera",150);
//        t.showPrice(i,c,10);
        InformationService e = new InformationService();
        e.showClients();
    }
}
