package repository;
import managers.DBConectionManager;
import model.Child;
import model.Student;
import service.AuditService;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile(s.getUsername()+" registered",t.getName());
        } catch (IOException e) {
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
    public ArrayList<Student> getStudents() {
        String sql = "SELECT * FROM students";

        try (
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ArrayList<Student> students = new ArrayList<>();
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                String u = set.getString("username");
                String p = set.getString("password");
                String fn = set.getString("first_name");
                int stid = set.getInt("studentIdNo");


                Student s = new Student(u, p, fn, stid);
                students.add(s);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void showStudents() {
        for (int i = 0; i < getStudents().size(); i++) {
            System.out.println(getStudents().get(i).toString());
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("printed all student clients",t.getName());
        } catch (IOException e) {
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
