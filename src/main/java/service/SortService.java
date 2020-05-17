package service;

import model.CharityEvent;
import model.Concert;
import model.Event;
import model.TheaterPlay;
import repository.CharityEventRepository;
import repository.ConcertRepository;
import repository.TheaterPlaysRepository;

import java.util.Arrays;
import java.util.Comparator;

public class SortService {
// events By date
   public Event[] sortConcerts() {
        ConcertRepository eventRepository = ConcertRepository.getInstance();
       Concert events1[] = eventRepository.getConcerts().toArray(new Concert[0]);
        Arrays.sort(events1);
        return events1;
    }

    public Event[] sortPlays() {
        TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.getInstance();
        TheaterPlay events1[] = theaterPlaysRepository.getPlays().toArray(new TheaterPlay[0]);
        Arrays.sort(events1);
        return events1;

    }

    public Event[] sortCharityEvents() {
        CharityEventRepository  charityEventRepository = CharityEventRepository.getInstance();
        CharityEvent events1[] = charityEventRepository.getCharityEvents().toArray(new CharityEvent[0]);
        Arrays.sort(events1);
        return events1;

    }
    //events sorted by name
    public Event[] sortConcertsByName() {
        ConcertRepository concertRepository = ConcertRepository.getInstance();
        Concert events1[] = concertRepository.getConcerts().toArray(new Concert[0]);
        Comparator<Concert> c1 = Comparator.comparing(Event::getName);
        Arrays.sort(events1,c1);
        return events1;
    }
    public Event[] sortPlaysByName() {
        TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.getInstance();
        TheaterPlay events1[] = theaterPlaysRepository.getPlays().toArray(new TheaterPlay[0]);
        Comparator<TheaterPlay> c1 = Comparator.comparing(TheaterPlay::getName);
        Arrays.sort(events1,c1);
        return events1;
    }
    public Event[] sortCharityEventsByName() {
        CharityEventRepository charityEventRepository = CharityEventRepository.getInstance();
        CharityEvent events1[] = charityEventRepository.getCharityEvents().toArray(new CharityEvent[0]);
        Comparator<CharityEvent> c1 = Comparator.comparing(CharityEvent::getName);
        Arrays.sort(events1,c1);
        return events1;
    }

}
