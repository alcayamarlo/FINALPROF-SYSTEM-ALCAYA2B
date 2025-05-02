/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Successfull.noAccount;
import admin.ManageUsers;
import admin.createUserForm;
import config.Session;
import config.dbConnect;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import login.Main;

/**
 *
 * @author alcay
 */
public class userForm extends javax.swing.JFrame {

    /**
     * Creates new form userForm
     */
    public userForm() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
    
  private String userId; // Declare userId at the class level

    public void setUserId(String id) {
        this.userId = id; // Store the user ID for later use
    }
 public String destination = ""; 
    File selectedFile;
    public String oldpath;
    public String path; 
    
    
      public boolean duplicateCheck() {
    dbConnect dbc = new dbConnect();
    try {
        String query = "SELECT * FROM users WHERE username = '" + un.getText() + "' OR email = '" + em.getText() + "'";
        ResultSet resultSet = dbc.getData(query);

        if (resultSet.next()) {
            String email = resultSet.getString("email");
            if (email.equals(em.getText())) {
                JOptionPane.showMessageDialog(null, "Email is Already used");
                em.setText("");
            }
            String username = resultSet.getString("username");
            if (username.equals(un.getText())) { //Error: You were comparing username with email's text field. changed to un1's text field
                JOptionPane.showMessageDialog(null, "Username is Already used"); //Error: Changed the message to reflect username duplication
                un.setText("");
            }
            return true;
        } else {
            return false;
        }
    } catch (SQLException ex) {
        System.out.println("" + ex);
        return false;
    }
}
  
  public boolean updateCheck() {
    dbConnect dbc = new dbConnect();
    try {
        String query = "SELECT * FROM users WHERE (username = '" + un.getText() + "' OR email = '" + em.getText() + "')AND p_id!= '"+p_id.getText()+"'";
        ResultSet resultSet = dbc.getData(query);

        if (resultSet.next()) {
            String email = resultSet.getString("email");
            if (email.equals(em.getText())) {
                JOptionPane.showMessageDialog(null, "Email is Already used");
                em.setText("");
            }
            String username = resultSet.getString("username");
            if (username.equals(un.getText())) { //Error: You were comparing username with email's text field. changed to un1's text field
                JOptionPane.showMessageDialog(null, "Username is Already used"); //Error: Changed the message to reflect username duplication
                un.setText("");
            }
            return true;
        } else {
            return false;
        }
    } catch (SQLException ex) {
        System.out.println("" + ex);
        return false;
    }
}
    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }    
    
       public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
            if(ImagePath !=null){
                MyImage = new ImageIcon(ImagePath);
            }else{
                MyImage = new ImageIcon(pic);
            }

        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
     public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/usersimages", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
    
        public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }
         private String selectedImagePath = "";
   
        public void logEvent(int userId, String username, String userType, String logDescription) {
    dbConnect dbc = new dbConnect();
    Connection con = dbc.getConnection();
    PreparedStatement pstmt = null;

    try {
        String sql = "INSERT INTO tbl_log (p_id, u_username, login_time, u_type, log_status) VALUES (?, ?, ?, ?, ?)";
        pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, userId);
        pstmt.setString(2, username);
        pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
        pstmt.setString(4, userType); // This should be "Admin" or "User"
        pstmt.setString(5, "Active");

        pstmt.executeUpdate();
        System.out.println("Log recorded successfully.");
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        un = new textfield.TextField();
        fn = new textfield.TextField();
        ct = new textfield.TextField();
        em = new textfield.TextField();
        cn = new textfield.TextField();
        p_id = new textfield.TextField();
        jLabel27 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        submit = new rojerusan.RSMaterialButtonCircle();
        cancel = new rojerusan.RSMaterialButtonCircle();
        jLabel9 = new javax.swing.JLabel();
        utype = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        u_image = new javax.swing.JLabel();
        remove = new rojerusan.RSMaterialButtonCircle();
        select = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/full.png"))); // NOI18N
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 60));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/user.png"))); // NOI18N
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, 60));

        jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/cnn.png"))); // NOI18N
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, 60));

        jLabel25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/emm.png"))); // NOI18N
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, 60));

        un.setBackground(new java.awt.Color(204, 255, 255));
        un.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        un.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        un.setLabelText("Enter your Username");
        getContentPane().add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 330, 60));

        fn.setBackground(new java.awt.Color(204, 255, 255));
        fn.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        fn.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        fn.setLabelText("Enter your Full Name");
        getContentPane().add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 330, 60));

        ct.setBackground(new java.awt.Color(204, 255, 255));
        ct.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        ct.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        ct.setLabelText("Enter your City and Address");
        ct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctActionPerformed(evt);
            }
        });
        getContentPane().add(ct, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 330, 60));

        em.setBackground(new java.awt.Color(204, 255, 255));
        em.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        em.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        em.setLabelText("Enter your Email");
        getContentPane().add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 330, 60));

        cn.setBackground(new java.awt.Color(204, 255, 255));
        cn.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        cn.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        cn.setLabelText("Enter your Contact No");
        getContentPane().add(cn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 330, 60));

        p_id.setBackground(new java.awt.Color(204, 255, 255));
        p_id.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        p_id.setEnabled(false);
        p_id.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        p_id.setLabelText("User ID");
        getContentPane().add(p_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 330, 60));

        jLabel27.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/idd.png"))); // NOI18N
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, 60));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 2, 16)); // NOI18N
        jLabel1.setText("Click Here.");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 630, -1, -1));

        submit.setBackground(new java.awt.Color(255, 51, 51));
        submit.setText("SUBMIT");
        submit.setFont(new java.awt.Font("Arial Black", 1, 17)); // NOI18N
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitMouseClicked(evt);
            }
        });
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        jPanel1.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 710, 390, 60));

        cancel.setBackground(new java.awt.Color(204, 204, 255));
        cancel.setForeground(new java.awt.Color(51, 51, 51));
        cancel.setText("CANCEL");
        cancel.setFont(new java.awt.Font("Arial Black", 1, 17)); // NOI18N
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 770, 390, 60));

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Do you want to Cancel?");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 830, -1, 30));

        utype.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        utype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User Type :", "Admin", "Patient" }));
        utype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utypeActionPerformed(evt);
            }
        });
        jPanel1.add(utype, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 660, 150, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel2.setText("Do you want to change your Password?");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 630, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/images-24-removebg-preview.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, 60));

        jLabel3.setBackground(new java.awt.Color(51, 51, 255));
        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setText("  X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 40, 40));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 255)));
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 40, 40));

        jLabel19.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("EDIT YOUR DETAILS");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Edit your information");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, 30));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(u_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 190));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 280, 210));

        remove.setText("REMOVE");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel1.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 250, 60));

        select.setText("ADD PROFILE");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel1.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 260, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
      userDashboard usd = new userDashboard();
      usd.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void ctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
 Session sess = Session.getInstance();
        if (sess.getPid() == 0) {
            noAccount acc = new noAccount();
            acc.setVisible(true);
            this.dispose();
        } else {
            p_id.setText(String.valueOf(sess.getPid()));
            fn.setText(sess.getFn());
            ct.setText(sess.getCityAddress());
            em.setText(sess.getEmail());
            cn.setText(sess.getContactNo());
            un.setText(sess.getUsername());
            utype.setSelectedItem(sess.getUsertype());
        }     
    }//GEN-LAST:event_formWindowActivated

    private void utypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utypeActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        userDashboard ud = new userDashboard();
        ud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
      dbConnect dbc = new dbConnect();
Session sess = Session.getInstance();
int userId = 0;
String uname2 = null;

// Check if username or email already exists
if (updateCheck()) {
    return;
}

// Validate inputs
if (fn.getText().isEmpty() || ct.getText().isEmpty() ||
    em.getText().isEmpty() || cn.getText().isEmpty() || un.getText().isEmpty()) {
    JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

String query = "UPDATE users SET fn = ?, cityAddress = ?, email = ?, contactNo = ?, username = ?, image = ? WHERE p_id = ?";

try (Connection conn = dbc.getConnection();
     PreparedStatement pstmt = conn.prepareStatement(query)) {

    pstmt.setString(1, fn.getText());
    pstmt.setString(2, ct.getText());
    pstmt.setString(3, em.getText());
    pstmt.setString(4, cn.getText());
    pstmt.setString(5, un.getText());
    pstmt.setString(6, selectedImagePath);
    pstmt.setInt(7, sess.getPid());

    int rowsAffected = pstmt.executeUpdate();

    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(this, "Profile updated successfully!");

        // Reuse the same connection to get updated data
        String query2 = "SELECT * FROM users WHERE p_id = ?";
        try (PreparedStatement pstmt2 = conn.prepareStatement(query2)) {
            pstmt2.setInt(1, sess.getPid());

            try (ResultSet resultSet = pstmt2.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("p_id");
                    uname2 = resultSet.getString("username");
                }
            }
        }

        logEvent(userId, uname2, sess.getUsertype(), "User Changed Their Details");

    } else {
        JOptionPane.showMessageDialog(this, "Failed to update profile!", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (SQLException ex) {
    System.out.println("SQL Error: " + ex.getMessage());
    JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
}

// Optional: redirect back to dashboard after update
userDashboard ud = new userDashboard();
ud.setVisible(true);
this.dispose();

    }//GEN-LAST:event_submitActionPerformed

    private void submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseClicked

    }//GEN-LAST:event_submitMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        userChangepass user = new userChangepass();
        user.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        remove.setEnabled(false);
        select.setEnabled(true);
        u_image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_removeActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/usersimages/" + selectedFile.getName();
                path  = selectedFile.getAbsolutePath();

                if(FileExistenceChecker(path) == 1){
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path= "";
                }else{
                    u_image.setIcon(ResizeImage(path, null, u_image));
                    select.setEnabled(false);
                    remove.setEnabled(true);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_selectActionPerformed

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
            java.util.logging.Logger.getLogger(userForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle cancel;
    public textfield.TextField cn;
    public textfield.TextField ct;
    public textfield.TextField em;
    public textfield.TextField fn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public textfield.TextField p_id;
    private rojerusan.RSMaterialButtonCircle remove;
    private rojerusan.RSMaterialButtonCircle select;
    private rojerusan.RSMaterialButtonCircle submit;
    private javax.swing.JLabel u_image;
    public textfield.TextField un;
    public javax.swing.JComboBox<String> utype;
    // End of variables declaration//GEN-END:variables
}
