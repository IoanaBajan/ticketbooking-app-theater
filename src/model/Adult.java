package model;

public class Adult extends Client {
    public Adult(String username, String password,String firstName, int age) {
        super(username, password, firstName, age);
    }
    public String toString() {
        return "Adult " + super.toString();
    }
}
