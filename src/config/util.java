
package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class util {
    
    // Validate password strength
    public static boolean isValidPassword(String password) {
        // Regex for password validation
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password.matches(passwordRegex);
    }
    
    // Validate email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
    
    // Validate email duplication
    public static boolean isEmailExists(String email) {
        dbConnect con = new dbConnect();
        try (Connection cn = con.getConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
            try (PreparedStatement pst = cn.prepareStatement(query)) {
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
    // Validate username duplication
    public static boolean isUsernameExists(String username) {
        dbConnect con = new dbConnect();
        try (Connection cn = con.getConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement pst = cn.prepareStatement(query)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Username exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Username does not exist
    }
}
