package repository;

import model.Student;
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
    public Optional<TheaterPlay> findPlay(String name) {
        for (TheaterPlay c : plays) {
            if (c != null) {
                if (name.equals(c.getName())) {
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }
    public void showPlays() {
        for (int i = 0; i < (plays).size(); i++) {
            System.out.println(plays.get(i).toString());
        }
    }

    public static void removePlay(TheaterPlay e) {
        plays.remove(e);
    }

}
