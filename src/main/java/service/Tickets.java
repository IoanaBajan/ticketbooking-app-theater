package service;

import model.CharityEvent;
import model.Concert;
import model.TheaterPlay;

import java.util.Arrays;

public class Tickets implements CalculatePrice {


    private int price;
    private int concertPrice = 60;
    private int playPrice = 30;
    private int charityEvPrice = 0;
    private double studentDiscount = 0.5;
    private int firstClass = 10;
    private int basicClass = 40;

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
                price = concertPrice;
                System.out.println(price + " lei ticket to" + event.toString()+" " + ((Concert) event).soldout+ Arrays.toString(((Concert) event).getOccupiedSeats()));

            } else return -1;
        }

        else if(event instanceof TheaterPlay) {
            if (((TheaterPlay) event).soldout < ((TheaterPlay) event).getMaxNumberSeats()) {
                ((TheaterPlay) event).soldout++;
                price = playPrice;
            } else return -1;
        }
        else {
            if (((CharityEvent) event).soldout < ((CharityEvent) event).getMaxNumberSeats()) {
                ((CharityEvent) event).soldout++;
                price = charityEvPrice;
            }else return -1 ;
        }

        if(seatNr <basicClass && seatNr>firstClass) price += 0.2*price;
        if(seatNr<firstClass) price += 0.25*price;
        setPrice(price);
        System.out.println(price);
    return price;
    }
}
