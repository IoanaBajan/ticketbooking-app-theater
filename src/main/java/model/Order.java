package model;

import java.sql.Date;
import java.util.Arrays;

public class Order {
    private String payment;
    private String date;
//    private int noTickets;
    private int seats;
//    private int eventID;
//    private int clientID;
    private int price;

    public Order(String payment, String date, int seats, int price) {
        this.payment = payment;
        this.date = date;
        this.seats = seats;
//        this.eventID = eventID;
//        this.clientID = clientID;
        this.price = price;
    }
}
