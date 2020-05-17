package service;


public class ReducedTickets extends Tickets implements CalculatePrice {
    public void setPrice(int price) {
        this.price = price;
    }

    private int price;
    private double studentDiscount = 0.5;

    public int calculate(int seatNr, Object event){
        super.calculate(seatNr,event);
        price -=studentDiscount*price;
        setPrice(price);
        return price;
    }
}
