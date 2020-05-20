package repository;
import managers.DBConectionManager;
import model.Student;

import java.sql.*;
import java.util.Optional;

public class DBStudentRepository implements StudentRepository {

    @Override
    public void addStudent(Student s) {
        String sql = "INSERT INTO students Values (NULL, ?, ?, ?, ?)";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, s.getUsername());
            statement.setString(2, s.getPassword());
            statement.setString(3, s.getFirstName());
            statement.setInt(4, s.getStudentIdNo());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<Student> findUserByName(String username) {

        String sql = "SELECT * FROM students WHERE username = ?";
        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();
            if (set.next()) {

                String u = set.getString("username");
                String p = set.getString("password");
                String fn = set.getString("first_name");
                int stid = set.getInt("studentIdNo");
                return Optional.of(new Student(u, p, fn, stid));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public static void deleteStudent(String username) {
        String sql = "DELETE FROM students WHERE username = '" + username + "' ";
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
    public static DBStudentRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static DBStudentRepository INSTANCE = new DBStudentRepository();
    }
}
