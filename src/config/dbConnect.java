package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class dbConnect {

    private Connection connect;

    public dbConnect() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_billing", "root", "");
        } catch (SQLException ex) {
            System.out.println("Can't connect to database: " + ex.getMessage());
        }
    }

    public int insertData(String sql) {
        int result;
        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Inserted Successfully!");
            pst.close();
            result = 1;
        } catch (SQLException ex) {
            System.out.println("Connection Error: " + ex);
            result = 0;
        }
        return result;
    }
    public void updateData(String sql){
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                    int rowsUpdated = pst.executeUpdate();
                        if(rowsUpdated > 0){
                            JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                        }else{
                            System.out.println("Data Update Failed!");
                        }
                        pst.close();
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
            }
        
        }

    public ResultSet getData(String sql) throws SQLException {
        Statement stmt = connect.createStatement();
        ResultSet rst = stmt.executeQuery(sql);
        return rst;
    }

    public int executeQueryForCount(String query) {
        int count = 0;
        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error in executeQueryForCount: " + e.getMessage());
        }
        return count;
    }

    public boolean checkLogin(String username, String password) {
        boolean isValidUser = false;
        try {
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");

                if ("Approved".equals(status)) {
                    isValidUser = true;
                } else if ("Pending".equals(status)) {
                    isValidUser = false;
                }
            }
            rs.close();
            pst.close();

        } catch (SQLException ex) {
            System.out.println("Error during login check: " + ex.getMessage());
        }
        return isValidUser;
    }

    public String getUserType(String username) {
        String usertype = null;
        try {
            String sql = "SELECT usertype FROM user WHERE username = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                usertype = rs.getString("usertype");
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            System.out.println("Error during fetching Usertype: " + ex.getMessage());
        }
        return usertype;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
   
}