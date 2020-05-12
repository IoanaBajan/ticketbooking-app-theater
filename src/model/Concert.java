package model;

public class Concert extends Event {

    private int location;
    public Concert(String date, String name, int maxNumberSeats) {
        super(date, name, maxNumberSeats);
    }

    @Override
    public String toString() {
        return "Concert " + super.toString();
    }
}
