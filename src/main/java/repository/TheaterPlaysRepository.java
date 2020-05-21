package repository;

import model.TheaterPlay;

import java.util.ArrayList;


public interface TheaterPlaysRepository {
    void addPlay(TheaterPlay play);
    void showPlays();
    ArrayList<TheaterPlay> getPlays();
    TheaterPlay findPlay(String name);
    void deletePlay(String name);
    void updatePlay(String name,String choice, String newValue);
    static TheaterPlaysRepository build(TheaterPlaysRepository.Type type) {
        switch (type){
            case DB: return new DBTheaterPlaysRepository();
            case FILE: return new FileTheaterPlaysRepository();
            case ARRAY: return new ArrayTheaterPlaysRepository();
        }
        throw  new RuntimeException("no such type");
    }
    enum Type{
        DB, FILE,ARRAY
    }

}
