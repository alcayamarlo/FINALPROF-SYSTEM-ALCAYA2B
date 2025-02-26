
package Project;
import java.sql.*;
/**
 *
 * @author alcay
 */
public class ConnectionProvider {
  
    public static Connection getCon()
    {
        try 
        {
        Class.forName("com.mysql.jbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://3306/hospital_billing","root","");
        return con;
        
        }
        catch (Exception e)
        {
        return null;
        }
        
    }
    
}
