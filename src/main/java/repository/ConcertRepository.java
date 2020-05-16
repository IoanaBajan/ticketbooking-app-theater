package repository;

import model.Concert;
import model.Event;

import java.util.ArrayList;

public class ConcertRepository {
    private static ArrayList<Concert> concerts = new ArrayList<> ();;

    public ArrayList<Concert> getConcerts() {
        return concerts;
    }

    private ConcertRepository() {
        concerts.add(new Concert("2020-03-02","Om la luna",100));
        concerts.add(new Concert("2020-03-08","Alternosfera",100));
        concerts.add(new Concert("2020-03-01","Luna Amara",100));
    }
    public static void addConcert(Concert e) {
        concerts.add(e);
    }
    public static void removeConcerts(Concert e) {
        concerts.remove(e);
    }

    public static ConcertRepository getInstance() {
        return ConcertRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static ConcertRepository INSTANCE = new ConcertRepository();
    }

}
