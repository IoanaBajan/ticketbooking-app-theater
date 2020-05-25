package service;

import model.*;
import repository.*;

import java.util.Optional;

public class BuyService {
    private StudentRepository studentRepository;
    private AdultRepository adultRepository;
    public BuyService() {
        studentRepository = StudentRepository.build(StudentRepository.Type.DB);
        adultRepository = AdultRepository.build(AdultRepository.Type.DB);
    }

    public int showPrice(User x, Event y, int searNr) {

        calculateFactory factory = new calculateFactory();
        if(studentRepository.findUserByName(x.getUsername()).isPresent()) {
            Optional<Student> s = studentRepository.findUserByName(x.getUsername());
            System.out.println(s.toString());
            CalculatePrice obj = factory.getPrice(s.get());
            return obj.calculate(searNr, y);
        }
        if(adultRepository.findUserByName(x.getUsername()).isPresent()) {
            Optional<Adult> s = adultRepository.findUserByName(x.getUsername());
            CalculatePrice obj = factory.getPrice(s.get());
            return obj.calculate(searNr, y);
        }

    return 0;
    }
}
