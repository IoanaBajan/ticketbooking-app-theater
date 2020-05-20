package model;

public class Adult extends Client {
    public Adult() {
        super();
    }

    public Adult(String username, String password, String firstName) {
        super(username, password, firstName);
    }
    public String toString() {
        return "Adult " + super.toString();
    }
}
