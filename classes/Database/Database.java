package Database;
import java.sql.*;

public class Database {
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/retro_world";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    public Connection getConnection() throws SQLException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return conn;
    }
    
    
}
