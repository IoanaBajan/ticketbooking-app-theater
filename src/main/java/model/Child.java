package model;

public class Child extends Client {
    int accompanied;

    public int isAccompanied() {
        return accompanied;
    }

    public void setAccompanied(int accompanied) {
        this.accompanied = accompanied;
    }

    public Child(String username, String password, String firstName, int age, int accompanied) {
        super(username, password, firstName, age);
        this.accompanied = accompanied;
    }

    @Override
    public String toString() {
        return "Child" + super.toString() +
                " accompanied? " + accompanied;
    }
}
