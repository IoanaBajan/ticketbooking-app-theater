package repository;

import model.Adult;

import java.util.ArrayList;
import java.util.Optional;

public class ArrayAdultRepository implements AdultRepository {
    private static ArrayList<Adult> adults = new ArrayList<> ();
    public ArrayList<Adult> getAdults() {
        return adults;
    }

    public ArrayAdultRepository() {
        adults.add(new Adult("testArray","12345","Ion"));
    }

    @Override
    public void addAdult(Adult a) {
            adults.add(a);
    }

    @Override
    public Optional<Adult> findUserByName(String username) {
        for (Adult c : adults) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }

    public void showAdults() {
        for (int i = 0; i < adults.size(); i++) {
            System.out.println(adults.get(i).toString());
        }
    }
}
