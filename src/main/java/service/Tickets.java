package service;

import model.Concert;
import model.TheaterPlay;

import java.util.Arrays;

public class Tickets implements CalculatePrice {


    private int price;
    private final int CONCERT_PRICE = 60;
    private final int PLAY_PRICE = 70;
    private final int FIRST_CLASS = 10;
    private final int BASIC_CLASS = 38;

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int calculate(int seatNr, Object event){
        if(event instanceof Concert) {
            if (((Concert) event).soldout < ((Concert) event).getMaxNumberSeats()) {
                ((Concert) event).soldout++;
                price = CONCERT_PRICE;
            } else return -1;
        }

        else if(event instanceof TheaterPlay) {
            if (((TheaterPlay) event).soldout < ((TheaterPlay) event).getMaxNumberSeats()) {
                ((TheaterPlay) event).soldout++;
                price = PLAY_PRICE;
            } else return -1;
        }

        if(seatNr <BASIC_CLASS && seatNr>FIRST_CLASS) price += 0.2*price;
        if(seatNr<FIRST_CLASS) price += 0.25*price;
        setPrice(price);
    return price;
    }
}
