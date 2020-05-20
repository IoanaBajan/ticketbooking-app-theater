package repository;

import managers.DBConectionManager;
import model.Concert;
import model.TheaterPlay;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DBTheaterPlaysRepository implements TheaterPlaysRepository {
    @Override
    public void addPlay(TheaterPlay t) {
        String sql = "INSERT INTO plays Values (NULL, ?, ?, ?, ?, ?)";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            java.sql.Date sqlDate = new java.sql.Date((t.getDate()).getTime());
            statement.setDate(1, sqlDate);
            statement.setString(2, t.getName());
            statement.setInt(3, t.getMaxNumberSeats());
            statement.setString(4, t.getDirector());
            statement.setString(5, t.getActors());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<TheaterPlay> findPlay(String name) {
        String sql = "SELECT * FROM plays WHERE name = ?";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, name);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String u = set.getString("date");
                String n = set.getString("name");
                int s = set.getInt("maxNumberSeats");
                String d = set.getString("director");
                String a = set.getString("actors");

                return Optional.of(new TheaterPlay(u, n, s, d, a));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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

                String u = set.getString("date");
                String n = set.getString("name");
                int s = set.getInt("maxNumberSeats");
                String d = set.getString("director");
                String a = set.getString("actors");

                TheaterPlay p = new TheaterPlay(u, n, s, d,a);
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
    }
    public static void deletePlay(String name) {
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
    }

    public static DBTheaterPlaysRepository getInstance() {
        return DBTheaterPlaysRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static DBTheaterPlaysRepository INSTANCE = new DBTheaterPlaysRepository();

    }
}