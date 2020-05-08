/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectuas;

import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 *
 * @author SiBee
 */
public final class FormTransaksiOp extends javax.swing.JFrame {
    
    public long total;
    public long bayar;
    public long kembali;
    public java.sql.Statement st;
    Connection cn = Koneksi.getKoneksi();
   private final DefaultTableModel model;
    public FormTransaksiOp() throws SQLException {
        initComponents();
        AutoNumber();
        model = new DefaultTableModel();
        tblTransaksi.setModel(model);
        model.addColumn("No");
        model.addColumn("Kode_Bahan");
        model.addColumn("Harga_Satuan");
        model.addColumn("Jumlah_Cetak");
        model.addColumn("Harga_Cetak");
        loadData();
        tampilpilih();
    }
    
    public void FilterHuruf(KeyEvent a){
       if(Character.isDigit(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "Masukan Huruf Saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
    public void FilterAngka(KeyEvent a){
       if(Character.isAlphabetic(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "Masukan Angka Saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
    
    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "Select * from tbltrans";
        ResultSet res = stat.executeQuery(sql);
        

            while (res.next()){
            Object[] obj = new Object[5];
            obj[0] = res.getString("No");
            obj[1] = res.getString("Kode_Bahan");
            obj[2] = res.getString("Harga_Satuan");
            obj[3] = res.getString("Jumlah_Cetak");
            obj[4] = res.getString("Harga_Cetak");
            
            model.addRow(obj);
        }
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    private void AutoNumber() {
        try {
            Connection c = Koneksi.getKoneksi();
            java.sql.Statement s = c.createStatement();

            String sql = "SELECT * FROM tblpembelian ORDER by no_transaksi desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("no_transaksi").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }

                txtNoTransaksi.setText("F" + Nol + AN);
            } else {
                txtNoTransaksi.setText("F0001");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void tampilpilih() {
        try {
            Connection c = Koneksi.getKoneksi();
            java.sql.Statement s = c.createStatement();

            String sql = "SELECT kode_bahan FROM tblinputbahan";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                cmbKode.addItem(r.getString("kode_bahan"));
            }

            r.last();
            int jumlahdata = r.getRow();
            r.first();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    public void cetak(){
        try {
        String namafile = "src/projectuas/report2.jasper";
        HashMap hash = new HashMap();
        File file = new File(namafile);
        JasperReport jasper = (JasperReport) JRLoader.loadObject(file.getPath());
        JasperPrint print = JasperFillManager.fillReport(jasper, hash, Koneksi.getKoneksi());
        JasperViewer.viewReport(print,false);
        } catch (JRException ex) {
        JOptionPane.showMessageDialog(null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNoTransaksi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSatuan = new javax.swing.JTextField();
        txtJumlahCetak = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cmbKode = new javax.swing.JComboBox<>();
        btnhitung = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        labTotal = new javax.swing.JLabel();
        btntotal = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        btnSelesai = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBackground(new java.awt.Color(128, 191, 255));

        jLabel1.setFont(new java.awt.Font("Antipasto Pro ", 0, 72)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("digitalBI");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Louis George Café", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(128, 191, 255));
        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Louis George Café", 1, 20)); // NOI18N
        btnBack.setForeground(new java.awt.Color(128, 191, 255));
        btnBack.setText("< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(128, 191, 240));

        jLabel5.setFont(new java.awt.Font("Louis George Café", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("digitalBI (c) 2020");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(568, 568, 568)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtNoTransaksi.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("No. Transaksi");

        jLabel4.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Harga Satuan");

        jLabel7.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Jumlah Cetak");

        txtSatuan.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtJumlahCetak.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtTotal.setFont(new java.awt.Font("Louis George Café", 0, 36)); // NOI18N

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        btnAdd.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Kode Bahan");

        cmbKode.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        cmbKode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Kode -" }));
        cmbKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKodeActionPerformed(evt);
            }
        });

        btnhitung.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        btnhitung.setText("Hitung");
        btnhitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhitungActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Louis George Café", 1, 48)); // NOI18N
        jLabel3.setText("Rp.");

        labTotal.setFont(new java.awt.Font("Louis George Café", 1, 48)); // NOI18N

        btntotal.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        btntotal.setText("TOTAL");
        btntotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntotalActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        jLabel8.setText("Bayar");

        jLabel10.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        jLabel10.setText("Kembalian");

        txtBayar.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBayarKeyTyped(evt);
            }
        });

        txtKembalian.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        btnSelesai.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        btnSelesai.setText("SELESAI TRANSAKSI");
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });

        btnCetak.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        btnCetak.setText("CETAK");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(41, 41, 41)
                        .addComponent(txtKembalian))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(92, 92, 92)
                        .addComponent(txtBayar))
                    .addComponent(btntotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(labTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSelesai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCetak, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labTotal))
                .addGap(32, 32, 32)
                .addComponent(btntotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnSelesai)
                .addGap(18, 18, 18)
                .addComponent(btnCetak)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnhitung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(113, 113, 113)
                                .addComponent(txtJumlahCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGap(113, 113, 113)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSatuan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbKode, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNoTransaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(215, 215, 215))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbKode))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtJumlahCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotal)
                            .addComponent(btnhitung, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Yakin ingin keluar?", "Pelabuha App", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                 FormLoginAs fb = new FormLoginAs();
                fb.setVisible(true);
                this.setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(txtNoTransaksi.getText().equals("") || cmbKode.getSelectedItem().equals("")|| txtSatuan.getText().equals("")|| txtJumlahCetak.getText().equals("")|| txtTotal.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            String pilihbarangg = (String)cmbKode.getSelectedItem();
            String hsatuann = txtSatuan.getText();
            String tjumlahh = txtJumlahCetak.getText();
            String totall = txtTotal.getText();
           
            try {
                Connection c = Koneksi.getKoneksi();
                
                String sql = "INSERT INTO tbltrans VALUES (?, ?, ?, ?, ?)";
                
               
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, null);
                p.setString(2, pilihbarangg);
                p.setString(3, hsatuann);
                p.setString(4, tjumlahh);
                p.setString(5, totall);
                
                p.executeUpdate();
                p.close();
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                AutoNumber();
                cmbKode.setSelectedItem("");
                txtSatuan.setText("");
                txtJumlahCetak.setText("");
                txtTotal.setText("");
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
                loadData();   
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
       
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        FormMenuoP fb = new FormMenuoP();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void cmbKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKodeActionPerformed
        if (cmbKode.getSelectedItem().equals("- Pilih Kode -")){
            txtSatuan.setText("");
        }else{
        
        try {
            Connection c = Koneksi.getKoneksi();
            java.sql.Statement s = c.createStatement();

            String sql = "SELECT harga_satuan FROM tblinputbahan WHERE kode_bahan ='" + cmbKode.getSelectedItem() + "'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                txtSatuan.setText(r.getString("harga_satuan"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }                                       
    }//GEN-LAST:event_cmbKodeActionPerformed

    private void btntotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntotalActionPerformed
      try {
            Connection c = Koneksi.getKoneksi();
            java.sql.Statement s = c.createStatement();

            String sql = "SELECT SUM(`harga_cetak`) AS total FROM tbltrans";
            ResultSet r = s.executeQuery(sql);
           
            while (r.next()) {
                labTotal.setText(r.getString(""+"total"));
                
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_btntotalActionPerformed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
       if(txtBayar.getText().equals("") ||txtKembalian.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            String a = txtKembalian.getText();
            int ab = Integer.parseInt(String.valueOf(txtKembalian.getText()));
              if(ab < 0){
                JOptionPane.showMessageDialog(null, "Uang Anda Tidak Cukup", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
                txtBayar.setText("");
                txtKembalian.setText("");
              }else{
           try {
            Connection c = Koneksi.getKoneksi();
            java.sql.Statement s = c.createStatement();

            String sql = "SELECT * FROM tbltrans";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String tgl = date.toString();
                String sqla = "INSERT INTO tblpembelian VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
         
                PreparedStatement p = c.prepareStatement(sqla);
                p.setString(1, txtNoTransaksi.getText());
                p.setString(2, r.getString("Kode_Bahan"));
                p.setString(3, r.getString("Harga_Satuan"));
                p.setString(4, r.getString("Jumlah_Cetak"));
                p.setString(5, r.getString("Harga_Cetak"));
                p.setString(6, txtBayar.getText());
                p.setString(7, txtKembalian.getText());
                p.setString(8, tgl);
                
                p.executeUpdate();
                p.close();
                
                
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }finally{
         try {
            String sqla ="TRUNCATE `tbltrans";
            java.sql.Connection conn=(Connection)Koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sqla);
            pst.execute();
            JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
            loadData();
            jTextField1.setText(txtNoTransaksi.getText());
            txtBayar.setText("");
            jTextField1.setText("");
            jLabel4.setText("");
            AutoNumber();
            btnCetak.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
           }
              }
         }
    }//GEN-LAST:event_btnSelesaiActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        cetak();
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnhitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhitungActionPerformed
        if(txtNoTransaksi.getText().equals("") || cmbKode.getSelectedItem().equals("")|| txtSatuan.getText().equals("")|| txtJumlahCetak.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
        String a = txtJumlahCetak.getText();
        int aa = Integer.parseInt(a);
        
        if(aa > 9999999){
             JOptionPane.showMessageDialog(null, "Jumlah Melebihi Stok", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
             txtJumlahCetak.setText("");
        }else{
       
        if(txtJumlahCetak.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ISI JUMLAH BELI !");
        }else{
        int jumlah, harga, total;
       
        jumlah = Integer.parseInt(txtJumlahCetak.getText().toString());
        harga = Integer.parseInt(txtSatuan.getText().toString());
        total = jumlah * harga;
       
       
             txtTotal.setText(Integer.toString(total));
        
        }
        }
        }  
        
        
    }//GEN-LAST:event_btnhitungActionPerformed

    private void txtBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyTyped
       FilterAngka(evt);
    }//GEN-LAST:event_txtBayarKeyTyped

    private void txtBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyReleased
        bayar = Integer.parseInt(String.valueOf(txtBayar.getText()));
            total = Integer.parseInt(String.valueOf(labTotal.getText()));
            kembali = bayar - total;
            
            txtKembalian.setText(Long.toString(kembali));
    }//GEN-LAST:event_txtBayarKeyReleased

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
            java.util.logging.Logger.getLogger(FormTransaksiOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormTransaksiOp().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormTransaksiOp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnSelesai;
    private javax.swing.JButton btnhitung;
    private javax.swing.JButton btntotal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cmbKode;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labTotal;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtJumlahCetak;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void buttonGroup1(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
