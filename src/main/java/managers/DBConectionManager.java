package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConectionManager {

    public Connection createConection(){
        String url = "jdbc:mysql://localhost/ticketdatabase";
        String username = "root";
        String password = "0764108542";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }
    public static DBConectionManager getInstance(){
        return SingletonHolder.INSTANCE;
    }


    private final static class  SingletonHolder{
        private static final DBConectionManager INSTANCE = new DBConectionManager();
    }

}
