package service;

import repository.*;

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
    public void showPlays() {
        TheaterPlaysRepository theaterPlayRepository = TheaterPlaysRepository.getInstance();
        for (int i = 0; i < (theaterPlayRepository.getPlays()).size(); i++) {
            System.out.println(theaterPlayRepository.getPlays().get(i).toString());
        }
    }
    public void showCharityEvents() {
        CharityEventRepository charityEventRepository = CharityEventRepository.getInstance();
        for (int i = 0; i < (charityEventRepository.getCharityEvents()).size(); i++) {
            System.out.println(charityEventRepository.getCharityEvents().get(i).toString());
        }
    }
    public void showConcerts() {
        ConcertRepository concertRepository = ConcertRepository.getInstance();
        for (int i = 0; i < (concertRepository.getConcerts()).size(); i++) {
            System.out.println(concertRepository.getConcerts().get(i).toString());
        }
    }
    public static InformationService getInstance() {
        return InformationService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static InformationService INSTANCE = new InformationService();
    }
}
