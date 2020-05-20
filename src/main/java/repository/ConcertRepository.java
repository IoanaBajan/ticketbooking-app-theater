package repository;

import model.Concert;
import model.TheaterPlay;

import java.util.ArrayList;
import java.util.Optional;

public interface ConcertRepository {
    void addConcert(Concert concert);
    void showConcerts();
    ArrayList<Concert> getConcerts();
    Optional<Concert> findConcert(String name);

    static ConcertRepository build(ConcertRepository.Type type) {
        switch (type){
            case DB: return new DBConcertRepository();
            case FILE: return new FileConcertRepository();
            case ARRAY: return new ArrayConcertRepository();
        }
        throw  new RuntimeException("no such type");
    }
    enum Type{
        DB, FILE,ARRAY
    }

}
