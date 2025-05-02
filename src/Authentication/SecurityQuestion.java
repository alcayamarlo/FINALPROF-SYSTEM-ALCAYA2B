/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;
import java.sql.*;
import config.Session;
import config.dbConnect;
import config.passwordHasher;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import login.Main;

/**
 *
 * @author alcay
 */
public class SecurityQuestion extends javax.swing.JFrame {
private String correctAnswer;
    /**
     * Creates new form SecurityQuestion
     */
    public SecurityQuestion() {
        initComponents();
    }
 private void fetchSecurityQuestion() {
        String username = un.getText();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username.");
            return;
        }

        dbConnect db = new dbConnect();
        Connection con = db.getConnection();

        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
            return;
        }

        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT securityQ, answer FROM users WHERE username = ?"
            );
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sq.removeAllItems();
                sq.addItem(rs.getString("securityQ"));
                sq.setEnabled(true);
                correctAnswer = rs.getString("answer");
                submit.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Username not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching the security question.");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void logEvent(int userId, String username, String description) {
        dbConnect dbc = new dbConnect();
        Connection con = dbc.getConnection();
        PreparedStatement pstmt = null;

        try {
            String sql = "INSERT INTO tbl_log (p_id, u_username, login_time, u_type, log_status) VALUES (?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setString(2, username);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(4, "Success - User Action");
            pstmt.setString(5, "Active");

            pstmt.executeUpdate();
            System.out.println("Log event recorded successfully.");
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

    private void resetPassword() {
        String enteredAnswer = ans.getText();
        String newPassword = new String(Newpass.getPassword()); // Use getPassword()

        int userId = -1;
        String uname2 = "";

        if (correctAnswer == null) {
            JOptionPane.showMessageDialog(this, "Please search for your username first.");
            return;
        }

        // No hashing for security answer; compare as plain text
        if (!enteredAnswer.equalsIgnoreCase(correctAnswer.trim())) {
            JOptionPane.showMessageDialog(this, "Incorrect security answer.");
            return;
        }

        try {
            String hashedPassword = passwordHasher.hashPassword(newPassword);

            dbConnect db = new dbConnect();
            Connection con = db.getConnection();

            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
                return;
            }

            try {
                PreparedStatement stmt = con.prepareStatement(
                        "UPDATE users SET password = ? WHERE username = ?"
                );
                stmt.setString(1, hashedPassword);
                stmt.setString(2, un.getText());

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Password successfully reset!");

                    // Log user event
                    String query2 = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement pstmt = con.prepareStatement(query2);
                    pstmt.setString(1, un.getText());
                    ResultSet resultSet = pstmt.executeQuery();

                    if (resultSet.next()) {
                        userId = resultSet.getInt("p_id");
                        uname2 = resultSet.getString("username");
                        logEvent(userId, uname2, "User Reset Their Password");
                    }

                    dispose(); // Close the window
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Username not found or password update failed.");
                }
            } finally {
                con.close();
            }

        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(this, "Error hashing password: " + ex.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while updating the password.");
        }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        un = new textfield.TextField();
        ans = new textfield.TextField();
        search = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        back = new rojerusan.RSMaterialButtonCircle();
        submit = new rojerusan.RSMaterialButtonCircle();
        sq = new javax.swing.JComboBox<>();
        Newpass = new textfield.PasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 153, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logooooo.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 230, 340, 250));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("HOSPITAL BILLING ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 280, 30));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("SYSTEM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 100, 30));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setText("HOSPITAL BILLING ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 280, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/fb.png"))); // NOI18N
        jLabel7.setText("Alcaya Marlo");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/ig.jpg"))); // NOI18N
        jLabel8.setText("@atreuz_szee");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel6.setText("SECURITY QUESTION");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 500, -1));

        jLabel3.setFont(new java.awt.Font("Malgun Gothic", 0, 24)); // NOI18N
        jLabel3.setText("Answer your Security question to reset the password.");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        un.setBackground(new java.awt.Color(204, 255, 255));
        un.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        un.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        un.setLabelText("Enter your Username");
        jPanel1.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 270, 60));

        ans.setBackground(new java.awt.Color(204, 255, 255));
        ans.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        ans.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        ans.setLabelText("Enter your Answer");
        jPanel1.add(ans, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 260, 60));

        search.setText("Search");
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 80, -1));

        jLabel13.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel13.setText("x");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 30, -1));

        jLabel14.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel14.setText("-");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 30, 20));

        back.setBackground(new java.awt.Color(255, 51, 51));
        back.setText("BACK");
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, 280, 60));

        submit.setText("SUBMIT");
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitMouseClicked(evt);
            }
        });
        jPanel1.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 460, 280, 60));

        sq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Whats your Security Question?" }));
        jPanel1.add(sq, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 260, 50));

        Newpass.setBackground(new java.awt.Color(204, 255, 255));
        Newpass.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        Newpass.setLabelText("Password\n");
        Newpass.setShowAndHide(true);
        Newpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewpassActionPerformed(evt);
            }
        });
        Newpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NewpassKeyPressed(evt);
            }
        });
        jPanel1.add(Newpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, 270, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
     
    }//GEN-LAST:event_searchActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
       Main mn = new Main();
       mn.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
     
    }//GEN-LAST:event_jLabel14MouseClicked

    private void submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseClicked
        resetPassword(); 
    }//GEN-LAST:event_submitMouseClicked

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        String username = un.getText();  
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your username.");
        return;
    }

    // Create a database connection
    dbConnect db = new dbConnect();  // Instantiate dbConnector
    Connection con = db.getConnection(); // Get connection

    if (con == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed. Please try again later.");
        return;
    }

    try {
        PreparedStatement stmt = con.prepareStatement(
            "SELECT securityQ, answer FROM users WHERE username = ?"
        );
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            sq.removeAllItems();
            sq.addItem(rs.getString("securityQ"));
            sq.setEnabled(true);
          correctAnswer = rs.getString("answer"); // still okay if hashed

            submit.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while fetching the security question.");
    } finally {
        try {
            if (con != null) {
                con.close(); // Close the connection after use
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    }//GEN-LAST:event_searchMouseClicked

    private void NewpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewpassActionPerformed

    private void NewpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NewpassKeyPressed

    }//GEN-LAST:event_NewpassKeyPressed

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
            java.util.logging.Logger.getLogger(SecurityQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecurityQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecurityQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecurityQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecurityQuestion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private textfield.PasswordField Newpass;
    private textfield.TextField ans;
    private rojerusan.RSMaterialButtonCircle back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton search;
    private javax.swing.JComboBox<String> sq;
    private rojerusan.RSMaterialButtonCircle submit;
    private textfield.TextField un;
    // End of variables declaration//GEN-END:variables
}
