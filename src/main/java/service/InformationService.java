package service;

import model.CharityEvent;
import model.Concert;
import model.Event;
import model.TheaterPlay;
import repository.*;

import java.util.ArrayList;

public class InformationService {
    public void showStudents(){
        StudentRepository studentRepository = StudentRepository.getInstance();
        for(int i = 0; i < (studentRepository.getStudents()).size(); i++)  {
            System.out.println(studentRepository.getStudents().get(i));
        }

    }
    public void showAdults(){
        AdultRepository adultRepository = AdultRepository.getInstance();
        for(int i = 0; i < (adultRepository.getAdults()).size(); i++)  {
            System.out.println(adultRepository.getAdults().get(i));
        }
    }
        public void showKids() {
            ChildRepository childRepository = ChildRepository.getInstance();
            for (int i = 0; i < (childRepository.getChildren()).size(); i++) {
                System.out.println(childRepository.getChildren().get(i));
            }
    }
    public ArrayList<TheaterPlay> showPlays() {
        TheaterPlaysRepository theaterPlayRepository = TheaterPlaysRepository.getInstance();
        ArrayList<TheaterPlay> plays = new ArrayList<> ();
        for (int i = 0; i < (theaterPlayRepository.getPlays()).size(); i++) {
            plays.add(theaterPlayRepository.getPlays().get(i));
        }
        return plays;
    }

    public ArrayList<CharityEvent> showCharityEvents() {
        CharityEventRepository charityEventRepository = CharityEventRepository.getInstance();

        ArrayList<CharityEvent> charity = new ArrayList<> ();
        for (int i = 0; i < (charityEventRepository.getCharityEvents()).size(); i++) {
            charity.add(charityEventRepository.getCharityEvents().get(i));
        }
        return charity;
    }
    public ArrayList<Concert> showConcerts() {
        ConcertRepository concertRepository = ConcertRepository.getInstance();

        ArrayList<Concert> concerts = new ArrayList<> ();
        for (int i = 0; i < (concertRepository.getConcerts()).size(); i++) {
            concerts.add(concertRepository.getConcerts().get(i));
        }
        return concerts;
    }
    public static InformationService getInstance() {
        return InformationService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static InformationService INSTANCE = new InformationService();
    }
}
