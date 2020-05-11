package repository;

import model.Client;
import model.Event;
import model.Student;

import java.util.ArrayList;
import java.util.Optional;

public class EventRepository {
    public ArrayList<Event> getEvents() {
        return events;
    }

    private ArrayList<Event> events = new ArrayList<> ();;

    private EventRepository() {
        this.events.add(new Event("2020/04/24","Piesa de teatru",100));
        this.events.add(new Event("2020/03/21","Piesa de teatru2",100));
    }
    public boolean addEvent(Event e) {
        events.add(e);
        return true;
    }
    public boolean removeEvent(Event e) {
        events.remove(e);
        return true;
    }

    public static EventRepository getInstance() {
        return EventRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static EventRepository INSTANCE = new EventRepository();
    }
}
