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
public final class FormInputBarang extends javax.swing.JFrame {

   private final DefaultTableModel model;
   
    public FormInputBarang() throws SQLException {
        initComponents();
        
        model = new DefaultTableModel();
        tblBahan.setModel(model);
        model.addColumn("Kode_Bahan");
        model.addColumn("Jenis_Bahan");
        model.addColumn("Gramatur");
        model.addColumn("Ukuran");
        model.addColumn("Harga Satuan");
        loadData();
    }
    
    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "Select * from tblinputbahan";
        ResultSet res = stat.executeQuery(sql);
        

            while (res.next()){
            Object[] obj = new Object[5];
            obj[0] = res.getString("Kode_Bahan");
            obj[1] = res.getString("Jenis_Bahan");
            obj[2] = res.getString("Gramatur");
            obj[3] = res.getString("Ukuran");
            obj[4] = res.getString("Harga_Satuan");
            
            model.addRow(obj);
        }
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void saveData(){
        if(txtKodeBahan.getText().equals("") ||txtJenis.getText().equals("") || txtGramatur.getText().equals("")|| txtUkuran.getText().equals("")|| txtHarga.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !");
        }else{
            String kode = txtKodeBahan.getText();
            String jenis = txtJenis.getText();

            String gram = txtGramatur.getText();
            String ukuran = txtUkuran.getText();
            String harga = txtHarga.getText();
            
            try{
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
            String sql = "Insert into tblinputbahan" + "(Kode_Bahan,Jenis_Bahan,Gramatur,Ukuran,Harga_Satuan)"
                    + "values ('"+ kode +"','"+ jenis +"','"+ gram +"','"+ ukuran +"','"+ harga +"')";
            PreparedStatement p = (PreparedStatement) Koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            loadData();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }finally{
            loadData();
                txtKodeBahan.setText("");
                txtJenis.setText("");
                txtGramatur.setText("");
                txtUkuran.setText("");
                txtHarga.setText("");
            }
    
    }
 }
    
    public void dataSelect(){
        int i = tblBahan.getSelectedRow();
        if (i == -1){
            return;
        }
        txtKodeBahan.setText(""+model.getValueAt(i, 0));
        txtJenis.setText(""+model.getValueAt(i, 1));
        txtGramatur.setText(""+model.getValueAt(i, 2));
        txtUkuran.setText(""+model.getValueAt(i, 3));
        txtHarga.setText(""+model.getValueAt(i, 4)); 
    }
    
     public void updateData() throws SQLException{
        int i = tblBahan.getSelectedRow();
        if (i == -1) {
            return;
        }
        String kode = (String) model.getValueAt(i, 0);
        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "UPDATE  tblinputbahan SET Jenis_Bahan =  '" + txtJenis.getText() + "',"
                    + "Gramatur =  '"+ txtGramatur.getText()+ "',"
                    + "Ukuran =  '"+ txtUkuran.getText() + "',"
                    + "Harga_Satuan =  '"+ txtHarga.getText() + "' WHERE  Kode_Bahan ='" + kode + "'";
            PreparedStatement p = c.prepareStatement(sql);
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        } finally {
            loadData();
            txtKodeBahan.setText("");
            txtJenis.setText("");
            txtGramatur.setText("");
            txtUkuran.setText("");
            txtHarga.setText("");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "digitalBI", JOptionPane.INFORMATION_MESSAGE);
            txtKodeBahan.requestFocus();
        }
    }
    
    public void deleteData() throws SQLException{
        int pesan = JOptionPane.showConfirmDialog(null, "Anda yakin menghapus data ini?","Konfirmasi",
                    JOptionPane.OK_CANCEL_OPTION);
        if (pesan == JOptionPane.OK_OPTION){
            try {
            String sql ="delete from tblinputbahan where Kode_Bahan='"+txtKodeBahan.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        txtKodeBahan.setText("");
        txtJenis.setText("");
        txtGramatur.setText("");
        txtUkuran.setText("");
        txtHarga.setText("");
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
        txtKodeBahan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtJenis = new javax.swing.JTextField();
        txtGramatur = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtUkuran = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBahan = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

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

        txtKodeBahan.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Kode Bahan");

        jLabel3.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Jenis Bahan");

        jLabel4.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Gramatur");

        jLabel6.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Ukuran");

        jLabel7.setFont(new java.awt.Font("Louis George Café", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Harga satuan");

        txtJenis.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtGramatur.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtHarga.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        txtUkuran.setFont(new java.awt.Font("Louis George Café", 0, 24)); // NOI18N

        tblBahan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBahanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBahan);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtKodeBahan, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGramatur, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
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
                            .addComponent(txtKodeBahan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtGramatur, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
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
           Logger.getLogger(FormInputBarang.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       try {
           deleteData();
       } catch (SQLException ex) {
           Logger.getLogger(FormInputBarang.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        saveData();
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblBahanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBahanMouseClicked
       dataSelect();
    }//GEN-LAST:event_tblBahanMouseClicked

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
            java.util.logging.Logger.getLogger(FormInputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormInputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormInputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormInputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                    new FormInputBarang().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormInputBarang.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBahan;
    private javax.swing.JTextField txtGramatur;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JTextField txtKodeBahan;
    private javax.swing.JTextField txtUkuran;
    // End of variables declaration//GEN-END:variables

    private void buttonGroup1(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
