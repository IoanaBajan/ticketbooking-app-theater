package service;

import model.Concert;
import model.Order;
import model.TheaterPlay;


public class PlaceOrderService {
    public boolean isOrderValid(Order o,TheaterPlay t){
        if(o.getDate().isBefore( t.getStartDate()) || o.getDate().isAfter(t.getEndDate())) {
           return false;
        }
        if(o.getSeats() > t.getMaxNumberSeats())
            return false;
        if(o.getPrice() <=-1)
            return false;
        return true;
    }
    public boolean isOrderValid(Order o,Concert t){
        if(!o.getDate().equals(t.getDate())) {
            return false;
        }
        if(o.getSeats() > t.getMaxNumberSeats())
            return false;
        if(o.getPrice() <=-1)
            return false;

        return true;
    }

}
