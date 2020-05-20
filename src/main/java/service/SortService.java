package service;

import model.Concert;
import model.Event;
import model.TheaterPlay;
import repository.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortService {
    private ConcertRepository concertRepository;
    private TheaterPlaysRepository theaterPlaysRepository;
    public SortService() {
        concertRepository = ConcertRepository.build(ConcertRepository.Type.DB);
        theaterPlaysRepository = TheaterPlaysRepository.build(TheaterPlaysRepository.Type.DB);
    }

    // events By date
   public Event[] sortConcerts() {
       Concert events1[] = concertRepository.getConcerts().toArray(new Concert[0]);
        Arrays.sort(events1);
        return events1;
    }

    public Event[] sortPlays() {
        TheaterPlay events1[] = theaterPlaysRepository.getPlays().toArray(new TheaterPlay[0]);
        Arrays.sort(events1);
        return events1;

    }

    //events sorted by name
    public Event[] sortConcertsByName() {
        Concert events1[] = concertRepository.getConcerts().toArray(new Concert[0]);
        Comparator<Concert> c1 = Comparator.comparing(Event::getName);
        Arrays.sort(events1,c1);
        return events1;
    }
    public Event[] sortPlaysByName() {
        TheaterPlay events1[] = theaterPlaysRepository.getPlays().toArray(new TheaterPlay[0]);
        Comparator<TheaterPlay> c1 = Comparator.comparing(TheaterPlay::getName);
        Arrays.sort(events1,c1);
        return events1;
    }
}
