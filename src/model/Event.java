package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;

public class Event extends Theater implements Comparable<Event> {
    private Date date;
    private String data;
    private String name;
    private int maxNumberSeats;

    @Override
    public String toString() {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        return "Event{" +
                "date=" + dt1.format(date) +
                ", name='" + name + '\'' +
                ", maxNumberSeats=" + maxNumberSeats +
                '}';
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public Event(String data, String name, int maxNumberSeats) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = dateFormat.parse(data);
            System.out.println(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.name = name;
        this.maxNumberSeats = maxNumberSeats;
    }

    @Override
    public int compareTo(Event ev) {
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
       Date now = new Date();
        ev.setDate(now);
        return (now.compareTo(this.getDate()));
    }

}
