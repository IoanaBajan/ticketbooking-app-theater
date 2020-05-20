package repository;

import model.Adult;

import java.util.Optional;

public interface AdultRepository {
    void addAdult(Adult a);
    Optional<Adult> findUserByName(String username);

    static AdultRepository build(AdultRepository.Type type) {
        switch (type){
            case DB: return  new DBAdultRepository();
            case FILE: return  new FileAdultRepository();
        }
        throw  new RuntimeException("no such type");
    }
    enum Type{
        DB, FILE
    }

}
