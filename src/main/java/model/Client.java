package model;

public class Client extends User{

    private String firstName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Client() {
        super();
    }

    public Client(String username, String password, String firstName) {
        super(username, password);
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return super.toString() + "  Name " + firstName;
    }
}

