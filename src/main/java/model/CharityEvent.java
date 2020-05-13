package model;

public class CharityEvent extends Event {
    public int soldout;

    public CharityEvent(String date, String name, int maxNumberSeats) {
        super(date, name, maxNumberSeats);
    }


    @Override
    public String toString() {
        return "Charity event " + super.toString();
    }
}
