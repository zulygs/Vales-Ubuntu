/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtrosDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Zuly Galicia
 */ 
public class Conexion {

    Connection c = null;

    public Connection Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // c = DriverManager.getConnection("jdbc:mysql://192.168.0.9/SascimVisitas", "zuly", "sascim2019db");
          c = DriverManager.getConnection("jdbc:mysql://192.168.0.9/SascimVisitaspruebas", "zuly", "sascim2019db");
           //c = DriverManager.getConnection("jdbc:mysql://192.168.0.9/SascimVisitas", "zuly", "sascim2019db");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
            e.printStackTrace();
        }
        return c;
    }

    public void Desconectar() {
        c = null;
        if (c != null) {
            c = null;
        }
    }

}
