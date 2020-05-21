package repository;
import managers.DBConectionManager;
import model.Child;

import java.util.ArrayList;
import java.util.Optional;

public interface ChildRepository {
    void addChild(Child a);
    Optional<Child> findUserByName(String username);
    ArrayList<Child> getChildren();
    void showChildren();
    static ChildRepository build(ChildRepository.Type type) {
        switch (type){
            case DB: return  new DBChildRepository();
        }
        throw  new RuntimeException("no such type");
    }
    enum Type{
        DB, FILE,ARRAY
    }

}
