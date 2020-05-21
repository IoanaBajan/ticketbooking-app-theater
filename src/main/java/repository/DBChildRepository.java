package repository;

import managers.DBConectionManager;
import model.Child;
import model.Concert;
import service.AuditService;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DBChildRepository implements ChildRepository {
    @Override
    public void addChild(Child c) {
        String sql = "INSERT INTO children Values (NULL, ?, ?, ?, ?)";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, c.getUsername());
            statement.setString(2, c.getPassword());
            statement.setString(3, c.getFirstName());
            statement.setInt(4, c.isAccompanied());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile(c.getUsername()+"registered",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Child> findUserByName(String name) {
        String sql = "SELECT * FROM children WHERE username = ?";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, name);

            ResultSet set = statement.executeQuery();
            if(set.next()) {
                String u = set.getString("username");
                String p = set.getString("password");
                String fn = set.getString("first_name");
                int d = set.getInt("accompanied");

                return Optional.of(new Child(u, p, fn, d));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("searched for "+ name,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public static void deleteUser(String name) {
        String sql = "DELETE FROM children WHERE username = '" + name + "' ";
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
    public void showChildren() {
        ArrayList<Child> c = getChildren();
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).toString());
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("printed all child clients ",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Child> getChildren(){
        String sql = "SELECT * FROM children";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ArrayList<Child> children = new ArrayList<>();
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                String u = set.getString("username");
                String p = set.getString("password");
                String fn = set.getString("first_name");
                int d = set.getInt("accompanied");

                Child c = new Child(u, p, fn, d);
                children.add(c);
            }
            return children;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static DBChildRepository getInstance() {
        return DBChildRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static DBChildRepository INSTANCE = new DBChildRepository();
    }

}
