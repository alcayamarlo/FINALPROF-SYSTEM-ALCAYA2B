package Project;
import java.sql.*;

public class ConnectionProvider {
    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_bill", "root", ""
            );
        } catch (Exception e) {
            e.printStackTrace(); // Print error for debugging
            return null;
        }
    }
}
