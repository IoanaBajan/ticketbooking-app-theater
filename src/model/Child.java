package model;

public class Child extends Client {
    boolean accompanied;
    public Child(String username, String password,String firstName, int age,boolean accompanied) {
        super(username, password, firstName, age);
        this.accompanied = accompanied;
    }

    @Override
    public String toString() {
        return "Child" + super.toString() +
                " accompanied? " + accompanied;
    }
}
