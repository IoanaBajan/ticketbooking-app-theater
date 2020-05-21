package repository;

import model.Adult;
import model.Concert;

import java.util.ArrayList;
import java.util.Optional;

public interface AdultRepository {
    void addAdult(Adult a);
    Optional<Adult> findUserByName(String username);
    ArrayList<Adult> getAdults();
    void showAdults();
    static AdultRepository build(AdultRepository.Type type) {
        switch (type){
            case DB: return  new DBAdultRepository();
            case FILE: return  new FileAdultRepository();
            case ARRAY: return  new ArrayAdultRepository();

        }
        throw  new RuntimeException("no such type");
    }
    enum Type{
        DB, FILE,ARRAY
    }

}
