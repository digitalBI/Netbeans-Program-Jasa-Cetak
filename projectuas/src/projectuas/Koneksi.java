/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectuas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author SiBee
 */
public class Koneksi {

      public static Connection conn;
    public static Connection getKoneksi () {
        String host = "jdbc:mysql://localhost/jasa_cetak",
               user = "root",
               pass = "";
       try{
           conn = (Connection) DriverManager.getConnection(host, user, pass);
       } catch (SQLException err) {
           JOptionPane.showMessageDialog(null, err.getMessage());
       }
       return conn;
    }

    static Connection getConnection(String jdbcmysqllocalhostjasa_cetak, String root, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}    
