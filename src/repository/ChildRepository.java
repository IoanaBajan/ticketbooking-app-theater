package repository;
import model.Child;
import java.util.ArrayList;
import java.util.Optional;

public class ChildRepository {
    private static ArrayList<Child> children = new ArrayList<> ();
    public ArrayList<Child> getChildren() {
        return children;
    }
    public ChildRepository() {
        this.children.add(new Child("rossie","12345","Ross",6,true));

    }
    public static void addChild(Child c) {
        children.add(c);
    }
    public Optional<Child> findUserByUsername(String username) {
        for (Child c : children) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }
    public static ChildRepository getInstance() {
        return ChildRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static ChildRepository INSTANCE = new ChildRepository();
    }

}
