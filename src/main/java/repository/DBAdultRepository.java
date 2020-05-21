package repository;

import managers.DBConectionManager;
import model.Adult;
import model.Child;
import service.AuditService;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DBAdultRepository implements AdultRepository{
    public DBAdultRepository() {

    }
    @Override
    public void addAdult(Adult a) {
        String sql = "INSERT INTO adults Values (NULL, ?, ?, ?)";
        try(
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement= con.prepareStatement(sql);
        ) {
            statement.setString(1,a.getUsername());
            statement.setString(2,a.getPassword());
            statement.setString(3,a.getFirstName());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Adult> findUserByName(String username) {
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

                return Optional.of(new Adult(u, p, fn));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("searched for "+ username,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Adult> getAdults() {
        String sql = "SELECT * FROM adults";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ArrayList<Adult> adults = new ArrayList<>();
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                String u = set.getString("username");
                String p = set.getString("password");
                String fn = set.getString("first_name");

                Adult c = new Adult(u, p, fn);
                adults.add(c);
            }
            return adults;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showAdults() {
        String threadName = Thread.currentThread().getName();

        ArrayList<Adult> c = getAdults();
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).toString());
        }

        AuditService audit = new AuditService();
        try {
            audit.addToAuditFile("printed all adult clients",threadName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAdult(String username) {
        String sql = "DELETE FROM adults WHERE username = '" + username + "' ";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                Statement statement = con.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("User deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBAdultRepository getInstance() {
        return DBAdultRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static DBAdultRepository INSTANCE = new DBAdultRepository();
    }

}
