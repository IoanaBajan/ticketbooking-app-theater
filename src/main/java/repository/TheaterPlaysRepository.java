package repository;

import managers.DBConectionManager;
import model.TheaterPlay;

import java.util.ArrayList;
import java.util.Optional;


public interface TheaterPlaysRepository {
    void addPlay(TheaterPlay play);
    void showPlays();
    ArrayList<TheaterPlay> getPlays();
    Optional<TheaterPlay> findPlay(String name);

    static TheaterPlaysRepository build(TheaterPlaysRepository.Type type) {
        switch (type){
            case DB: return new DBTheaterPlaysRepository();
            case FILE: return new FileTheaterPlaysRepository();
        }
        throw  new RuntimeException("no such type");
    }
    enum Type{
        DB, FILE
    }

}
