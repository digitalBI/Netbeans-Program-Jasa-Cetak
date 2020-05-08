/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectuas;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SiBee
 */
public final class FormResellerOp extends javax.swing.JFrame {

   private final DefaultTableModel model;
    public FormResellerOp() throws SQLException {
        initComponents();
        AutoNumber();
        model = new DefaultTableModel();
        tblreseller.setModel(model);
        model.addColumn("NoID");
        model.addColumn("Nama_Lengkap");
        model.addColumn("Jenis_Kelamin");
        model.addColumn("Alamat");
        model.addColumn("NoTelp");
        loadData();
    }
    
    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "Select * from tbldatareseller";
        ResultSet res = stat.executeQuery(sql);
        

            while (res.next()){
            Object[] obj = new Object[5];
            obj[0] = res.getString("NoID");
            obj[1] = res.getString("Nama_Lengkap");
            obj[2] = res.getString("Jenis_Kelamin");
            obj[3] = res.getString("Alamat");
            obj[4] = res.getString("NoTelp");
            
            model.addRow(obj);
        }
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void AutoNumber() {
        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
            String sql = "SELECT * FROM tbldatareseller ORDER BY NoID DESC";
            ResultSet res = stat.executeQuery(sql);
            if (res.next()) {
                String noid = res.getString("NoID").substring(1);
                String AN = "" + (Integer.parseInt(noid) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if(AN.length()==3)
                {Nol = "0";}
                else if(AN.length()==4)
                {Nol = "";}
                txtId.setText("B" + Nol + AN);//sesuaikan dengan variable namenya
            } else {
                txtId.setText("B0001");//sesuaikan dengan variable namenya
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();//penanganan masalah
        }
    }
    
    public void saveData(){
        if(txtId.getText().equals("") || txtNama.getText().equals("")|| radioLaki.getText().equals("")|| txtAlamat.getText().equals("")|| txtTelp.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !");
        }else{
            String id = txtId.getText();
            String namaa = txtNama.getText();
            String alamatt = txtAlamat.getText();
            String telpp = txtTelp.getText();
            String jk ="";
            if(radioLaki.isSelected()){
                jk = radioLaki.getText();
            }else{
                jk = radioPerempuan.getText();
            }
            try{
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
            String sql = "Insert into tbldatareseller" + "(NoID,Nama_Lengkap,Jenis_Kelamin,Alamat,NoTelp)"
                    + "values ('"+ id +"','"+ namaa +"','"+ jk +"','"+ alamatt +"','"+ telpp +"')";
            PreparedStatement p = (PreparedStatement) Koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            loadData();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }finally{
            loadData();
                txtId.setText("");
                txtNama.setText("");
                buttonGroup1.clearSelection();
                txtAlamat.setText("");
                txtTelp.setText("");
            }
    
    }
 }
    
    public void dataSelect(){
        int i = tblreseller.getSelectedRow();
        if (i == -1){
            return;
        }
        txtId.setText(""+model.getValueAt(i, 0));
        txtNama.setText(""+model.getValueAt(i, 1));
        String jk = (String) model.getValueAt(i, 2);
        String l ="Laki-laki";
        if(jk.equals(l)){
            radioLaki.setSelected(true);
        }else{
            radioPerempuan.setSelected(true);
        }
        txtAlamat.setText(""+model.getValueAt(i, 3));
        txtTelp.setText(""+model.getValueAt(i, 4)); 
    }
    
     public void updateData() throws SQLException{
        
        int i = tblreseller.getSelectedRow();
        if (i == -1) {
            return;
        }
        String user = (String) model.getValueAt(i, 0);
        try {
            Connection c = Koneksi.getKoneksi();
            String jk ="";
            if(radioLaki.isSelected()){
                jk = radioLaki.getText();
            }else{
                jk = radioPerempuan.getText();
            }
            String sql = "UPDATE  tbldatareseller SET Nama_Lengkap =  '" + txtNama.getText() + "',"
                    + "Jenis_Kelamin='"+ jk +"',"
                    + "Alamat =  '" + txtAlamat.getText() + "',"
                    + "NoTelp =  '" + txtTelp.getText() + "' WHERE  NoID ='" + user + "'";
            PreparedStatement p = c.prepareStatement(sql);
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        } finally {
            loadData();
            txtId.setText("");
            txtNama.setText("");
            buttonGroup1.clearSelection();
            txtAlamat.setText("");
            txtTelp.setText("");
            btnAdd.setEnabled(true);
            txtId.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
            txtId.requestFocus();
        }
    }
    
    public void deleteData() throws SQLException{
        int pesan = JOptionPane.showConfirmDialog(null, "Anda yakin menghapus data ini?","Konfirmasi",
                    JOptionPane.OK_CANCEL_OPTION);
        if (pesan == JOptionPane.OK_OPTION){
            try {
            String sql ="delete from tbldatareseller where NoID='"+txtId.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        txtId.setText("");
        txtNama.setText("");
        buttonGroup1.clearSelection();
        txtAlamat.setText("");
        txtTelp.setText("");
        txtId.setEnabled(true);
        txtId.requestFocus();
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
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtTelp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblreseller = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        radioLaki = new javax.swing.JRadioButton();
        radioPerempuan = new javax.swing.JRadioButton();

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
                .addContainerGap(603, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtId.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("No. ID");

        jLabel4.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nama Lengkap");

        jLabel6.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Jenis Kelamin");

        jLabel7.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Alamat");

        jLabel8.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("No. Telp");

        txtNama.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtAlamat.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtTelp.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        tblreseller.setModel(new javax.swing.table.DefaultTableModel(
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
        tblreseller.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblresellerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblreseller);

        btnAdd.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        radioLaki.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(radioLaki);
        radioLaki.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        radioLaki.setText("Laki-laki");
        radioLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioLakiActionPerformed(evt);
            }
        });

        radioPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(radioPerempuan);
        radioPerempuan.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N
        radioPerempuan.setText("Perempuan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radioLaki, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(radioPerempuan)))))
                .addGap(99, 99, 99)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radioLaki)
                                .addComponent(radioPerempuan)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate))
                .addGap(63, 63, 63)
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       try {
           updateData();
       } catch (SQLException ex) {
           Logger.getLogger(FormResellerOp.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       try {
           deleteData();
       } catch (SQLException ex) {
           Logger.getLogger(FormResellerOp.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        saveData();
    }//GEN-LAST:event_btnAddActionPerformed

    private void radioLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioLakiActionPerformed
       
    }//GEN-LAST:event_radioLakiActionPerformed

    private void tblresellerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblresellerMouseClicked
       dataSelect();
       txtId.setEnabled(false);
       btnAdd.setEnabled(false);
    }//GEN-LAST:event_tblresellerMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        FormMenuoP fb = new FormMenuoP();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(FormResellerOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormResellerOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormResellerOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormResellerOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormResellerOp().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormResellerOp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioLaki;
    private javax.swing.JRadioButton radioPerempuan;
    private javax.swing.JTable tblreseller;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTelp;
    // End of variables declaration//GEN-END:variables

    private void buttonGroup1(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
