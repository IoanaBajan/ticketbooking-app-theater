package repository;

import model.Child;

import java.util.ArrayList;
import java.util.Optional;

public class ArrayChildRepository implements ChildRepository {
    private ArrayList<Child> children = new ArrayList<> ();
    public ArrayList<Child> getChildren() {
        return children;
    }
   @Override
    public void addChild(Child c) {
        children.add(c);
    }
    @Override
    public Optional<Child> findUserByName(String username) {
        for (Child c : children) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }
    @Override
    public void showChildren() {
        for (int i = 0; i < children.size(); i++) {
            System.out.println(children.get(i).toString());
        }
    }
}
