package model;

public class Concert extends Event {
    public int soldout;
    private String performers;

    public String getPerformers() {
        return performers;
    }

    public void setPerformers(String performers) {
        this.performers = performers;
    }

    public Concert(String date, String name, int maxNumberSeats, String performers) {
        super(date, name, maxNumberSeats);
        this.performers = performers;
    }

    public Concert() {
    }

    @Override
    public String toString() {
        return "Concert " + super.toString()+ "performers:" + performers;
    }
}
