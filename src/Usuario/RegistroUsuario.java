/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Escritorio.Escritorio;
import static Escritorio.Escritorio.CentrarInternal;
import static Escritorio.Escritorio.DeskTop;
import OtrosDriver.Conexion;
import OtrosDriver.Driver;
import static Usuario.Consulta_Usuario.buscar;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zuly Galicia
 */
public class RegistroUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form RegistroUsuario
     */
    public RegistroUsuario() {
        initComponents();
        Inicio();
        idusuario = 0;
    }
    Conexion con = new Conexion();

    Connection cn;
    Driver_Usuario DU = new Driver_Usuario();
    Driver d = new Driver();
    Consulta_Usuario CU = new Consulta_Usuario();
    int idusuario;
    Driver driver = new Driver();

    void Inicio() {
        cn = con.Conectar();
        Limpiar();
        driver.OnlyNumbers(TelefonoUser);

    }

    void Limpiar() {
        NombreUser.setText("");
        ApellidoUser.setText("");
        CorreoUser.setText("");
        PasswordUser.setText("");
        TelefonoUser.setText("");
        ComboPuesto.removeAllItems();
        ComboRol.removeAllItems();
        
        if (DU.Comparar_Existente(cn) == true) {
            ComboRol.addItem("Usuario");
        } else {
            ComboRol.addItem("Administrador");
            ComboRol.addItem("Usuario");
        }

        ComboPuesto.addItem("Gerente");
        ComboPuesto.addItem("Recepcion");

    }

    void Guardar_Editar(String Nombre, String Apellido, String Correo, String Password, String Telefono, String Rol, String Puesto) {

        int telefono = Integer.parseInt(Telefono);
        if (String.valueOf(Nombre).equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>??Introduzca Nombre!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Apellido.equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>??Introduzca Apellido!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Correo.equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>??Introduzca Correo!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Password.equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>??Introduzca Contrase??a!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DU.GuardarUsuario(Nombre, Apellido, Correo, Password, telefono, Rol, Puesto, cn);
        Limpiar();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NombreUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ApellidoUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CorreoUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        PasswordUser = new javax.swing.JPasswordField();
        ComboRol = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ComboPuesto = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        TelefonoUser = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        BttLimpiar = new javax.swing.JButton();
        BttGuardar = new javax.swing.JButton();
        Consultar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuario");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        kGradientPanel1.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel1.setStartColor(new java.awt.Color(0, 102, 204));

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("A??adir Usuario");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kGradientPanel1.add(jLabel3);
        jLabel3.setBounds(10, 20, 360, 41);
        kGradientPanel1.add(jSeparator1);
        jSeparator1.setBounds(10, 60, 570, 10);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre: ");

        NombreUser.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        NombreUser.setText("jTextField1");

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Apellido: ");

        ApellidoUser.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ApellidoUser.setText("jTextField2");

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contrase??a: ");

        CorreoUser.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        CorreoUser.setText("jTextField3");

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Correo: ");

        PasswordUser.setText("jPasswordField1");

        ComboRol.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ComboRol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Rol: ");

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Puesto: ");

        ComboPuesto.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ComboPuesto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tel??fono: ");

        TelefonoUser.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        TelefonoUser.setText("jTextField4");

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Botones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

        BttLimpiar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        BttLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        BttLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/broom_45px.png"))); // NOI18N
        BttLimpiar.setText("Limpiar");
        BttLimpiar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BttLimpiar.setContentAreaFilled(false);
        BttLimpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Broom_555px.png"))); // NOI18N
        BttLimpiar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/broom_45px.png"))); // NOI18N
        BttLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttLimpiarActionPerformed(evt);
            }
        });

        BttGuardar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        BttGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BttGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Save_45px.png"))); // NOI18N
        BttGuardar.setText("Guardar");
        BttGuardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BttGuardar.setContentAreaFilled(false);
        BttGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Save_55px.png"))); // NOI18N
        BttGuardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Save_45px.png"))); // NOI18N
        BttGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BttGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BttLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(BttGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(BttLimpiar)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PasswordUser, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NombreUser, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ApellidoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CorreoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TelefonoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboRol, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(NombreUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ApellidoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CorreoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(PasswordUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TelefonoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(ComboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(35, 35, 35))
        );

        kGradientPanel1.add(jPanel1);
        jPanel1.setBounds(10, 80, 570, 350);

        Consultar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Consultar.setForeground(new java.awt.Color(255, 255, 255));
        Consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Query_35px.png"))); // NOI18N
        Consultar.setText("Consultar Registros");
        Consultar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Consultar.setContentAreaFilled(false);
        Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarActionPerformed(evt);
            }
        });
        kGradientPanel1.add(Consultar);
        Consultar.setBounds(380, 10, 190, 39);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Nuevo Usuario", new javax.swing.ImageIcon(getClass().getResource("/IMG/Add User Male_35px.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Nuevo Usurio");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:

    }//GEN-LAST:event_formInternalFrameClosed

    private void BttGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttGuardarActionPerformed
        // TODO add your handling code here:
        Guardar_Editar(NombreUser.getText(), ApellidoUser.getText(), CorreoUser.getText(), PasswordUser.getText(), TelefonoUser.getText(), String.valueOf(ComboRol.getSelectedItem()), String.valueOf(ComboPuesto.getSelectedItem()));

    }//GEN-LAST:event_BttGuardarActionPerformed

    private void BttLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiarActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_BttLimpiarActionPerformed

    private void ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Consulta_Usuario NU = new Consulta_Usuario();
        DeskTop.add(NU);
        CentrarInternal(NU);
        NU.show();
    }//GEN-LAST:event_ConsultarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField ApellidoUser;
    private javax.swing.JButton BttGuardar;
    private javax.swing.JButton BttLimpiar;
    public javax.swing.JComboBox ComboPuesto;
    public javax.swing.JComboBox ComboRol;
    private javax.swing.JButton Consultar;
    public javax.swing.JTextField CorreoUser;
    public javax.swing.JTextField NombreUser;
    public javax.swing.JPasswordField PasswordUser;
    public javax.swing.JTextField TelefonoUser;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
