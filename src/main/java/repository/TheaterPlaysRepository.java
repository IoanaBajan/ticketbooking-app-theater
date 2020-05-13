package repository;

import model.TheaterPlay;

import java.util.ArrayList;

public class TheaterPlaysRepository {

    private static ArrayList<TheaterPlay> plays = new ArrayList<> ();;

    public ArrayList<TheaterPlay> getPlays() {
        return plays;
    }

    private TheaterPlaysRepository() {
        plays.add(new TheaterPlay("03/03/2020","50 De Secunde",60,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime"));
        plays.add(new TheaterPlay("08/03/2020","50 De Secunde",60,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime"));
        plays.add(new TheaterPlay("01/03/2020","50 De Secunde",60,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime"));
    }
    public static void addPlay(TheaterPlay e) {
        plays.add(e);
    }
    public static void removePlay(TheaterPlay e) {
        plays.remove(e);
    }

    public static TheaterPlaysRepository getInstance() {
        return TheaterPlaysRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static TheaterPlaysRepository INSTANCE = new TheaterPlaysRepository();
    }


}
