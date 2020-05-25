package service;

import model.Adult;
import model.Student;


public class calculateFactory {
    public CalculatePrice getPrice(Student c) {
        CalculatePrice obj;
        obj = new ReducedTickets();
        return obj;

    }
    public CalculatePrice getPrice(Adult c) {
        CalculatePrice obj;
         obj = new Tickets();
        return obj;

    }

}
