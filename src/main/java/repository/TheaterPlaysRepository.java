package repository;

import managers.DBConectionManager;
import model.Student;
import model.Theater;
import model.TheaterPlay;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class TheaterPlaysRepository {

    private static ArrayList<TheaterPlay> plays = new ArrayList<> ();

    public ArrayList<TheaterPlay> getPlays() {
        return plays;
    }

    private TheaterPlaysRepository() {
        plays.add(new TheaterPlay("2020-03-02","50 De Secunde",60,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime"));
        plays.add(new TheaterPlay("2020-03-08","50 De Secunde",60,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime"));
        plays.add(new TheaterPlay("2020-03-01","50 De Secunde",60,"Eugen Gyemant ","Alexandru Voicu, Diana Dumbrava,Lucian Iftime"));
    }
    public static void addPlay(TheaterPlay e) {
        plays.add(e);
    }
    public static void removePlay(TheaterPlay e) {
        plays.remove(e);
    }

    public void addToDtbase(TheaterPlay t) {
        String sql = "INSERT INTO plays Values (NULL, ?, ?, ?, ?, ?)";
        try(
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement= con.prepareStatement(sql);
        ) {
            java.sql.Date sqlDate = new java.sql.Date((t.getDate()).getTime());
            statement.setDate(1, sqlDate);
            statement.setString(2,t.getName());
            statement.setInt(3, t.getMaxNumberSeats());
            statement.setString(4, t.getDirector());
            statement.setString(5, t.getActors());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static TheaterPlay findEventInDB(String name) {
        String sql = "SELECT * FROM plays WHERE name = ?";

        try(
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement= con.prepareStatement(sql);
        ) {
            statement.setString(1,name);

            ResultSet set = statement.executeQuery();
            set.next();

            String u = set.getString("date");
            String n = set.getString("name");
            int s = set.getInt("maxNumberSeats");
            String d = set.getString("director");
            String a = set.getString("actors");

            return new TheaterPlay(u,n,s,d,a);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public static TheaterPlaysRepository getInstance() {
        return TheaterPlaysRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static TheaterPlaysRepository INSTANCE = new TheaterPlaysRepository();
    }


}
