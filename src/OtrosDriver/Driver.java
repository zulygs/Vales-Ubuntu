/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtrosDriver;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Sistemas
 */
public class Driver {

    public void OnlyLetters(JTextField TXT) {
        TXT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void space(JTextField TXT) {
        TXT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char car = e.getKeyChar();
                if (Character.isLetter(car) || Character.isDigit(car)) {

                } else {
                    e.consume();
                  Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }

    public void OnlyNumbers(JTextField TXT) {
        TXT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int c = e.getKeyChar();
                if ((c < 48) || (c > 57)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void OnlyDecimal(JTextField TXT) {
        TXT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int c = e.getKeyChar();
                if ((c > 45) && (c < 58)) {
                    if (c == 46) {
                        String dato = TXT.getText();
                        int len = dato.length();
                        for (int x = 0; x < len; x++) {
                            if (dato.contains(".")) {
                                e.setKeyChar((char) KeyEvent.VK_CLEAR);
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }
                    } else if (c == 47) {
                        e.setKeyChar((char) KeyEvent.VK_CLEAR);
                    }
                } else {
                    e.setKeyChar((char) KeyEvent.VK_CLEAR);
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    /**
     * *******************************************************************************************************
     */
    public String Fecha(String formato) {
        String fecha = "";
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.Conectar();
            String Consulta = "SELECT DATE_FORMAT(now(),'" + formato + "') 'Fecha'";
            PreparedStatement pr = cn.prepareStatement(Consulta);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                fecha = rs.getString("Fecha");
            }
            rs.close();
            pr.close();
            cn.close();
            cc.Desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fecha;
    }

    public static void reiniciarJTable(javax.swing.JTable Tabla) {
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        TableColumnModel modCol = Tabla.getColumnModel();
        while (modCol.getColumnCount() > 0) {
            modCol.removeColumn(modCol.getColumn(0));
        }

    }
    
    

}
