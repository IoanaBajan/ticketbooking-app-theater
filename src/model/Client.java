package model;

public class Client extends User{

    private final int age;
    private final String firstName;

    public Client(String username, String password, String firstName, int age) {
        super(username, password);
        this.firstName = firstName;
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + "  Name " + firstName + "  Age " + age;
    }
}

