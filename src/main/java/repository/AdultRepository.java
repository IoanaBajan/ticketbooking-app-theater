package repository;

import managers.DBConectionManager;
import model.Adult;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class AdultRepository {
    private static ArrayList<Adult> adults = new ArrayList<> ();
    public ArrayList<Adult> getAdults() {
        return adults;
    }
    public AdultRepository() {
        this.adults.add(new Adult("mosby","12345","Ted",35));
    }
    public static void addAdult(Adult c) {
        adults.add(c);
    }
    public void addToDtbase(Adult a) {
        String sql = "INSERT INTO adults Values (NULL, ?, ?, ?, ?)";
        try(
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement= con.prepareStatement(sql);
        ) {
            statement.setString(1,a.getUsername());
            statement.setString(2,a.getPassword());
            statement.setString(3,a.getFirstName());
            statement.setString(4, String.valueOf(a.getAge()));

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Optional<Adult> findUserByUsername(String username) {
        for (Adult c : adults) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }

    public Adult findUserInDB(String username) {
    String sql = "SELECT * FROM adults WHERE username = ?";

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

                return new Adult(u, p, fn, ag);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public static AdultRepository getInstance() {
        return AdultRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static AdultRepository INSTANCE = new AdultRepository();
    }

}
