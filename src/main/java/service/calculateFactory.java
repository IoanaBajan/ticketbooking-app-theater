package service;

import model.Student;

public class calculateFactory {
    public CalculatePrice getPrice(Object o){
        CalculatePrice obj;
        if(o instanceof Student)
            obj = new ReducedTickets();

        else obj = new Tickets();
        return obj;

    }
}
