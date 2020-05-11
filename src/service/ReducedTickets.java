package service;

import model.CharityEvent;
import model.Concert;

public class ReducedTickets implements CalculatePrice {
    private int price;

    public void calculate(int seatNr, Object event){
        System.out.println("reduced TICKET");
        if(event instanceof Concert) price = 60;
        else price = 50;
        if(seatNr <40 && seatNr>10) price += 0.2*price;
        if(seatNr<10) price += 0.25*price;
        price -=0.5*price;
        if(event instanceof CharityEvent) price = 0;
    }
}
