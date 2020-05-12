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
        concerts.add(new Concert("03/03/2020","Piesa de teatru",100));
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
