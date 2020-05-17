package service;

import model.Child;
import model.Event;

public class BuyService {

    public int showPrice(Object x, Object y, int searNr) {
        calculateFactory factory = new calculateFactory();
        CalculatePrice obj = factory.getPrice(x);
            if (x instanceof Child){
                return  0;
            }else return obj.calculate(searNr, y);
    }
}
