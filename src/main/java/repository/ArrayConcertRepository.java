package repository;

import model.Concert;
import model.Student;

import java.util.ArrayList;
import java.util.Optional;

public class ArrayConcertRepository implements ConcertRepository {
    private static ArrayList<Concert> concerts = new ArrayList<> ();;
    public ArrayList<Concert> getConcerts() {
        return concerts;
    }

    @Override
    public  void addConcert(Concert e) {
        concerts.add(e);
    }

    @Override
    public Optional<Concert> findConcert(String name) {
        for (Concert c : concerts) {
            if (c != null) {
                if (name.equals(c.getName())) {
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }
    public void showConcerts() {
        for (int i = 0; i < (concerts).size(); i++) {
            System.out.println(concerts.get(i).toString());
        }
    }
    public void removeConcerts(Concert e) {
        concerts.remove(e);
    }
    public  ArrayConcertRepository getInstance() {
        return ArrayConcertRepository.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static ArrayConcertRepository INSTANCE = new ArrayConcertRepository();
    }

}
