package repository;

import model.CharityEvent;

import java.util.ArrayList;

public class CharityEventRepository {
    private static ArrayList<CharityEvent> charityEvents = new ArrayList<> ();;

    public ArrayList<CharityEvent> getCharityEvents() {
        return charityEvents;
    }

    private CharityEventRepository() {
        charityEvents.add(new CharityEvent("03/03/2020","Piesa de teatru1",100));
        charityEvents.add(new CharityEvent("08/08/2020","Piesa de teatru2",80));
        charityEvents.add(new CharityEvent("01/01/2020","Piesa de teatru3",80));
    }
    public static void addCharityEvent(CharityEvent e) {
        charityEvents.add(e);
    }
    public static void removeCharityEvent(CharityEvent e) {
        charityEvents.remove(e);
    }

    public static CharityEventRepository getInstance() {
        return CharityEventRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static CharityEventRepository INSTANCE = new CharityEventRepository();
    }
}
