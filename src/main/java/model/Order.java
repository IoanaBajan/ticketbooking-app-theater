package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Order {
    private String payment;
    private LocalDate date;
    private int seats;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String eventName;
    private String clientName;
    private int price;

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        LocalDate d = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String datetemp = formatter.format(d);
        this.date = LocalDate.parse(datetemp);
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

    public Order(String payment, LocalDate date, int seats, int price,String eventName,String clientName) {
        this.payment = payment;
        this.date = date;
        this.seats = seats;
        this.eventName = eventName;
        this.clientName = clientName;
        this.price = price;
    }
}
