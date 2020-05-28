package repository;

import managers.DBConectionManager;
import model.Concert;
import service.AuditService;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DBConcertRepository implements ConcertRepository {
     @Override
    public void addConcert(Concert c) {
         String sql = "INSERT INTO concerts Values (NULL, ?, ?, ?, ?)";
         try (
                 Connection con = DBConectionManager.getInstance().createConection();
                 PreparedStatement statement = con.prepareStatement(sql);
         ) {
             java.sql.Date sqlDate = Date.valueOf(c.getDate());
             statement.setDate(1, sqlDate);
             statement.setString(2, c.getName());
             statement.setInt(3, c.getMaxNumberSeats());
             statement.setString(4, c.getPerformers());

             statement.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }

         Thread t = new Thread();
         t.start();
         try {
             AuditService.getInstance().addToAuditFile("added "+c.getName(),t.getName());
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    @Override
    public Optional<Concert> findConcert(String name) {
        String sql = "SELECT * FROM concerts WHERE name = ?";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, name);

            ResultSet set = statement.executeQuery();
            if(set.next()) {

                String u = set.getString("date");
                String n = set.getString("name");
                int s = set.getInt("maxNumberSeats");
                String d = set.getString("performers");

                return Optional.of(new Concert(u, n, s, d));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("searched for  "+ name,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }
    @Override
    public void deleteConcert(String name) {
        String sql = "DELETE FROM concerts WHERE name = '" + name + "' ";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                Statement statement = con.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("Concert deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("deleted "+ name,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void showConcerts() {
        ArrayList<Concert> c = getConcerts();
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).toString());
        }

        Thread t1 = new Thread();
        t1.start();
        try {
            AuditService.getInstance().addToAuditFile("show concerts",t1.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Concert> getConcerts(){
        String sql = "SELECT * FROM concerts";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ArrayList<Concert> concerts = new ArrayList<>();
            ResultSet set = statement.executeQuery();

            while(set.next()) {

                String u = set.getString("date");
                String n = set.getString("name");
                int s = set.getInt("maxNumberSeats");
                String d = set.getString("performers");

                Concert c = new Concert(u, n, s, d);
                concerts.add(c);
            }
            return concerts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
     }
    public static DBConcertRepository getInstance() {
        return DBConcertRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static DBConcertRepository INSTANCE = new DBConcertRepository();
    }

}
