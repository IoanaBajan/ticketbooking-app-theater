package repository;

import model.Concert;

import java.util.ArrayList;

public class ConcertRepository {
    private static ArrayList<Concert> concerts = new ArrayList<> ();;

    public ArrayList<Concert> getConcerts() {
        return concerts;
    }

    private ConcertRepository() {
        concerts.add(new Concert("2020-06-08","Concert Simfonic",68));
        concerts.add(new Concert("2020-06-01","Luna Amara",68));
        concerts.add(new Concert("2020-06-02","Concert Coral",60));
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
