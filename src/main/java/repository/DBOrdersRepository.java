package repository;

import managers.DBConectionManager;
import model.Order;
import model.Student;
import service.AuditService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DBOrdersRepository {
    public void addOrder(Order o) {
        if(isSeatAvailable(o.getSeats()).isPresent() && findByDate(o.getDate()).isPresent()){
            System.out.println("seat is not available");
        }else{

            String sql = "INSERT INTO orders Values (NULL, ?, ?, ?, ?)";
            try (
                    Connection con = DBConectionManager.getInstance().createConection();
                    PreparedStatement statement = con.prepareStatement(sql);
            ) {
                statement.setString(1, o.getPayment());
                statement.setString(2, o.getDate());
                statement.setInt(3, o.getSeats());
                statement.setInt(4, o.getPrice());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Thread t = new Thread();
            t.start();
            try {
                AuditService.getInstance().addToAuditFile(" placed order ", t.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static DBOrdersRepository getInstance() {
        return DBOrdersRepository.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static DBOrdersRepository INSTANCE = new DBOrdersRepository();
    }

    public Optional<Order> isSeatAvailable(int seat) {

        String sql = "SELECT * FROM orders WHERE seat = ?";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setInt(1, seat);

            ResultSet set = statement.executeQuery();
            if (set.next()) {

                String attr1 = set.getString("payment");
                String attr2 = set.getString("date");
                int attr3 = set.getInt("seat");
                int attr4 = set.getInt("price");

                return Optional.of(new Order(attr1, attr2, attr3, attr4));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("checked if seat is available ",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Order> findByDate(String date) {

        String sql = "SELECT * FROM orders WHERE date = ?";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, date);

            ResultSet set = statement.executeQuery();
            if (set.next()) {

                String attr1 = set.getString("payment");
                String attr2 = set.getString("date");
                int attr3 = set.getInt("seat");
                int attr4 = set.getInt("price");

                return Optional.of(new Order(attr1, attr2, attr3, attr4));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("searched for orders on "+ date,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
