package model;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Event extends Theater implements Comparable<Event> {
    private Date date;
    private String data;
    private String name;
    private int maxNumberSeats;
    protected int[] occupiedSeats = {0};

    public int[] getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(int[] occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public void addSeat(Integer seat) {
        occupiedSeats[seat] = seat;
    }

    public int getMaxNumberSeats() {
        return maxNumberSeats;
    }

    @Override
    public String toString() {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        return  " date: " + dt1.format(date) +
                "  name: '" + name + '\'' +
                "  maxNumberSeats " + maxNumberSeats;
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
//            System.out.println(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.name = name;
        this.maxNumberSeats = maxNumberSeats;
    }

    @Override
    public int compareTo(Event ev) {
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = ev.getDate();
        Date date2 = this.getDate();
        return (date1.compareTo(date2));
    }

}
