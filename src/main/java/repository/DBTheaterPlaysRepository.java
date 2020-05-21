package repository;

import managers.DBConectionManager;
import model.TheaterPlay;
import service.AuditService;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DBTheaterPlaysRepository implements TheaterPlaysRepository {
    @Override
    public void addPlay(TheaterPlay t) {
        String sql = "INSERT INTO plays Values (NULL, ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, t.getName());
            statement.setString(2, t.getActors());
            statement.setString(3, t.getDirector());
            statement.setInt(4, t.getMaxNumberSeats());
            java.sql.Date sqlDate = Date.valueOf(t.getDate());
            statement.setDate(5, sqlDate);
            statement.setString(6,t.getEndDate().toString());
            statement.setInt(7, t.getNrRepresentations());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Thread t1 = new Thread();
        t1.start();
        try {
            AuditService.getInstance().addToAuditFile("added "+ t.getName()+"to the datatbase",t1.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TheaterPlay findPlay(String name) {
        String sql = "SELECT * FROM plays WHERE name = ?";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, name);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                String data1 = set.getString("startDate");
                String n = set.getString("name");
                int s = set.getInt("maxNrSeats");
                String d = set.getString("director");
                String a = set.getString("actors");
                String endDate = set.getString("endDate");
                int r = set.getInt("nrRepresentations");

                return new TheaterPlay(data1, n, s, d, a,endDate,r);
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
        return null;
    }

    public ArrayList<TheaterPlay> getPlays(){
        String sql = "SELECT * FROM plays";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ArrayList<TheaterPlay> plays = new ArrayList<>();
            ResultSet set = statement.executeQuery();

            while(set.next()) {

                String data1 = set.getString("startDate");
                String n = set.getString("name");
                int s = set.getInt("maxNrSeats");
                String d = set.getString("director");
                String a = set.getString("actors");
                String endDate = set.getString("endDate");
                int r = set.getInt("nrRepresentations");

                TheaterPlay p = new TheaterPlay(data1, n, s, d,a,endDate,r);
                plays.add(p);
            }
            return plays;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void showPlays() {
        ArrayList<TheaterPlay> plays = getPlays();

        for (int i = 0; i < (plays).size(); i++) {
            System.out.println(plays.get(i).toString());
        }

        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("show plays",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deletePlay(String name) {
        String sql = "DELETE FROM plays WHERE name = '" + name + "' ";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                Statement statement = con.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("Play deleted");

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

    public void updatePlay(String name,String choice, String newValue) {
        TheaterPlay play = findPlay(name);
        if(choice.equals("actors")) {
            String sql = "UPDATE plays SET actors = '" + newValue + "' WHERE name = '" + name + "' ";
            try (
                    Connection con = DBConectionManager.getInstance().createConection();
                    Statement statement = con.createStatement();
            ) {
                statement.executeUpdate(sql);
                System.out.println("Play updated !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if(choice.equals("startDate")) {
            String sql = "UPDATE plays SET startDate = '" + newValue + "' WHERE name = '" + name + "' ";
            try (
                    Connection con = DBConectionManager.getInstance().createConection();
                    Statement statement = con.createStatement();
            ) {
                statement.executeUpdate(sql);
                System.out.println("Play updated !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if(choice.equals("endDate")) {
            String sql = "UPDATE plays SET endDate = '" + newValue + "' WHERE name = '" + name + "' ";
            try (
                    Connection con = DBConectionManager.getInstance().createConection();
                    Statement statement = con.createStatement();
            ) {
                statement.executeUpdate(sql);
                System.out.println("Play updated !");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("updated "+ name,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DBTheaterPlaysRepository getInstance() {
        return DBTheaterPlaysRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static DBTheaterPlaysRepository INSTANCE = new DBTheaterPlaysRepository();

    }
}