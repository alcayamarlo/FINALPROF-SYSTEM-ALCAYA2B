
/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;
import java.sql.*;
import Project.ConnectionProvider;
import Successfull.noAccount;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import config.Session;
import config.dbConnect;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author alcay
 */
public class addBills extends javax.swing.JFrame {
public int finalTotal=0;

    /**
     * Creates new form addBills
     */
    public addBills() {
        initComponents();
        displayData();
        SimpleDateFormat dFormat= new SimpleDateFormat("dd-MM-yyyy");
       Date date = new Date();
       ondate.setText(dFormat.format(date));
       
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
       LocalDateTime now = LocalDateTime.now();
       time.setText(dtf.format(now));
       
       
       
       
    }
    
    
    
    public void displayData() {
        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT treatmentType,Description,Rate,Quantity,TotalAmount FROM billings");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
        
   }
    
    
private void clearFields() {
    id.setText("");
    fn.setText("");
    
    address.setText("");
    cn.setText("");
    em.setText("");
    ut.setText("");
}
private void clearBillingFields() {
    // Clear patient details
    id.setText("");
    fn.setText("");
    address.setText("");
    cn.setText("");
    em.setText("");
    ut.setText("");

    // Clear billing details
    tt.setText("");
    amount.setText("");
    AmountPaid.setText("");
    date.setText("");
    des.setText("");
}




private void loadBillingTable() {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_bill", "root", "");
        String sql = "SELECT * FROM billings";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        // Assuming your JTable model is DefaultTableModel
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            Object[] row = {
                rs.getInt("b_id"),
                rs.getInt("p_id"),
                rs.getString("Name"),
                rs.getString("treatmentType"),
                rs.getDouble("TotalAmount"),
                rs.getDouble("AmountPaid"),
                rs.getDate("Date"),
                rs.getString("Description")
            };
            model.addRow(row);
        }

        rs.close();
        pstmt.close();
        conn.close();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading billing data: " + ex.getMessage());
    }
    
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
        jLabel19 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        ondate = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ut = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        des = new javax.swing.JTextField();
        Date = new javax.swing.JLabel();
        addBilling = new rojerusan.RSMaterialButtonCircle();
        jLabel10 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        search = new rojerusan.RSMaterialButtonCircle();
        clearFields = new rojerusan.RSMaterialButtonCircle();
        jLabel20 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        returnA = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        AmountPaid = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rate = new javax.swing.JTextField();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel17 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojeru_san.complementos.RSTableMetro();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        quan = new javax.swing.JTextField();
        med = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 0, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/Billing ani.gif"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, -1, 110));

        time.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        time.setText("TIME :");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, -1, -1));

        ondate.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        ondate.setText("DATE :");
        jPanel1.add(ondate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setText("DATE :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel4.setText("TIME :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 110, 1260, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel5.setText("ID :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel6.setText("CALCULATION :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, -1, -1));

        fn.setBackground(new java.awt.Color(204, 255, 255));
        fn.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        fn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        fn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnActionPerformed(evt);
            }
        });
        jPanel1.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 230, 40));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel7.setText("Email :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        cn.setBackground(new java.awt.Color(204, 255, 255));
        cn.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        cn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        cn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnActionPerformed(evt);
            }
        });
        jPanel1.add(cn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 230, 40));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel8.setText("City Address :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        em.setBackground(new java.awt.Color(204, 255, 255));
        em.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        em.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 230, 40));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel9.setText("Usertype :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        ut.setBackground(new java.awt.Color(204, 255, 255));
        ut.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        ut.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(ut, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 230, 40));

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel11.setText("PATIENT DETAILS :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        tt.setBackground(new java.awt.Color(204, 255, 255));
        tt.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        tt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(tt, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 240, 30));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel13.setText("Quantity :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, -1, -1));

        des.setBackground(new java.awt.Color(204, 255, 255));
        des.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        des.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.add(des, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 360, 250, 70));

        Date.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        Date.setText("Date :");
        jPanel1.add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, -1, -1));

        addBilling.setBackground(new java.awt.Color(102, 102, 255));
        addBilling.setText("ADD");
        addBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBillingActionPerformed(evt);
            }
        });
        jPanel1.add(addBilling, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 430, 220, 50));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel10.setText("Name :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        id.setBackground(new java.awt.Color(204, 255, 255));
        id.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 230, 40));

        search.setBackground(new java.awt.Color(153, 153, 153));
        search.setText("SEARCH");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 90, 50));

        clearFields.setBackground(new java.awt.Color(255, 102, 102));
        clearFields.setText("clear");
        clearFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFieldsActionPerformed(evt);
            }
        });
        jPanel1.add(clearFields, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 240, 50));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel20.setText("Description :");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, -1, -1));

        date.setBackground(new java.awt.Color(204, 255, 255));
        date.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        date.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 440, 250, 30));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel2.setText("Return Amount :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 700, -1, -1));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel12.setText("Total : ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 570, -1, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel14.setText("Status :");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 760, -1, -1));

        returnA.setBackground(new java.awt.Color(204, 255, 255));
        returnA.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        returnA.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(returnA, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 700, 180, 30));

        amount.setBackground(new java.awt.Color(204, 255, 255));
        amount.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        amount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });
        jPanel1.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 566, 180, 30));

        AmountPaid.setBackground(new java.awt.Color(204, 255, 255));
        AmountPaid.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        AmountPaid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        AmountPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmountPaidActionPerformed(evt);
            }
        });
        jPanel1.add(AmountPaid, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 630, 180, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel15.setText("Medication :");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel16.setText("Rate :");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, -1, -1));

        rate.setBackground(new java.awt.Color(204, 255, 255));
        rate.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        rate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateActionPerformed(evt);
            }
        });
        jPanel1.add(rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, 240, 30));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(51, 51, 255));
        rSMaterialButtonCircle1.setText("SAVE");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 570, 240, 60));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(153, 153, 153));
        rSMaterialButtonCircle2.setText("CLOSE");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 730, 240, 60));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 102, 102));
        rSMaterialButtonCircle3.setText("RESET");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 650, 240, 60));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel17.setText("Contact Number :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        address.setBackground(new java.awt.Color(204, 255, 255));
        address.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        address.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 230, 40));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel18.setText("Amount Paid :");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 640, -1, -1));

        status.setBackground(new java.awt.Color(204, 255, 255));
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Status", "Pending", "Paid", "Unpaid" }));
        status.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 750, 180, 40));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("Bills Overview :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 481, -1, 30));

        table.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 51)));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "b_id", "Name", "TreatmentType", "TotalAmount", "Status"
            }
        ));
        table.setColorBackgoundHead(new java.awt.Color(255, 204, 51));
        table.setColorBordeFilas(new java.awt.Color(255, 204, 51));
        table.setColorBordeHead(new java.awt.Color(255, 204, 51));
        table.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        table.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        table.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        table.setColorSelBackgound(new java.awt.Color(102, 102, 102));
        table.setIntercellSpacing(new java.awt.Dimension(2, 2));
        table.setRowHeight(34);
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 630, 310));

        jLabel21.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel21.setText("BILLING DETAILS :");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        jLabel22.setText("Treatment Type :");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, -1, -1));

        quan.setBackground(new java.awt.Color(204, 255, 255));
        quan.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        quan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(quan, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 240, 30));

        med.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        med.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add Medication :", "Paracetamol", "Ibuprofen", "Amoxicillin", "Metformin", "Amlodipine", "Atenolol", "Omeprazole", "Salbutamol", "Cetirizine", "Loratadine", "Ciprofloxacin", "Losartan", "Simvastatin", "Furosemide", "Metoprolol", "Tramadol", "Diclofenac", "Clopidogrel", "Insulin (e.g., Lantus)", "Hydrocortisone" }));
        jPanel1.add(med, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 240, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 840));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnActionPerformed
    
  

    }//GEN-LAST:event_fnActionPerformed

    private void cnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnActionPerformed
    
    }//GEN-LAST:event_cnActionPerformed

    private void addBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBillingActionPerformed
       int price = Integer.parseInt(rate.getText());
       int quantity=Integer.parseInt(quan.getText());
       int total=price*quantity;
       DefaultTableModel model = (DefaultTableModel)table.getModel();
       model.addRow(new Object []{tt.getText(),des.getText(),price,quantity,total});
       finalTotal=finalTotal+total;
       String finalTotal1=String.valueOf(finalTotal);
       amount.setText(finalTotal1);
       

    }//GEN-LAST:event_addBillingActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
       try {
        // Get p_id from input field
        int p_id = Integer.parseInt(id.getText());

        // Database connection setup
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_bill", "root", "");
        String sql = "SELECT fn, cityAddress,contactNo, email, usertype FROM users WHERE p_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, p_id);
        
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Populate the fields with user details
            fn.setText(rs.getString("fn"));
            address.setText(rs.getString("cityAddress"));
            cn.setText(rs.getString("contactNo"));
            em.setText(rs.getString("email"));
            ut.setText(rs.getString("usertype"));
        } else {
            JOptionPane.showMessageDialog(this, "User not found!");
        }

        // Close resources
        rs.close();
        stmt.close();
        conn.close();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID");
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
    }

    }//GEN-LAST:event_searchActionPerformed

    private void clearFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFieldsActionPerformed
      clearFields();

    }//GEN-LAST:event_clearFieldsActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

             }//GEN-LAST:event_formWindowActivated

    private void AmountPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmountPaidActionPerformed
      String paidAmount = AmountPaid.getText();
      int z = Integer.parseInt(paidAmount);
      finalTotal = z-finalTotal;
      String finalTotal1=String.valueOf(finalTotal);
      returnA.setText(finalTotal1);
      returnA.setEditable(false);
    }//GEN-LAST:event_AmountPaidActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
dashBoard  db = new dashBoard();
db.setVisible(true);
this.dispose();
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        setVisible(false);
        new addBills().setVisible(true);
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed

 String query = "INSERT INTO billings (p_id, Name, treatmentType, TotalAmount, AmountPaid, Date, BillingStatus, Description, rate, quantity, returnAmount, medication) " +
               "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

// Field-by-field validation
if (id.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Patient ID is required.");
    id.requestFocus(); return;
}
if (fn.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Full Name is required.");
    fn.requestFocus(); return;
}
if (tt.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Treatment Type is required.");
    tt.requestFocus(); return;
}
if (amount.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Total Amount is required.");
    amount.requestFocus(); return;
}
if (AmountPaid.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Amount Paid is required.");
    AmountPaid.requestFocus(); return;
}
if (date.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Billing Date is required.");
    date.requestFocus(); return;
}
if (status.getSelectedItem() == null) {
    JOptionPane.showMessageDialog(null, "Billing Status is required.");
    status.requestFocus(); return;
}
if (des.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Description is required.");
    des.requestFocus(); return;
}
if (rate.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Rate is required.");
    rate.requestFocus(); return;
}
if (quan.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Quantity is required.");
    quan.requestFocus(); return;
}
if (returnA.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Return Amount is required.");
    returnA.requestFocus(); return;
}
if (med.getSelectedItem() == null || med.getSelectedItem().toString().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Medication is required.");
    med.requestFocus(); return;
}
if (address.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Address is required.");
    address.requestFocus(); return;
}
if (cn.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Contact Number is required.");
    cn.requestFocus(); return;
}
if (em.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Email is required.");
    em.requestFocus(); return;
}

int patientId, quantity;
double totalAmount, amountPaid, rateValue, returnAmount;
try {
    patientId = Integer.parseInt(id.getText().trim());
    totalAmount = Double.parseDouble(amount.getText().trim());
    amountPaid = Double.parseDouble(AmountPaid.getText().trim());
    rateValue = Double.parseDouble(rate.getText().trim());
    quantity = Integer.parseInt(quan.getText().trim());
    returnAmount = Double.parseDouble(returnA.getText().trim());
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Invalid numeric input: " + e.getMessage());
    return;
}

String medication = med.getSelectedItem().toString().trim();

try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_bill", "root", "")) {

    String checkQuery = "SELECT COUNT(*) FROM billings WHERE p_id = ?";
    PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
    checkStmt.setInt(1, patientId);
    ResultSet rs = checkStmt.executeQuery();
    if (rs.next() && rs.getInt(1) > 0) {
        JOptionPane.showMessageDialog(null, "This patient already has billing records.");
        return;
    }

    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setInt(1, patientId);
    stmt.setString(2, fn.getText().trim());
    stmt.setString(3, tt.getText().trim());
    stmt.setDouble(4, totalAmount);
    stmt.setDouble(5, amountPaid);
    stmt.setString(6, date.getText().trim());
    stmt.setString(7, status.getSelectedItem().toString());
    stmt.setString(8, des.getText().trim());
    stmt.setDouble(9, rateValue);
    stmt.setInt(10, quantity);
    stmt.setDouble(11, returnAmount);
    stmt.setString(12, medication);

    int rowsInserted = stmt.executeUpdate();

    if (rowsInserted > 0) {
        String cityAddress = address.getText().trim();
        String contactNo = cn.getText().trim();
        String email = em.getText().trim();
        String patientName = fn.getText().trim();
        String billingDate = date.getText().trim();
        String path = System.getProperty("user.home") + "\\Downloads\\";
        new File(path).mkdirs();

        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + patientName + "_Bill.pdf"));
            doc.open();

            Paragraph header = new Paragraph(
                "                                           ALCAYA MARLO (HOSPITAL BILLING SYSTEM)\n" +
                "                                               \n CONTACT NUMBER: 0914410936\n\n"
            );
            doc.add(header);

            Paragraph patientDetails = new Paragraph(
                "Date & Time: " + billingDate + "\n" +
                "Patient Details:\n" +
                "Name: " + patientName + "\n" +
                "Contact No: " + contactNo + "\n" +
                "Email: " + email + "\n" +
                "City Address: " + cityAddress + "\n\n"
            );
            doc.add(patientDetails);
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
model.addRow(new Object[] {
    med.getSelectedItem().toString(),  
    tt.getText(),                      
    des.getText(),                     
    rate.getText(),                   
    quan.getText()                    
});

  PdfPTable tb1 = new PdfPTable(5); 
tb1.setWidthPercentage(100);

tb1.addCell("Medication");
tb1.addCell("Treatment Type");
tb1.addCell("Description");
tb1.addCell("Rate");
tb1.addCell("Quantity");

for (int i = 1; i < table.getRowCount(); i++) {
    System.out.println("Medication at row " + i + ": " + table.getValueAt(i, 0));

    if (table.getValueAt(i, 0) == null || table.getValueAt(i, 1) == null ||
        table.getValueAt(i, 2) == null || table.getValueAt(i, 3) == null ||
        table.getValueAt(i, 4) == null) continue;

    tb1.addCell(table.getValueAt(i, 0).toString()); 
    tb1.addCell(table.getValueAt(i, 1).toString());
    tb1.addCell(table.getValueAt(i, 2).toString());
    tb1.addCell(table.getValueAt(i, 3).toString()); 
    tb1.addCell(table.getValueAt(i, 4).toString());
}


            doc.add(tb1);

            Paragraph summary = new Paragraph(
                "\nTotal: " + totalAmount +
                "\nPaid Amount: " + amountPaid +
                "\nReturn Amount: " + returnAmount +
                "\n\nTHANK YOU FOR VISITING MY SYSTEM!!"
            );
            doc.add(summary);

            Paragraph signature = new Paragraph(
                "\n\n______________________________\n" +
                " Signature\n\n "
            );
            signature.setAlignment(Element.ALIGN_RIGHT);
            doc.add(signature);

            JOptionPane.showMessageDialog(null, "Done Bill Generated!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "PDF Generation Error: " + e.getMessage());
        } finally {
            if (doc.isOpen()) doc.close();
        }

        setVisible(false);
        new dashBoard().setVisible(true);

    } else {
        JOptionPane.showMessageDialog(null, "Failed to insert bill into the database.");
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
}


    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed

    private void rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rateActionPerformed

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
            java.util.logging.Logger.getLogger(addBills.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addBills.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addBills.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addBills.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new addBills().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AmountPaid;
    private javax.swing.JLabel Date;
    private rojerusan.RSMaterialButtonCircle addBilling;
    private javax.swing.JTextField address;
    private javax.swing.JTextField amount;
    private rojerusan.RSMaterialButtonCircle clearFields;
    private javax.swing.JTextField cn;
    private javax.swing.JTextField date;
    private javax.swing.JTextField des;
    private javax.swing.JTextField em;
    private javax.swing.JTextField fn;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> med;
    private javax.swing.JLabel ondate;
    private javax.swing.JTextField quan;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private javax.swing.JTextField rate;
    private javax.swing.JTextField returnA;
    private rojerusan.RSMaterialButtonCircle search;
    private javax.swing.JComboBox<String> status;
    private rojeru_san.complementos.RSTableMetro table;
    private javax.swing.JLabel time;
    private javax.swing.JTextField tt;
    private javax.swing.JTextField ut;
    // End of variables declaration//GEN-END:variables
}
