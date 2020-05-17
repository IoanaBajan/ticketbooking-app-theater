package repository;
import managers.DBConectionManager;
import model.Adult;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StudentRepository {
    private static ArrayList<Student> students = new ArrayList<> ();
    public ArrayList<Student> getStudents() {
        return students;
    }

    public StudentRepository() {

        this.students.add( new Student("alabala","09876","portocala",23,11299));
        this.students.add(new Student("yolo123","locked","Ioana",20,21564));

    }

    public static void addStudent(Student c) {
        students.add(c);
    }
    public void addToDtbase(Student s) {
        String sql = "INSERT INTO students Values (NULL, ?, ?, ?, ?, ?)";
        try(
                Connection con = DBConectionManager.getInstance().createConection();
                PreparedStatement statement= con.prepareStatement(sql);
        ) {
            statement.setString(1,s.getUsername());
            statement.setString(2,s.getPassword());
            statement.setString(3,s.getFirstName());
            statement.setString(4, String.valueOf(s.getAge()));
            statement.setInt(5, s.getStudentIdNo());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Student findUserInDB(String username) {
        String sql = "SELECT * FROM students WHERE username = ?";
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
                int stid = set.getInt("studentIdNo");
                return new Student(u,p,fn,ag,stid);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
    public Optional<Student> findUserByUsername(String username) {
        for (Student c : students) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }

        return Optional.empty();
    }


    public static StudentRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static StudentRepository INSTANCE = new StudentRepository();
    }
}
