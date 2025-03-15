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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
        showHistogram();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/menu.png"))); // NOI18N
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 60, 60));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 10, 60));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("  X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 0, 40, 40));

        jPanel10.setBackground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 0, 40, 40));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Hospital Billing System");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 80));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 0, 51));
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

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel8.setText("Manage Users");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 220, -1));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home_24px.png"))); // NOI18N
        jLabel9.setText("Manage Users");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 340, 60));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 270, 340, 60));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Book_26px.png"))); // NOI18N
        jLabel10.setText("Manage Bills");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 340, 280, 60));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_View_Details_26px.png"))); // NOI18N
        jLabel12.setText("Payment Records");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 410, 280, 60));

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImage/icons8_Exit_26px.png"))); // NOI18N
        jLabel13.setText("Logout");
        jPanel11.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 270, 60));

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 250, 840));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel18.setText("Accounts Overview :");
        jPanel16.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 190, -1));

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
        table.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        table.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        table.setRowHeight(34);
        jScrollPane2.setViewportView(table);

        jPanel16.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 830, 620));

        getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 1350, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      Session sess = Session.getInstance();
       int pid = sess.getPid();
       if(sess.getPid() == 0){       
       new noAccount().setVisible(true);
       this.setVisible(false);
       this.dispose();
       }else{
           name.setText(""+sess.getFn());
       }
    }//GEN-LAST:event_formWindowActivated

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
       ManageUsers user = new ManageUsers();
       user.setVisible(true);
       dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

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
    private javax.swing.JLabel docc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel name;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JLabel patient;
    private javax.swing.JLabel pen;
    private rojeru_san.complementos.RSTableMetro table;
    // End of variables declaration//GEN-END:variables
}
