package repository;
import managers.DBConectionManager;
import model.Child;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ChildRepository {
    private static ArrayList<Child> children = new ArrayList<> ();
    public ArrayList<Child> getChildren() {
        return children;
    }
    public ChildRepository() {
        this.children.add(new Child("rossie","12345","Ross",6,1));

    }
    public void addToDtbase(Child c) {
        String sql = "INSERT INTO children Values (NULL, ?, ?, ?, ?, ?)";
        try(
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement= con.prepareStatement(sql);
        ) {
            statement.setString(1,c.getUsername());
            statement.setString(2,c.getPassword());
            statement.setString(3,c.getFirstName());
            statement.setString(4, String.valueOf(c.getAge()));
            statement.setInt(5, c.isAccompanied());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Child findUserInDB(String username) {
        String sql = "SELECT * FROM children WHERE username = ?";

        try(
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement= con.prepareStatement(sql);
        ) {
            statement.setString(1,username);

            ResultSet set = statement.executeQuery();
            if(set.next()) {

                String u = set.getString("username");
                String p = set.getString("password");
                String fn = set.getString("first_name");
                int ag = set.getInt("age");
                int ac = set.getInt("accompanied");

                return new Child(u, p, fn, ag, ac);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
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
