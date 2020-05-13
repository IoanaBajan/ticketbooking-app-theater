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

    public void calculate(int seatNr, Object event){
        if(event instanceof Concert) {
            if (((Concert) event).soldout < ((Concert) event).getMaxNumberSeats()) {
                ((Concert) event).soldout++;
                price = concertPrice;
                System.out.println("AICI E VARIABILA PE CARE O CAUTI   " + seatNr);
//                    int[] myArray = ((Concert) event).getOccupiedSeats();
//                    myArray [seatNr]= seatNr;
//                ((Concert) event).setOccupiedSeats(myArray);
                System.out.println(price + " lei ticket to" + event.toString()+" " + ((Concert) event).soldout+ Arrays.toString(((Concert) event).getOccupiedSeats()));

            } else System.out.println("the event is soldout");
        }

        else if(event instanceof TheaterPlay) {
            if (((TheaterPlay) event).soldout < ((TheaterPlay) event).getMaxNumberSeats()) {
                ((TheaterPlay) event).soldout++;
                price = playPrice;
//                ((Theater) event).addSeat(seatNr);
                System.out.println(price + " lei ticket to" + event.toString()+" " + ((TheaterPlay) event).soldout );
            } else System.out.println("the event is soldout");
        }
        else {
            if (((CharityEvent) event).soldout < ((CharityEvent) event).getMaxNumberSeats()) {
                ((CharityEvent) event).soldout++;
                price = charityEvPrice;
                System.out.println(price + " lei ticket to" + event.toString() + " " + ((CharityEvent) event).soldout);
            }else System.out.println("event is soldout");
        }

        if(seatNr <basicClass && seatNr>firstClass) price += 0.2*price;
        if(seatNr<firstClass) price += 0.25*price;
    }
}
