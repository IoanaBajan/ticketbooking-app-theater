package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class Event implements Comparable<Event> {
    private LocalDate date;
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
    public Event() {
    }
    @Override
    public String toString() {
        return
                "  name: '" + name + '\'' +
                "  maxNumberSeats " + maxNumberSeats;
    }

    public void setDate(String d) {

            this.date = LocalDate.parse(d);

    }


    public LocalDate getDate() {
//            LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            return date;
        }


    public void setName(String name) {
        this.name = name;
    }

    public void setMaxNumberSeats(int maxNumberSeats) {
        this.maxNumberSeats = maxNumberSeats;
    }

    public String getName() {
        return name;
    }

    public Event(String data, String name, int maxNumberSeats) {
        this.date = LocalDate.parse(data);
        this.name = name;
        this.maxNumberSeats = maxNumberSeats;
    }

    @Override
    public int compareTo(Event ev) {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate date1 = ev.getDate();
        LocalDate date2 = this.getDate();
        return (date2.compareTo(date1));
    }

}
