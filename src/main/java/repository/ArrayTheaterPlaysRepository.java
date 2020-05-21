package repository;

import model.TheaterPlay;

import java.util.ArrayList;
import java.util.Optional;

public class ArrayTheaterPlaysRepository implements TheaterPlaysRepository{
    private static ArrayList<TheaterPlay> plays = new ArrayList<> ();

    public ArrayList<TheaterPlay> getPlays() {
        return plays;
    }
    @Override
    public void addPlay(TheaterPlay e) {
        plays.add(e);
    }

    @Override
    public TheaterPlay findPlay(String name) {
        for (TheaterPlay c : plays) {
            if (c != null) {
                if (name.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public void deletePlay(String name) {
        TheaterPlay p = findPlay(name);
        plays.remove(p);

    }

    @Override
    public void updatePlay(String name, String choice, String newValue) {

    }

    public void showPlays() {
        for (int i = 0; i < (plays).size(); i++) {
            System.out.println(plays.get(i).toString());
        }
    }

}
