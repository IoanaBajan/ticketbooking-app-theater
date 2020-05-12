package repository;

import model.Adult;

import java.util.ArrayList;
import java.util.Optional;

public class AdultRepository {
    private static ArrayList<Adult> adults = new ArrayList<> ();
    public ArrayList<Adult> getAdults() {
        return adults;
    }
    public AdultRepository() {
        this.adults.add(new Adult("mosby","12345","Ted",35));
    }
    public static void addAdult(Adult c) {
        adults.add(c);
    }
    public Optional<Adult> findUserByUsername(String username) {
        for (Adult c : adults) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }
    public static AdultRepository getInstance() {
        return AdultRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static AdultRepository INSTANCE = new AdultRepository();
    }

}
