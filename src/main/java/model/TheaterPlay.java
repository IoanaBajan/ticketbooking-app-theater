package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TheaterPlay extends Event {
    private String director;
    private String actors;
    private LocalDate endDate;
    private Integer nrRepresentations;
    public int soldout;


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        LocalDate d = LocalDate.parse(endDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = formatter.format(d);
        this.endDate = LocalDate.parse(date);

    }

    public Integer getNrRepresentations() {
        return nrRepresentations;
    }

    public void setNrRepresentations(Integer nrRepresentations) {
        this.nrRepresentations = nrRepresentations;
    }

    public TheaterPlay(String data1, String n, int s, String d, String a, String endDate) {
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public TheaterPlay() {
        super();
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }
    public LocalDate getStartDate() {
        return super.getDate();
    }


    public TheaterPlay(String date, String name, int maxNumberSeats, String director, String actors,String endDate,Integer nrRepresentations) {
        super(date, name, maxNumberSeats);
        this.director = director;
        this.actors = actors;
        this.nrRepresentations = nrRepresentations;
        this.endDate = LocalDate.parse(endDate);

    }

    @Override
    public String toString() {

        return "TheaterPlay  " + super.toString() +
                " director " + director + '\n' +
                " actors " + actors + '\n' + " the play will be performed starting " + getStartDate() +
                " until " + endDate + " number of performances this season " + nrRepresentations;
    }

}
