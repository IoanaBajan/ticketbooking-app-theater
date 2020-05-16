package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.name = name;
        this.maxNumberSeats = maxNumberSeats;
    }

    @Override
    public int compareTo(Event ev) {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = ev.getDate();
        Date date2 = this.getDate();
        return (date2.compareTo(date1));
    }

}
