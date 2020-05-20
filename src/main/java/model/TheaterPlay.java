package model;

import java.util.ArrayList;

public class TheaterPlay extends Event {
    private String director;
    private String actors;
    public int soldout;

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

    public TheaterPlay(String date, String name, int maxNumberSeats, String director, String actors) {
        super(date, name, maxNumberSeats);
        this.director = director;
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "TheaterPlay  " + super.toString() +
                " director " + director + '\'' +
                " actors " + actors + '\'';
    }

}
