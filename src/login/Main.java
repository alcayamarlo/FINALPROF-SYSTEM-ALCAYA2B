    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import Authentication.SecurityQuestion;
import Successfull.adminSuccess;
import Successfull.loginSuccess;
import Successfull.needApproval;
import Successfull.noAccount;
import admin.dashBoard;
import config.Session;
import config.dbConnect;
import config.passwordHasher;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;
import user.userDashboard;

/**
 *
 * @author RAVEN
 */
public class Main extends javax.swing.JFrame {
    

    /**
     * Creates new form Test
     */
    public Main() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 255, 255));
        
        
        setResizable(false);

        // Set a fixed size for the frame (width, height)
        setSize(800, 600); // Use the same size as defined in your layout

        // Center the frame on the screen
        setLocationRelativeTo(null);
        
        
    }
        static String status;
static String utype;

public static boolean loginAcc(String username, String password) {
    dbConnect connect = new dbConnect();
    try {
        String query = "SELECT * FROM users WHERE username = ?"; 
        java.sql.PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String hashedPass = resultSet.getString("password");
            String rehashedPass = passwordHasher.hashPassword(password);

            if (hashedPass.equals(rehashedPass)) {
                status = resultSet.getString("status");
                utype = resultSet.getString("usertype");

                Session sess = Session.getInstance();
                sess.setPid(resultSet.getInt("p_id"));
                sess.setFn(resultSet.getString("fn"));
                sess.setCityAddress(resultSet.getString("cityAddress"));
                sess.setEmail(resultSet.getString("email"));
                sess.setContactNo(resultSet.getString("contactNo"));
                sess.setUsername(resultSet.getString("username"));
                sess.setPassword(resultSet.getString("password"));
                sess.setUsertype(resultSet.getString("usertype"));
                sess.setStatus(resultSet.getString("status"));

                System.out.println("" + sess.getPid());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    } catch (SQLException | NoSuchAlgorithmException ex) {
        ex.printStackTrace(); // Log the exception
        return false;
    }
}
public String getUserId(String username) {
       
       dbConnect dbc = new dbConnect();
PreparedStatement pstmt = null;
ResultSet rs = null;
String userId = null;

try {
    Connection con = dbc.getConnection();  // ✅ get the connection from dbConnect
    String sql = "SELECT p_id FROM users WHERE username = ?";
    pstmt = con.prepareStatement(sql);
    pstmt.setString(1, username);
    rs = pstmt.executeQuery();

    if (rs.next()) {
        userId = rs.getString("p_id");
    }
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    try {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

           
       
        }
        return userId;
    }
 
  public void logEvent(int userId, String username, String userType) {
    dbConnect dbc = new dbConnect();
    Connection con = dbc.getConnection();
    PreparedStatement pstmt = null;
    String ut = null;

    try {
        // Assuming there's no 'log_time' column, remove it from the query
        String sql = "INSERT INTO tbl_log (p_id, u_username, login_time, u_type, log_status) VALUES (?, ?, ?, ?, ?)";
        pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, userId);
        pstmt.setString(2, username);
        pstmt.setTimestamp(3, new Timestamp(new Date().getTime())); // Make sure 'login_time' column exists
        pstmt.setString(4, userType);
        ut = "Active";
        pstmt.setString(5, ut);

        pstmt.executeUpdate();
        System.out.println("Login log recorded successfully.");
    } catch (SQLException e) {
        System.out.println("Error recording log: " + e.getMessage());
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
        }
    }
}

     public String getUserTypeFromDatabase(String username) {
    String type = "";
    String query = "SELECT usertype FROM users WHERE LOWER(username) = LOWER(?)";
    
    // Use an instance of dbConnector to get the connection
    dbConnect connector = new dbConnect();  // Create instance of dbConnector
    try (Connection con = connector.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            type = rs.getString("usertype");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return type;
}
      public String getStatusFromDatabase(String username) {
    String status = "";
    String query = "SELECT log_status FROM tbl_log WHERE LOWER(u_username) = LOWER(?) ORDER BY login_time DESC LIMIT 1";
    
    // Use an instance of dbConnector to get the connection
    dbConnect connector = new dbConnect();  // Create instance of dbConnector
    try (Connection con = connector.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            status = rs.getString("log_status");
            System.out.println("status: "+status);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
}
    



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        loginPane = new javax.swing.JPanel();
        un = new textfield.TextField();
        pass = new textfield.PasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Signup = new rojerusan.RSMaterialButtonCircle();
        loginAcc = new rojerusan.RSMaterialButtonCircle();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 255)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logooooo.png"))); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 210, 340, 250));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("HOSPITAL BILLING ");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 280, 30));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("SYSTEM");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 100, 30));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setText("HOSPITAL BILLING ");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 280, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/fb.png"))); // NOI18N
        jLabel7.setText("Alcaya Marlo");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/ig.jpg"))); // NOI18N
        jLabel8.setText("@atreuz_szee");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 600));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 600));

        loginPane.setBackground(new java.awt.Color(255, 255, 255));
        loginPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        un.setBackground(new java.awt.Color(204, 255, 255));
        un.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        un.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        un.setLabelText("Username");
        loginPane.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 300, 60));

        pass.setBackground(new java.awt.Color(204, 255, 255));
        pass.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        pass.setLabelText("Password\n");
        pass.setShowAndHide(true);
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        loginPane.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 300, 60));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setText("  X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        loginPane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 40, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 0, 51)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        loginPane.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 40, 40));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 2, new java.awt.Color(51, 51, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel17.setText("I forgot my Password.");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, -1, 30));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
        jLabel9.setText("Welcome back! Please Enter your details");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 330, 30));

        jLabel19.setBackground(new java.awt.Color(102, 102, 102));
        jLabel19.setFont(new java.awt.Font("Arial Black", 0, 45)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/login ani.gif"))); // NOI18N
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 100));

        Signup.setBackground(new java.awt.Color(204, 204, 255));
        Signup.setForeground(new java.awt.Color(51, 51, 51));
        Signup.setText("SIGN UP");
        Signup.setFont(new java.awt.Font("Arial Black", 1, 17)); // NOI18N
        Signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignupMouseClicked(evt);
            }
        });
        Signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignupActionPerformed(evt);
            }
        });
        jPanel5.add(Signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 320, 60));

        loginAcc.setBackground(new java.awt.Color(255, 51, 51));
        loginAcc.setText("LOG IN");
        loginAcc.setFont(new java.awt.Font("Arial Black", 1, 17)); // NOI18N
        loginAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginAccMouseClicked(evt);
            }
        });
        loginAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginAccActionPerformed(evt);
            }
        });
        jPanel5.add(loginAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 320, 60));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/pass.png"))); // NOI18N
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, 60));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/user.png"))); // NOI18N
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 60));

        loginPane.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 420, 600));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(51, 51, 255)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        loginPane.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 600));

        getContentPane().add(loginPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, -10, 470, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonActionPerformed

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
    
    }//GEN-LAST:event_loginButtonMouseClicked

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void loginAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginAccMouseClicked
        loginSuccess log = new loginSuccess();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_loginAccMouseClicked

    private void loginAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginAccActionPerformed
String username = un.getText();
String password = new String(pass.getPassword());

if (un.getText().isEmpty() && pass.getPassword().length == 0) {
    JOptionPane.showMessageDialog(null, "All Fields Are Required");
} else if (un.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Username is required");
} else if (pass.getPassword().length == 0) {
    JOptionPane.showMessageDialog(null, "Password is required");
} else if (pass.getPassword().length < 8) {
    JOptionPane.showMessageDialog(null, "Password should have at least 8 characters");
} else {
    if (loginAcc(username, password)) {
        Session sess = Session.getInstance();
        int userId = sess.getPid();

        String status = getStatusFromDatabase(username);  // e.g., Approved, Pending
        String type = getUserTypeFromDatabase(username);  // e.g., Admin, Patient

        // ✅ Skip status check if user is Admin
        if (!"Admin".equalsIgnoreCase(type)) {
            dbConnect connector = new dbConnect();
            try {
                String query2 = "SELECT status FROM users WHERE username = ?";
                PreparedStatement pstmt = connector.getConnection().prepareStatement(query2);
                pstmt.setString(1, username);

                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    status = resultSet.getString("status");
                }

                resultSet.close();
                pstmt.close();
            } catch (SQLException ex) {
                System.out.println("SQL Exception: " + ex);
            }

            // Block non-admins if not approved
            if (!"Approved".equalsIgnoreCase(status)) {
                needApproval na = new needApproval();
                na.setVisible(true);
                this.dispose();
                logEvent(userId, username, "Failed - Inactive Account");
                return; // ❌ Skip login continuation
            }
        }

        // ✅ Handle logins based on type
        if ("Admin".equalsIgnoreCase(type)) {
            adminSuccess ad = new adminSuccess();
            ad.setVisible(true);
            this.dispose();
            logEvent(userId, username, "Success - Admin Login");
        } else if ("Patient".equalsIgnoreCase(type)) {
            loginSuccess log = new loginSuccess();
            log.setVisible(true);
            this.dispose();
            logEvent(userId, username, "Success - User Login");
        } else {
            JOptionPane.showMessageDialog(null, "No account type found, Contact the Admin");
        }

    } else {
        JOptionPane.showMessageDialog(null, "Invalid Account");
        logEvent(-1, username, "Failed - Invalid Login");
    }
}




        

       
    }//GEN-LAST:event_loginAccActionPerformed

    private void SignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignupMouseClicked

    }//GEN-LAST:event_SignupMouseClicked

    private void SignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignupActionPerformed
        registerUser rtu = new registerUser();
        rtu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SignupActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
      
    }//GEN-LAST:event_passKeyPressed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
     SecurityQuestion sq = new SecurityQuestion();
        sq.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
    
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
       
    }//GEN-LAST:event_jLabel17MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle Signup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private rojerusan.RSMaterialButtonCircle loginAcc;
    private javax.swing.JPanel loginPane;
    private textfield.PasswordField pass;
    private textfield.TextField un;
    // End of variables declaration//GEN-END:variables
}
