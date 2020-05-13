package model;

public class Client extends User{

    private int age;
    private String firstName;

    public Client() {
        super();
    }

    public Client(String username, String password, String firstName, int age) {
        super(username, password);
        this.firstName = firstName;
        this.age = age;
    }


    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return super.toString() + "  Name " + firstName + "  Age " + age;
    }
}

