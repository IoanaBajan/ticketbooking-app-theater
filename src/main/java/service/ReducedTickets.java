package service;

public class ReducedTickets extends Tickets implements CalculatePrice {
    private int price;
    private double studentDiscount = 0.5;

    public void setPrice(int price) {
        this.price = price;
    }

    public int calculate(int seatNr, Object event){
        price = super.calculate(seatNr,event);
        if(price!=-1){
            price -=studentDiscount*price;
            setPrice(price);
        }
        return price;
    }
}
