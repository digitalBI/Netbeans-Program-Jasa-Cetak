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
public final class FormDaftarOp extends javax.swing.JFrame {

   private final DefaultTableModel model;
   String username, password, nama, kelamin, telp, alamat;
    public FormDaftarOp() throws SQLException {
        initComponents();
        
        model = new DefaultTableModel();
        tbluser.setModel(model);
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Nama_Lengkap");
        model.addColumn("Jenis_Kelamin");
        model.addColumn("NoTelp");
        model.addColumn("Alamat");
        loadData();
    }
    
    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "Select * from tbloperator";
        ResultSet res = stat.executeQuery(sql);
        

            while (res.next()){
            Object[] obj = new Object[6];
            obj[0] = res.getString("Username");
            obj[1] = res.getString("Password");
            obj[2] = res.getString("Nama_Lengkap");
            obj[3] = res.getString("Jenis_Kelamin");
            obj[4] = res.getString("NoTelp");
            obj[5] = res.getString("Alamat");
            
            model.addRow(obj);
        }
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void saveData(){
        if(txtUsernameOp.getText().equals("") ||txtPasswordOp.getText().equals("") || txtNamaOp.getText().equals("")|| radioLaki.getText().equals("")|| txtTelpOp.getText().equals("")|| txtAlamatOp.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !");
        }else{
            String user = txtUsernameOp.getText();
            String pass = txtPasswordOp.getText();

            String namaa = txtNamaOp.getText();
            String telpp = txtTelpOp.getText();
            String alamatt = txtAlamatOp.getText();
            String jk ="";
            if(radioLaki.isSelected()){
                jk = radioLaki.getText();
            }else{
                jk = radioPerempuan.getText();
            }
            try{
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
            String sql = "Insert into tbloperator" + "(Username,Password,Nama_Lengkap,Jenis_Kelamin,NoTelp,Alamat)"
                    + "values ('"+ user +"','"+ pass +"','"+ namaa +"','"+ jk +"','"+ telpp +"','"+ alamatt +"')";
            PreparedStatement p = (PreparedStatement) Koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            loadData();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }finally{
            loadData();
                txtUsernameOp.setText("");
                txtPasswordOp.setText("");
                txtNamaOp.setText("");
                buttonGroup1.clearSelection();
                txtTelpOp.setText("");
                txtAlamatOp.setText("");
            }
    
    }
 }
    
    public void dataSelect(){
        int i = tbluser.getSelectedRow();
        if (i == -1){
            return;
        }
        txtUsernameOp.setText(""+model.getValueAt(i, 0));
        txtPasswordOp.setText(""+model.getValueAt(i, 1));
        txtNamaOp.setText(""+model.getValueAt(i, 2));
        String jk = (String) model.getValueAt(i, 3);
        String l ="Laki-laki";
        if(jk.equals(l)){
            radioLaki.setSelected(true);
        }else{
            radioPerempuan.setSelected(true);
        }
        txtTelpOp.setText(""+model.getValueAt(i, 4));
        txtAlamatOp.setText(""+model.getValueAt(i, 5)); 
    }
    
     public void updateData() throws SQLException{
        
        int i = tbluser.getSelectedRow();
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
            String sql = "UPDATE  tbloperator SET Password =  '" + txtPasswordOp.getText() + "',"
                    + "Nama_Lengkap =  '" + txtNamaOp.getText() + "',"
                    + "Jenis_Kelamin='"+ jk +"',"
                    + "NoTelp =  '" + txtTelpOp.getText() + "',"
                    + "Alamat =  '" + txtAlamatOp.getText() + "' WHERE  Username ='" + user + "'";
            PreparedStatement p = c.prepareStatement(sql);
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        } finally {
            loadData();
            txtUsernameOp.setText("");
            txtPasswordOp.setText("");
            txtNamaOp.setText("");
            buttonGroup1.clearSelection();
            txtTelpOp.setText("");
            txtAlamatOp.setText("");
            btnAdd.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
            txtUsernameOp.requestFocus();
        }
    }
    
    public void deleteData() throws SQLException{
        int pesan = JOptionPane.showConfirmDialog(null, "Anda yakin menghapus data ini?","Konfirmasi",
                    JOptionPane.OK_CANCEL_OPTION);
        if (pesan == JOptionPane.OK_OPTION){
            try {
            String sql ="delete from tbloperator where username='"+txtUsernameOp.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        txtUsernameOp.setText("");
        txtPasswordOp.setText("");
        txtNamaOp.setText("");
        buttonGroup1.clearSelection();
        txtTelpOp.setText("");
        txtAlamatOp.setText("");
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
        txtUsernameOp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPasswordOp = new javax.swing.JTextField();
        txtNamaOp = new javax.swing.JTextField();
        txtTelpOp = new javax.swing.JTextField();
        txtAlamatOp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbluser = new javax.swing.JTable();
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

        txtUsernameOp.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nama Lengkap");

        jLabel6.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Jenis Kelamin");

        jLabel7.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("No. Telepon");

        jLabel8.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Alamat");

        txtPasswordOp.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtNamaOp.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtTelpOp.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtAlamatOp.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        tbluser.setModel(new javax.swing.table.DefaultTableModel(
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
        tbluser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbluserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbluser);

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
                        .addComponent(txtAlamatOp, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTelpOp, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUsernameOp, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPasswordOp, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaOp, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(txtUsernameOp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPasswordOp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNamaOp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radioLaki)
                                .addComponent(radioPerempuan)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTelpOp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtAlamatOp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
           Logger.getLogger(FormDaftarOp.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       try {
           deleteData();
       } catch (SQLException ex) {
           Logger.getLogger(FormDaftarOp.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        saveData();
    }//GEN-LAST:event_btnAddActionPerformed

    private void radioLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioLakiActionPerformed
       
    }//GEN-LAST:event_radioLakiActionPerformed

    private void tbluserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbluserMouseClicked
       dataSelect();
       btnAdd.setEnabled(false);
    }//GEN-LAST:event_tbluserMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        FormMenu fb = new FormMenu();
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
            java.util.logging.Logger.getLogger(FormDaftarOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDaftarOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDaftarOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDaftarOp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormDaftarOp().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormDaftarOp.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JTable tbluser;
    private javax.swing.JTextField txtAlamatOp;
    private javax.swing.JTextField txtNamaOp;
    private javax.swing.JTextField txtPasswordOp;
    private javax.swing.JTextField txtTelpOp;
    private javax.swing.JTextField txtUsernameOp;
    // End of variables declaration//GEN-END:variables

    private void buttonGroup1(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
