package service;

import model.Child;
import model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Comparator;
import java.util.Date;

public class BuyService {

    public void showPrice(Object x, Object y, int searNr) {
        calculateFactory factory = new calculateFactory();
        CalculatePrice obj = factory.getPrice(x);
        Event now = new Event("2020/12/12","null",222);
        if(((Event) y).compareTo(now)<=-1) {
            System.out.println("show is available");
            if (x instanceof Child){
                System.out.println("price is 0");
            }else  obj.calculate(searNr, y);
        }else System.out.println("The show is no longer available");

    }
}
