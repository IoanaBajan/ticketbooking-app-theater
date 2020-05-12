package model;

import java.util.ArrayList;

public class TheaterPlay extends Event {
    private final String director;
    private final String actors;

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
