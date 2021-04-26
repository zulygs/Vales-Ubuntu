/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitante;

import Empresa.Controlador_Empresa;
import Empresa.Modelo_Empresa;
import Escritorio.Escritorio;
import OtrosDriver.Conexion;
import Visitas.Visita;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static Escritorio.Escritorio.DeskTop;
import static Escritorio.Escritorio.CentrarInternal;
import OtrosDriver.Driver;
import static Visitante.Registrar_Visitante.comboEmpresa;

/**
 *
 * @author familia
 */
public class Consulta_Visitante extends javax.swing.JInternalFrame {

    /**
     * Creates new form Consulta_Visitante
     */
    public Consulta_Visitante() {
        initComponents();
        inicio();
    }
    Controlador_Visitante DV = new Controlador_Visitante();
    Controlador_Empresa em = new Controlador_Empresa();
    ArrayList<Modelo_Empresa> mod;
    Conexion cx = new Conexion();
    Connection cn;
    Driver d = new Driver();

    void inicio() {
        cn = cx.Conectar();
        limpiar();
        Tabla("");
        Monto.setText("");
        idVi = 0;
        enterprise.removeAllItems();
        Obtener_Empresa();
    }

    void limpiar() {
        NombreVisitante.setText("");
        ApellidoVisitante.setText("");
        TelefonoVisitante.setText("");
        Monto.setText("");
        buscar.setText("");
        d.OnlyNumbers(TelefonoVisitante);
        d.OnlyDecimal(Monto);

    }

    void Tabla(String buscar) {
        DefaultTableModel tab = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas < 1) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        String[] Titulos = {"ID Visitante", "Nombre", "Telefono", "Monto", "Empresa"};
        tab.setColumnIdentifiers(Titulos);
        String[] dato = new String[5];
        ArrayList<Modelo_Visitante> mod = DV.Consulta(buscar, cn);
        for (int x = 0; x < mod.size(); x++) {
            dato[0] = String.valueOf(mod.get(x).getId_Visitante());
            dato[1] = mod.get(x).getNombreVisitante() + " " + mod.get(x).getApellido();
            dato[2] = String.valueOf(mod.get(x).getTelefono());
            dato[3] = String.valueOf(mod.get(x).getMonto());
            dato[4] = String.valueOf(mod.get(x).getEmpresa());
            tab.addRow(dato);
        }
        tabla.setModel(tab);

    }

    void eliminar() {
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                DV.Eliminar_Visitante(String.valueOf(tabla.getValueAt(row, 0)), cn);
                JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Eliminado!</font></html>");

                limpiar();

            }
        }
    }

    void traer_datos(String bus) {
        ArrayList<Modelo_Visitante> modelo = DV.ConsultaVisitante(bus, cn);
        for (int x = 0; x < modelo.size(); x++) {
            NombreVisitante.setText(modelo.get(x).getNombreVisitante());
            ApellidoVisitante.setText(modelo.get(x).getApellido());
            TelefonoVisitante.setText(String.valueOf(modelo.get(x).getTelefono()));

            if (modelo.get(x).getLugarMonto().equals("E")) {
                MontoE.setSelected(true);
                Monto.setText(String.valueOf(modelo.get(x).getMontoEmpresa()));
                Monto.setEnabled(false);
            } else {
                MontoV.setSelected(true);
                Monto.setText(String.valueOf(modelo.get(x).getMonto()));
                Monto.setEnabled(true);
            }
            
        }

    }
    public static int idVi = 0;

    void editar() {
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            idVi = Integer.parseInt(tabla.getValueAt(row, 0).toString());
            traer_datos(tabla.getValueAt(row, 0).toString());
        }

    }

    void empresass() {

        mod = em.Consulta_Empresa2("", cn);
        for (int x = 0; x < mod.size(); x++) {

            enterprise.addItem(mod.get(x).getNombreEmpresa());

        }
    }

    public void Obtener_Empresa() {
        mod = em.ConsultaSaldo("", cn);
        for (int x = 0; x < mod.size(); x++) {
            enterprise.addItem(mod.get(x).getNombreEmpresa());

        }
    }
    
    String LugarMonto(){
        String va="";
    if(MontoE.isSelected()){
    va="E";
    }else{
       va="V"; 
    }
    return va;
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Eliminar = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        NombreVisitante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TelefonoVisitante = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Monto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ApellidoVisitante = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        buscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BttLimpiar1 = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        BttLimpiar = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        enterprise = new javax.swing.JComboBox();
        MontoV = new javax.swing.JRadioButton();
        MontoE = new javax.swing.JRadioButton();

        Eliminar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Trash_25px.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.setComponentPopupMenu(jPopupMenu1);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        Edit.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_25px.png"))); // NOI18N
        Edit.setText("Editar");
        Edit.setComponentPopupMenu(jPopupMenu1);
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Edit);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jTabbedPane1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        kGradientPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Del Visitante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        kGradientPanel1.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel1.setStartColor(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre: ");
        kGradientPanel1.add(jLabel1);
        jLabel1.setBounds(30, 30, 75, 21);

        NombreVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        NombreVisitante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NombreVisitante.setText("jTextField1");
        kGradientPanel1.add(NombreVisitante);
        NombreVisitante.setBounds(110, 30, 200, 30);

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Teléfono: ");
        kGradientPanel1.add(jLabel9);
        jLabel9.setBounds(30, 70, 73, 21);

        TelefonoVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        TelefonoVisitante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TelefonoVisitante.setText("jTextField4");
        kGradientPanel1.add(TelefonoVisitante);
        TelefonoVisitante.setBounds(110, 70, 161, 30);

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Monto:");
        kGradientPanel1.add(jLabel10);
        jLabel10.setBounds(340, 110, 51, 21);

        Monto.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Monto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Monto.setText("jTextField1");
        Monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MontoKeyReleased(evt);
            }
        });
        kGradientPanel1.add(Monto);
        Monto.setBounds(420, 110, 153, 30);

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Empresa: ");
        kGradientPanel1.add(jLabel8);
        jLabel8.setBounds(30, 110, 76, 21);

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Apellido: ");
        kGradientPanel1.add(jLabel2);
        jLabel2.setBounds(340, 30, 75, 21);

        ApellidoVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ApellidoVisitante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ApellidoVisitante.setText("jTextField2");
        kGradientPanel1.add(ApellidoVisitante);
        ApellidoVisitante.setBounds(420, 30, 240, 30);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro De Visitantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);

        tabla.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tabla);

        buscar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        buscar.setText("jTextField2");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText(" Nombre O Apellido: ");

        BttLimpiar1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        BttLimpiar1.setForeground(new java.awt.Color(255, 255, 255));
        BttLimpiar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Loader_35px.png"))); // NOI18N
        BttLimpiar1.setText("Cargar Tabla");
        BttLimpiar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BttLimpiar1.setContentAreaFilled(false);
        BttLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttLimpiar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BttLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(BttLimpiar1))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        kGradientPanel1.add(jPanel2);
        jPanel2.setBounds(10, 200, 710, 320);

        edit.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_45px.png"))); // NOI18N
        edit.setText("Editar");
        edit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        edit.setContentAreaFilled(false);
        edit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_55px.png"))); // NOI18N
        edit.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_45px.png"))); // NOI18N
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        kGradientPanel1.add(edit);
        edit.setBounds(170, 150, 140, 50);

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
        kGradientPanel1.add(BttLimpiar);
        BttLimpiar.setBounds(330, 150, 140, 49);

        Add.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Add.setForeground(new java.awt.Color(255, 255, 255));
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Add_35px.png"))); // NOI18N
        Add.setText("Añadir Visitante");
        Add.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Add.setContentAreaFilled(false);
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        kGradientPanel1.add(Add);
        Add.setBounds(480, 150, 190, 50);

        enterprise.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        enterprise.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        kGradientPanel1.add(enterprise);
        enterprise.setBounds(110, 110, 170, 22);

        buttonGroup2.add(MontoV);
        MontoV.setText("Monto Visitante");
        MontoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MontoVActionPerformed(evt);
            }
        });
        kGradientPanel1.add(MontoV);
        MontoV.setBounds(520, 70, 120, 21);

        buttonGroup2.add(MontoE);
        MontoE.setText("Monto Empresa");
        kGradientPanel1.add(MontoE);
        MontoE.setBounds(370, 70, 130, 23);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Consultar Visitante", new javax.swing.ImageIcon(getClass().getResource("/iconos/Contact Details_35px.png")), jPanel1); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Consulta Visitantes");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        Tabla("");
    }//GEN-LAST:event_EliminarActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_EditActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
        Tabla(buscar.getText());
    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        // TODO add your handling code here:
        Tabla(buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        if (NombreVisitante.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Nombre!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (ApellidoVisitante.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Apellido!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (TelefonoVisitante.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Telefono!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Monto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Monto!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Double.parseDouble(Monto.getText()) > em.ConsultaMonto(cn)) {

            JOptionPane.showMessageDialog(null, "Monto De Empresa Insuficiente");
            return;

        } else {
            DV.Editar_Visitante(idVi, NombreVisitante.getText(), ApellidoVisitante.getText(), Integer.parseInt(TelefonoVisitante.getText()), Double.parseDouble(Monto.getText()), Integer.parseInt(Escritorio.LblId.getText()), mod.get(enterprise.getSelectedIndex()).getId_Empresa(),LugarMonto(), cn);
            Tabla("");
            limpiar();
        }
    }//GEN-LAST:event_editActionPerformed

    private void BttLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_BttLimpiarActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:

        Registrar_Visitante NU = new Registrar_Visitante();
        DeskTop.add(NU);
        CentrarInternal(NU);
        NU.show();
    }//GEN-LAST:event_AddActionPerformed

    private void BttLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiar1ActionPerformed
        // TODO add your handling code here:
        Tabla("");
    }//GEN-LAST:event_BttLimpiar1ActionPerformed

    private void MontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MontoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_MontoKeyReleased

    private void MontoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MontoVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MontoVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JTextField ApellidoVisitante;
    private javax.swing.JButton BttLimpiar;
    private javax.swing.JButton BttLimpiar1;
    private javax.swing.JMenuItem Edit;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JTextField Monto;
    private javax.swing.JRadioButton MontoE;
    private javax.swing.JRadioButton MontoV;
    private javax.swing.JTextField NombreVisitante;
    private javax.swing.JTextField TelefonoVisitante;
    private javax.swing.JTextField buscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton edit;
    private javax.swing.JComboBox enterprise;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
