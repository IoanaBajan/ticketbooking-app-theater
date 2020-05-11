package model;

public class Adult extends Client {
    public Adult(String username, String password) {
        super(username, password);
    }
    public String toString() {
        return "Adult{" + super.toString() +
                '}';
    }
}
