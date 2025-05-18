/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Successfull.noAccount;
import config.Session;
import config.dbConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import login.Main;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author alcay
 */
public class dashBoard extends javax.swing.JFrame {
    private javax.swing.JLabel approved;
    private javax.swing.JLabel pending;
    private javax.swing.JLabel regis;
    
    private dbConnect dbc; 
  
    /**
     * Creates new form dashBoard1
     */
    public dashBoard() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        getTotalAcc();
        getPatientCount();
        getPendingAccount();   
        getDoctorCount();
        showLineChart();
        showPieChart();
        displayData();
        displayData1();
        showHistogram();
          Timer timer = new Timer(0, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDateTime();
            }
        });
        timer.start();
        
    }
      public void displayData() {
        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT p_id,fn,cityAddress,contactNo,username,usertype,status FROM users");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }
        public void displayData1() {
        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT b_id,Name,treatmentType,TotalAmount FROM billings");
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }
    
    public void getTotalAcc() {
        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT COUNT(*) FROM users ");
            if (rs.next()) {
                int count = rs.getInt(1);
              acc.setText(String.valueOf(count));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
    }
public void getPatientCount() {
        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT COUNT(*) FROM users WHERE usertype = 'Patient'");
            if (rs.next()) {
                int count = rs.getInt(1);
                patient.setText(String.valueOf(count));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
    }
public void getPendingAccount() {
        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT COUNT(*) FROM users WHERE status = 'Pending'");
            if (rs.next()) {
                int count = rs.getInt(1);
                pen.setText(String.valueOf(count));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
    }
public void getDoctorCount() {
        try {
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT COUNT(*) FROM users WHERE usertype = 'Doctor'");
            if (rs.next()) {
                int count = rs.getInt(1);
                docc.setText(String.valueOf(count));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
    }
public void showPieChart(){
        
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      barDataset.setValue( "Weak" , new Double( 20 ) );  
      barDataset.setValue( "Okay" , new Double( 20 ) );   
      barDataset.setValue( "Excellent" , new Double( 40 ) );    
      barDataset.setValue( "Poor" , new Double( 10 ) );  
      
       JFreeChart piechart = ChartFactory.createPieChart("Patient Satisfaction",barDataset, false,true,false);
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       piePlot.setSectionPaint("Weak", new Color(255, 160, 122)); 
        piePlot.setSectionPaint("Okay", new Color(173, 216, 230));
        piePlot.setSectionPaint("Excellent", new Color(144, 238, 144)); 
        piePlot.setSectionPaint("Poor", new Color(220, 20, 60)); 

       
        piePlot.setBackgroundPaint(Color.white);
        
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panel1.removeAll();
        panel1.add(barChartPanel, BorderLayout.CENTER);
        panel1.validate();
    }
 public void showLineChart(){
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "january");
        dataset.setValue(150, "Amount", "february");
        dataset.setValue(18, "Amount", "march");
        dataset.setValue(100, "Amount", "april");
        dataset.setValue(80, "Amount", "may");
        dataset.setValue(250, "Amount", "june");
        
        JFreeChart linechart = ChartFactory.createLineChart("Patients by Month","monthly","amount", 
                dataset, PlotOrientation.VERTICAL.VERTICAL, false,true,false);
        
       
         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        panel3.removeAll();
        panel3.add(lineChartPanel, BorderLayout.CENTER);
        panel3.validate();
    }
  public void showHistogram(){
        
         double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
                            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
                            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
                            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
                            77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
                          };
 
 
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);
        
         JFreeChart chart = ChartFactory.createHistogram("Admission vs Cost Over Time","Data", "Frequency", dataset,PlotOrientation.VERTICAL, false,true,false);
            XYPlot plot= chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        
        
        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        panel4.removeAll();
        panel4.add(barpChartPanel2, BorderLayout.CENTER);
        panel4.validate();
    }
   public void setDateTime(){
        Session sess = Session.getInstance();
        Date now = new Date();
        
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String time = timeFormat.format(now);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String date = dateFormat.format(now);
        
        date_disp.setText(date);
        haha.setText(time);
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

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        haha = new javax.swing.JLabel();
        date_disp = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        time_disp = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        user = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        docc = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        patient = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        pen = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        acc = new javax.swing.JLabel();
        panel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new rojeru_san.complementos.RSTableMetro();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new rojeru_san.complementos.RSTableMetro();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(51, 51, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 20, 60));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 25)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("HOSPITAL BILLING SYSTEM");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        haha.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        haha.setForeground(new java.awt.Color(255, 255, 255));
        haha.setText("Time :");
        jPanel5.add(haha, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 40, 140, -1));

        date_disp.setFont(new java.awt.Font("Segoe UI Semilight", 1, 24)); // NOI18N
        date_disp.setForeground(new java.awt.Color(255, 255, 255));
        date_disp.setText("dd/mm/yyyy");
        jPanel5.add(date_disp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date :");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 10, -1, -1));

        time_disp.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        time_disp.setForeground(new java.awt.Color(255, 255, 255));
        time_disp.setText("Time :");
        jPanel5.add(time_disp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 40, 60, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 80));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home_24px.png"))); // NOI18N
        jLabel2.setText("Home page");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 110, 280, 60));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Library_26px_1.png"))); // NOI18N
        jLabel7.setText("HBS Dashboard ");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 170, 340, 60));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Features");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        user.setBackground(new java.awt.Color(51, 51, 51));
        user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userMouseExited(evt);
            }
        });
        user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel8.setText("Manage Users");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        user.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 150, -1));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home_24px.png"))); // NOI18N
        jLabel9.setText("Manage Users");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        user.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 340, 60));

        jPanel2.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 270, 340, 60));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Book_26px.png"))); // NOI18N
        jLabel10.setText("Manage Bills");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 130, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 340, 280, 60));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_View_Details_26px.png"))); // NOI18N
        jLabel16.setText("Reports");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 170, -1));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 410, 280, 60));

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel11MouseExited(evt);
            }
        });
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Sell_26px.png"))); // NOI18N
        jLabel12.setText("Activiy Logs");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, -1));

        jPanel19.setBackground(new java.awt.Color(51, 51, 51));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel19MouseExited(evt);
            }
        });
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_View_Details_26px.png"))); // NOI18N
        jLabel17.setText("Reports");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel19.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, -1));

        jPanel11.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 270, 60));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 270, 60));

        name.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/male_user_50px.png"))); // NOI18N
        name.setText("Welcome, Admin");
        jPanel2.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 350, -1));

        jPanel18.setBackground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Online");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Exit_26px.png"))); // NOI18N
        jLabel13.setText("Logout");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel13)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 260, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 250, 840));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel18.setText("Bills Overview :");
        jPanel16.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 190, -1));

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        docc.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        docc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Read_Online_26px.png"))); // NOI18N
        docc.setText("10");
        jPanel12.add(docc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 160, -1));

        jPanel16.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 210, 120));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel15.setText("No. of Doctor");
        jPanel16.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 190, -1));

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_People_50px.png"))); // NOI18N
        jLabel22.setText("10");
        jPanel14.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 160, -1));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 130, 230, 150));

        patient.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        patient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_People_50px.png"))); // NOI18N
        patient.setText("10");
        jPanel13.add(patient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 160, -1));

        jPanel16.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 190, 120));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel23.setText("No. of Patient ");
        jPanel16.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 190, -1));

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pen.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        pen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Sell_26px.png"))); // NOI18N
        pen.setText("10");
        jPanel15.add(pen, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 160, -1));

        jPanel16.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 190, 120));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel20.setText("No. of Pendings");
        jPanel16.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 190, -1));

        panel3.setLayout(new java.awt.BorderLayout());
        jPanel16.add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 220, 520, 260));

        panel1.setLayout(new java.awt.BorderLayout());
        jPanel16.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 520, 220));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel19.setText("Total Accounts");
        jPanel16.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, -1));

        panel2.setBackground(new java.awt.Color(204, 204, 255));
        panel2.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acc.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        acc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Conference_26px.png"))); // NOI18N
        acc.setText("10");
        panel2.add(acc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 190, -1));

        jPanel16.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, 120));

        panel4.setLayout(new java.awt.BorderLayout());
        jPanel16.add(panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 480, 520, 360));

        table.setForeground(new java.awt.Color(255, 255, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setColorBackgoundHead(new java.awt.Color(255, 204, 51));
        table.setColorBordeFilas(new java.awt.Color(255, 204, 51));
        table.setColorBordeHead(new java.awt.Color(255, 204, 51));
        table.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        table.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        table.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        table.setColorSelBackgound(new java.awt.Color(102, 102, 102));
        table.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        table.setIntercellSpacing(new java.awt.Dimension(2, 2));
        table.setRowHeight(34);
        jScrollPane2.setViewportView(table);

        jPanel16.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 830, 260));

        table1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 51)));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "b_id", "p_id", "Name", "treatmentType", "TotalAmount", "AmountPaid", "Status"
            }
        ));
        table1.setColorBackgoundHead(new java.awt.Color(255, 204, 51));
        table1.setColorBordeFilas(new java.awt.Color(255, 204, 51));
        table1.setColorBordeHead(new java.awt.Color(255, 204, 51));
        table1.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        table1.setColorFilasForeground1(new java.awt.Color(102, 102, 102));
        table1.setColorFilasForeground2(new java.awt.Color(102, 102, 102));
        table1.setColorSelBackgound(new java.awt.Color(102, 102, 102));
        table1.setIntercellSpacing(new java.awt.Dimension(2, 2));
        table1.setRowHeight(34);
        jScrollPane1.setViewportView(table1);

        jPanel16.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 830, 320));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel21.setText("Accounts Overview :");
        jPanel16.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 190, -1));

        getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 1350, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
Session sess = Session.getInstance();
       
       if(sess.getPid() == 0){
          noAccount no= new noAccount();
           no.setVisible(true);
           this.dispose();
       
       }
       name.setText(""+sess.getFn());
         
    }//GEN-LAST:event_formWindowActivated

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
       ManageUsers user = new ManageUsers();
       user.setVisible(true);
       dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
      addBills bill = new addBills  ();
       bill.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
Logs log = new Logs();
log.setVisible(true);
this.dispose();

    }//GEN-LAST:event_jLabel12MouseClicked

    private void userMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMouseEntered
            user.setBackground(new Color (97, 103, 122));
    }//GEN-LAST:event_userMouseEntered

    private void userMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMouseExited
      user.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_userMouseExited

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        jPanel8.setBackground(new Color (97, 103, 122));
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        jPanel8.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
       jPanel9.setBackground(new Color (97, 103, 122));
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
       jPanel9.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jPanel9MouseExited

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
       jPanel11.setBackground(new Color (97, 103, 122));
    }//GEN-LAST:event_jPanel11MouseEntered

    private void jPanel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseExited
         jPanel11.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jPanel11MouseExited

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
       Transaction tt = new Transaction();
       tt.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jPanel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel19MouseEntered

    private void jPanel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel19MouseExited

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        jPanel6.setBackground(new Color (97, 103, 122));
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
       jPanel6.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jPanel6MouseExited

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
int a = JOptionPane.showConfirmDialog(null,"DO you want to logout the System?","Select",JOptionPane.YES_NO_OPTION);
        if(a==0)
        {
            Main mn = new Main();
            mn.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jLabel13MouseClicked

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
            java.util.logging.Logger.getLogger(dashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acc;
    private javax.swing.JLabel date_disp;
    private javax.swing.JLabel docc;
    private javax.swing.JLabel haha;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel name;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JLabel patient;
    private javax.swing.JLabel pen;
    private rojeru_san.complementos.RSTableMetro table;
    private rojeru_san.complementos.RSTableMetro table1;
    private javax.swing.JLabel time_disp;
    private javax.swing.JPanel user;
    // End of variables declaration//GEN-END:variables
}
