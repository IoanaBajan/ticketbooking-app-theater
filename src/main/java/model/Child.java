package model;

public class Child extends Client {
    int accompanied;

    public Child() {
    }

    public int isAccompanied() {
        return accompanied;
    }

    public void setAccompanied(int accompanied) {
        this.accompanied = accompanied;
    }

    public Child(String username, String password, String firstName, int accompanied) {
        super(username, password, firstName);
        this.accompanied = accompanied;
    }

    @Override
    public String toString() {
        return "Child" + super.toString() +
                " accompanied? " + accompanied;
    }
}
