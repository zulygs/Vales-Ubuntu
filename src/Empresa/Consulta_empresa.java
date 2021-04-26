/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empresa;

import static Escritorio.Escritorio.CentrarInternal;
import static Escritorio.Escritorio.DeskTop;
import OtrosDriver.Conexion;
import OtrosDriver.Driver;
import Visitante.Controlador_Visitante;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Zuly Galicia
 */
public class Consulta_empresa extends javax.swing.JInternalFrame {

    /**
     * Creates new form Consulta_empresa
     */
    public Consulta_empresa() {
        initComponents();
        inicio();
        cn = con.Conectar();
        idEmpresa = 0;
    }
    Conexion con = new Conexion();

    Connection cn;
    Controlador_Empresa DE = new Controlador_Empresa();
    Driver ser = new Driver();
    Controlador_Visitante DV=new Controlador_Visitante();

    void inicio() {
        cn = con.Conectar();
        Tabla_Empresa("", ser.Fecha("%Y-%m"),"", cn);
        limpiar();
    }

    void limpiar() {
        ubicacion.setText("");
        Telefono.setText("");
        Nit.setText("");
        monto.setText("");
        NombreEmpresa.setText("");
        ID.setText("");
        ID.setEnabled(true);
        ser.OnlyNumbers(Telefono);
        ser.OnlyDecimal(monto);
        buscar.setText("");
    }

    public void Tabla_Empresa(String buscar, String fecha,String bus, Connection cn) {
             DefaultTableModel tab = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if(columnas<1){
                return true;
                }else{
                return false;
                }
            }
    
    };
        String[] Titulos = {"Id Empresa", "Nombre", "Ubicacion", "Monto", "Saldo"};
        tab.setColumnIdentifiers(Titulos);
        String[] Dato = new String[5];
        ArrayList<Modelo_Empresa> mod = DE.ConsultaSaldo(bus,cn);
        for (int x = 0; x < mod.size(); x++) {
            Dato[0] = String.valueOf(mod.get(x).getId_Empresa());
            Dato[1] = mod.get(x).getNombreEmpresa();
            Dato[2] = mod.get(x).getUbicacion();
            Dato[3] = String.valueOf(mod.get(x).getMonto());
            Dato[4] = String.valueOf(FuncionSaldo(mod.get(x).getId_Empresa(), mod.get(x).getMonto()));
            tab.addRow(Dato);
        }
        tabla.setModel(tab);
    }

    double FuncionSaldo(int empresa, double monto) {

        double consultaSaldo = DE.ConsultaSaldoValeSoloValorVales(String.valueOf(empresa), ser.Fecha("%Y-%m"), cn);
        return consultaSaldo == 0 ? monto : monto - consultaSaldo; //el signo ? es para ver si se cumplio si es 0 = a monto de lo contrario : monto -consultasaldo
    }

    void eliminar() {
        int row = tabla.getSelectedRow();

        if (row >= 1) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                DE.Eliminar_Empresa(String.valueOf(tabla.getValueAt(row, 0)), cn);
            }
        }
    }

    int idEmpresa = 0;

    void Traer_Datos(String buscar) {
        ArrayList<Modelo_Empresa> mod = DE.Consulta_Empresa2(buscar, cn);
        if (mod.size() > 0) {
            ID.setEnabled(false);
            ID.setText(String.valueOf(mod.get(0).getId_Empresa()));
            NombreEmpresa.setText(mod.get(0).getNombreEmpresa());
            ubicacion.setText(mod.get(0).getUbicacion());
            Nit.setText(mod.get(0).getPersona());
            Telefono.setText(String.valueOf(mod.get(0).getVales()));
            monto.setText(String.valueOf(mod.get(0).getMonto()));
            if (mod.get(0).getId_Empresa() == 1) {
                NombreEmpresa.setEditable(false);
                ubicacion.setEditable(false);
                Nit.setEditable(false);
                Telefono.setEditable(false);
            }else{
             NombreEmpresa.setEditable(true);
                ubicacion.setEditable(true);
                Nit.setEditable(true);
                Telefono.setEditable(true);
            }
        }
    }

    void editar() {
        int row = tabla.getSelectedRow();
        if (row >= 0) {

            Traer_Datos(String.valueOf(tabla.getValueAt(row, 0).toString()));

        }

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
        String[] Titulos = {"Id Empresa", "Nombre", "Ubicacion", "Monto", "Saldo"};
        modelo.setColumnIdentifiers(Titulos);
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
        Editar = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        BttLimpiar1 = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NombreEmpresa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ubicacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Nit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Telefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        edit = new javax.swing.JButton();
        BttLimpiar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        Add = new javax.swing.JButton();

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

        Editar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_25px.png"))); // NOI18N
        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Editar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Consulta Empresas");

        jTabbedPane1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N

        kGradientPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos De Empresas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        kGradientPanel1.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel1.setStartColor(new java.awt.Color(0, 102, 204));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de Empresas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
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
        tabla.setGridColor(new java.awt.Color(0, 102, 204));
        tabla.setSelectionBackground(new java.awt.Color(51, 153, 255));
        jScrollPane2.setViewportView(tabla);

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

        buscar.setText("jTextField1");
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nombre Empresa:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BttLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BttLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        kGradientPanel1.add(jPanel2);
        jPanel2.setBounds(20, 210, 710, 320);

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID:");
        kGradientPanel1.add(jLabel4);
        jLabel4.setBounds(30, 20, 40, 21);

        NombreEmpresa.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        NombreEmpresa.setText("jTextField1");
        kGradientPanel1.add(NombreEmpresa);
        NombreEmpresa.setBounds(170, 70, 263, 30);

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dirección:");
        kGradientPanel1.add(jLabel2);
        jLabel2.setBounds(30, 110, 90, 21);

        ubicacion.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ubicacion.setText("jTextField1");
        kGradientPanel1.add(ubicacion);
        ubicacion.setBounds(120, 110, 140, 30);

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nit:");
        kGradientPanel1.add(jLabel3);
        jLabel3.setBounds(290, 110, 75, 21);

        Nit.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Nit.setText("jTextField2");
        kGradientPanel1.add(Nit);
        Nit.setBounds(370, 110, 140, 30);

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Teléfono:");
        kGradientPanel1.add(jLabel9);
        jLabel9.setBounds(30, 150, 68, 21);

        Telefono.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Telefono.setText("jTextField4");
        kGradientPanel1.add(Telefono);
        Telefono.setBounds(120, 150, 128, 30);

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Monto:");
        kGradientPanel1.add(jLabel8);
        jLabel8.setBounds(290, 150, 51, 21);

        monto.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        monto.setText("jTextField1");
        kGradientPanel1.add(monto);
        monto.setBounds(370, 150, 150, 30);

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
        edit.setBounds(560, 40, 140, 50);

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
        BttLimpiar.setBounds(560, 110, 140, 49);

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre Empresa:");
        kGradientPanel1.add(jLabel6);
        jLabel6.setBounds(30, 70, 139, 21);

        ID.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ID.setText("jTextField1");
        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });
        kGradientPanel1.add(ID);
        ID.setBounds(80, 20, 110, 30);

        Add.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Add.setForeground(new java.awt.Color(255, 255, 255));
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Add_35px.png"))); // NOI18N
        Add.setText("Añadir Empresa");
        Add.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Add.setContentAreaFilled(false);
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        kGradientPanel1.add(Add);
        Add.setBounds(250, 20, 190, 39);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Consultar Empresa", new javax.swing.ImageIcon(getClass().getResource("/iconos/New Company_35px.png")), jPanel1); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        Tabla_Empresa("", ser.Fecha("%Y-%m"),"", cn);
    }//GEN-LAST:event_EliminarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_EditarActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
           if (NombreEmpresa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Nombre!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ubicacion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Ubicacion!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (Telefono.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Vales!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }if (monto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Vales!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }else{
        DE.Editar_Empresa(Integer.parseInt(ID.getText()), NombreEmpresa.getText(), ubicacion.getText(), Nit.getText(), Integer.parseInt(Telefono.getText()), Double.parseDouble(monto.getText()), cn);
        DV.Editar_Monto(Integer.parseInt(ID.getText()),Double.parseDouble(monto.getText()), cn);
        Tabla_Empresa("", ser.Fecha("%Y-%m"),"", cn);
        limpiar();}
    }//GEN-LAST:event_editActionPerformed

    private void BttLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_BttLimpiarActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        Empresa NU = new Empresa();
        DeskTop.add(NU);
        CentrarInternal(NU);
        NU.show();
    }//GEN-LAST:event_AddActionPerformed

    private void BttLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiar1ActionPerformed
        // TODO add your handling code here:
        reiniciarJTable(tabla);
        Tabla_Empresa("", ser.Fecha("%Y-%m"),"", cn);
    }//GEN-LAST:event_BttLimpiar1ActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        // TODO add your handling code here:
        Tabla_Empresa(ID.getText(), ser.Fecha("%Y-%m"),"", cn);
    }//GEN-LAST:event_IDActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        // TODO add your handling code here:
            Tabla_Empresa("", ser.Fecha("%Y-%m"),buscar.getText(), cn);
    }//GEN-LAST:event_buscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton BttLimpiar;
    private javax.swing.JButton BttLimpiar1;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField Nit;
    private javax.swing.JTextField NombreEmpresa;
    private javax.swing.JTextField Telefono;
    private javax.swing.JTextField buscar;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JTextField monto;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField ubicacion;
    // End of variables declaration//GEN-END:variables
}
