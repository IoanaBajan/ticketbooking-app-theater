package model;

public class Child extends Client {
    boolean accompanied;
    public Child(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString() {
        return "Child{" + super.toString() +
                "accompanied=" + accompanied +
                '}';
    }
}
