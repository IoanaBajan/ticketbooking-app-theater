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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Order(String payment, String date, int seats, int price) {
        this.payment = payment;
        this.date = date;
        this.seats = seats;
//        this.eventID = eventID;
//        this.clientID = clientID;
        this.price = price;
    }
}
