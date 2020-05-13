package service;

import model.CharityEvent;
import model.Concert;
import model.Theater;
import model.TheaterPlay;

import java.util.Arrays;


public class ReducedTickets extends Tickets implements CalculatePrice {
    private int price;
    private double studentDiscount = 0.5;

    public void calculate(int seatNr, Object event){
        super.calculate(seatNr,event);
        price -=studentDiscount*price;

    }
}
