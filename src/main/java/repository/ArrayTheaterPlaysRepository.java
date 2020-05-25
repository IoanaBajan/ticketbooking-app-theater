package repository;

import model.TheaterPlay;

import java.util.ArrayList;

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
        TheaterPlay p = findPlay(name);
        if(p!=null){
            if(choice == "actors"){
                for (TheaterPlay c : plays) {
                    if (c != null) {
                        if (name.equals(c.getName())) {
                            c.setActors(newValue);
                        }
                    }
                }
            }
            if(choice == "startDate"){
                for (TheaterPlay c : plays) {
                    if (c != null) {
                        if (name.equals(c.getName())) {
                            c.setDate(newValue);
                        }
                    }
                }
            }if(choice == "endDate"){
                for (TheaterPlay c : plays) {
                    if (c != null) {
                        if (name.equals(c.getName())) {
                            c.setEndDate(newValue);
                        }
                    }
                }
            }
        }
    }

    public void showPlays() {
        for (int i = 0; i < (plays).size(); i++) {
            System.out.println(plays.get(i).toString());
        }
    }

}
