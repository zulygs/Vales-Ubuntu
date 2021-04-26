/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Escritorio.Driver_Login;
import static Escritorio.Escritorio.CentrarInternal;
import static Escritorio.Escritorio.DeskTop;
import Escritorio.Modelo_Login;
import OtrosDriver.Conexion;
import OtrosDriver.Driver;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author familia
 */
public class Consulta_Usuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form Consuk
     */
    public Consulta_Usuario() {
        initComponents();
        inicio();
        buscar.setText("");
    }

    Conexion con = new Conexion();
    Connection cn;
    Driver_Usuario DU = new Driver_Usuario();
    Driver_Login DL = new Driver_Login();
    Driver d = new Driver();

    void inicio() {
        cn = con.Conectar();
        Tabla_Usuarios("", cn);
        d.OnlyNumbers(TelefonoUser);
        Limpiar();

    }

    void Limpiar() {
        NombreUser.setText("");
        ApellidoUser.setText("");
        CorreoUser.setText("");
        TelefonoUser.setText("");
        rol.setText("");
        puesto.setText("");
        idusuario = 0;
        ID.setText("");
        pass.setText("");
        pass.setVisible(false);
        NuevaPass.setText("");
        NuevaPass.setVisible(false);
        incorrecta.setVisible(false);
        labelAnterior.setVisible(false);
        labelNueva.setVisible(false);
        BttLimpiar.setVisible(false);
        edit.setVisible(false);
    }

    void Tabla_Usuarios(String buscar, Connection cn) {
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
        String[] Titulos = {"Id Usuario", "Nombre", "Apellido", "Telefono"};
        tab.setColumnIdentifiers(Titulos);
        String[] Dato = new String[7];
        ArrayList<Modelo_Usuario> mod = DU.Consulta_Usuario(buscar, cn);
        for (int x = 0; x < mod.size(); x++) {
            Dato[0] = String.valueOf(mod.get(x).getId_Usuario());
            Dato[1] = mod.get(x).getNombre();
            Dato[2] = mod.get(x).getApellido();
            Dato[3] = String.valueOf(mod.get(x).getTelefono());
            Dato[4] = String.valueOf(mod.get(x).getRol());
            tab.addRow(Dato);
        }

        tabla.setModel(tab);

    }

    void eliminar() {
        int row = tabla.getSelectedRow();
        if (row >= 1) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                DU.Eliminar_Usuario(String.valueOf(tabla.getValueAt(row, 0)), cn);
            }
        }
    }

    void traer_datos(String buscar) {
        ArrayList<Modelo_Usuario> ausuario = DU.Consulta_Usuario(buscar, cn);
        if (ausuario.size() > 0) {
            ID.setEnabled(false);
            if (ausuario.get(0).getId_Usuario() == 1) {
                NombreUser.setEditable(false);
                ApellidoUser.setEditable(false);

            }
            ID.setText(String.valueOf(ausuario.get(0).getId_Usuario()));
            NombreUser.setText(String.valueOf(ausuario.get(0).getNombre()));
            ApellidoUser.setText(String.valueOf(ausuario.get(0).getApellido()));
            CorreoUser.setText(String.valueOf(ausuario.get(0).getCorreo()));
            TelefonoUser.setText(String.valueOf(ausuario.get(0).getTelefono()));
            rol.setText(ausuario.get(0).getRol());
            puesto.setText(ausuario.get(0).getPuesto());

            puesto.setEditable(false);
            rol.setEditable(false);
        } else {
            JOptionPane.showMessageDialog(null, "usuario no encontrado");
        }

    }

    int idusuario = 0;

    void Editar() {
        
        ArrayList<Modelo_Usuario> mod = new ArrayList<>();
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            idusuario = Integer.parseInt(tabla.getValueAt(row, 0).toString());
            traer_datos(tabla.getValueAt(row, 0).toString());

        }
    }

    public void Obtener_Pass(String id, Connection cn) {
             ArrayList<Modelo_Usuario> mod =DL.Comparacionpass(id, cn);
        int row = tabla.getSelectedRow();
        if (row >= 0) {
             if (mod.get(0).getPassword().equals(pass.getText())) {
                NuevaPass.setVisible(true);
                incorrecta.setVisible(false);
                System.out.println(pass.getText());
            } else {
                incorrecta.setVisible(true);
                NuevaPass.setVisible(false);
                labelNueva.setVisible(false);
            }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        Eliminar = new javax.swing.JMenuItem();
        Editar = new javax.swing.JMenuItem();
        CambiarPass = new javax.swing.JMenuItem();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        BttLimpiar1 = new javax.swing.JButton();
        labelNueva = new javax.swing.JLabel();
        NombreUser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ApellidoUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CorreoUser = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TelefonoUser = new javax.swing.JTextField();
        rol = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        puesto = new javax.swing.JTextField();
        edit = new javax.swing.JButton();
        BttLimpiar = new javax.swing.JButton();
        ID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Add = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        incorrecta = new javax.swing.JLabel();
        NuevaPass = new javax.swing.JPasswordField();
        labelAnterior = new javax.swing.JLabel();
        BttLimpiar2 = new javax.swing.JButton();

        Eliminar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Trash_25px.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        Editar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_25px.png"))); // NOI18N
        Editar.setText("Editar Usuario");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Editar);

        CambiarPass.setText("Cambiar Contraseña");
        CambiarPass.setToolTipText("");
        CambiarPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarPassActionPerformed(evt);
            }
        });
        jPopupMenu1.add(CambiarPass);
        CambiarPass.getAccessibleContext().setAccessibleName("Cambiar Contraseña");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Consulta Usuarios");

        jTabbedPane2.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        kGradientPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos De Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        kGradientPanel1.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel1.setStartColor(new java.awt.Color(0, 102, 204));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros de Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID/Nombre Usuario: ");

        buscar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        buscar.setText("jTextField1");
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 182, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BttLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BttLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        kGradientPanel1.add(jPanel3);
        jPanel3.setBounds(20, 260, 693, 280);

        labelNueva.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        labelNueva.setForeground(new java.awt.Color(255, 255, 255));
        labelNueva.setText("Contraseña Nueva :");
        kGradientPanel1.add(labelNueva);
        labelNueva.setBounds(280, 220, 160, 21);

        NombreUser.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        NombreUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NombreUser.setText("jTextField1");
        NombreUser.setBorder(null);
        NombreUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreUserActionPerformed(evt);
            }
        });
        kGradientPanel1.add(NombreUser);
        NombreUser.setBounds(290, 30, 130, 30);

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido: ");
        kGradientPanel1.add(jLabel3);
        jLabel3.setBounds(30, 80, 72, 21);

        ApellidoUser.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ApellidoUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ApellidoUser.setText("jTextField2");
        kGradientPanel1.add(ApellidoUser);
        ApellidoUser.setBounds(110, 80, 130, 30);

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Correo: ");
        kGradientPanel1.add(jLabel5);
        jLabel5.setBounds(30, 120, 60, 21);

        CorreoUser.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        CorreoUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CorreoUser.setText("jTextField3");
        kGradientPanel1.add(CorreoUser);
        CorreoUser.setBounds(100, 120, 140, 30);

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Teléfono: ");
        kGradientPanel1.add(jLabel8);
        jLabel8.setBounds(280, 90, 90, 21);

        TelefonoUser.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        TelefonoUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TelefonoUser.setText("jTextField4");
        kGradientPanel1.add(TelefonoUser);
        TelefonoUser.setBounds(370, 90, 150, 30);

        rol.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        rol.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rol.setText("jTextField1");
        kGradientPanel1.add(rol);
        rol.setBounds(90, 170, 160, 30);

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Rol: ");
        kGradientPanel1.add(jLabel6);
        jLabel6.setBounds(30, 170, 34, 21);

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Puesto: ");
        kGradientPanel1.add(jLabel7);
        jLabel7.setBounds(30, 210, 60, 21);

        puesto.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        puesto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        puesto.setText("jTextField1");
        kGradientPanel1.add(puesto);
        puesto.setBounds(90, 210, 160, 30);

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
        edit.setBounds(580, 70, 140, 50);

        BttLimpiar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        BttLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        BttLimpiar.setText("Cambiar Contraseña");
        BttLimpiar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BttLimpiar.setContentAreaFilled(false);
        BttLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttLimpiarActionPerformed(evt);
            }
        });
        kGradientPanel1.add(BttLimpiar);
        BttLimpiar.setBounds(580, 190, 140, 21);

        ID.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ID.setText("jTextField1");
        kGradientPanel1.add(ID);
        ID.setBounds(80, 30, 110, 30);

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID:");
        kGradientPanel1.add(jLabel9);
        jLabel9.setBounds(30, 30, 40, 21);

        Add.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Add.setForeground(new java.awt.Color(255, 255, 255));
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Add_35px.png"))); // NOI18N
        Add.setText("Añadir Usuario");
        Add.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Add.setContentAreaFilled(false);
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        kGradientPanel1.add(Add);
        Add.setBounds(530, 20, 190, 39);

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nombre: ");
        kGradientPanel1.add(jLabel10);
        jLabel10.setBounds(210, 30, 73, 21);

        pass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pass.setText("jPasswordField1");
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passKeyReleased(evt);
            }
        });
        kGradientPanel1.add(pass);
        pass.setBounds(440, 140, 120, 30);

        incorrecta.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        incorrecta.setForeground(new java.awt.Color(204, 0, 0));
        incorrecta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incorrecta.setText("Contraseña Incorrecta");
        incorrecta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kGradientPanel1.add(incorrecta);
        incorrecta.setBounds(270, 180, 290, 21);

        NuevaPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NuevaPass.setText("jPasswordField1");
        kGradientPanel1.add(NuevaPass);
        NuevaPass.setBounds(440, 220, 120, 30);

        labelAnterior.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        labelAnterior.setForeground(new java.awt.Color(255, 255, 255));
        labelAnterior.setText("Contraseña Anterior :");
        kGradientPanel1.add(labelAnterior);
        labelAnterior.setBounds(270, 140, 160, 21);

        BttLimpiar2.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        BttLimpiar2.setForeground(new java.awt.Color(255, 255, 255));
        BttLimpiar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/broom_45px.png"))); // NOI18N
        BttLimpiar2.setText("Limpiar");
        BttLimpiar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BttLimpiar2.setContentAreaFilled(false);
        BttLimpiar2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Broom_555px.png"))); // NOI18N
        BttLimpiar2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/broom_45px.png"))); // NOI18N
        BttLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttLimpiar2ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(BttLimpiar2);
        BttLimpiar2.setBounds(580, 130, 140, 49);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Consultar Usuarios", new javax.swing.ImageIcon(getClass().getResource("/IMG/Find User Male_35px.png")), jPanel1); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
           edit.setVisible(true);
        NuevaPass.setVisible(false);
        incorrecta.setVisible(false);
        labelAnterior.setVisible(false);
        labelNueva.setVisible(false);
        BttLimpiar.setVisible(false);
        Editar();
        Tabla_Usuarios("", cn);
    }//GEN-LAST:event_EditarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        Tabla_Usuarios("", cn);
    }//GEN-LAST:event_EliminarActionPerformed

    private void NombreUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreUserActionPerformed
        // TODO add your handling code here:
        traer_datos(NombreUser.getText());
    }//GEN-LAST:event_NombreUserActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        if (NombreUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Nombre!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ApellidoUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Apellido!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (CorreoUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Correo!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            DU.UpdateUser(Integer.parseInt(ID.getText()), NombreUser.getText(), ApellidoUser.getText(), CorreoUser.getText(), Integer.parseInt(TelefonoUser.getText()), rol.getText(), puesto.getText(), cn);
            Tabla_Usuarios("", cn);
            Limpiar();
        }
    }//GEN-LAST:event_editActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        // TODO add your handling code here:
        Tabla_Usuarios(buscar.getText(), cn);
    }//GEN-LAST:event_buscarKeyReleased

    private void BttLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiarActionPerformed
        // TODO add your handling code here:
      DU.UpdatePass(ID.getText(), NuevaPass.getText(), cn);
            
        Limpiar();
    }//GEN-LAST:event_BttLimpiarActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        RegistroUsuario NU = new RegistroUsuario();
        DeskTop.add(NU);
        CentrarInternal(NU);
        NU.show();
    }//GEN-LAST:event_AddActionPerformed

    private void BttLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiar1ActionPerformed
        // TODO add your handling code here:
        buscar.setText("");
        Tabla_Usuarios("", cn);
    }//GEN-LAST:event_BttLimpiar1ActionPerformed

    private void CambiarPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarPassActionPerformed
        // TODO add your handling code here:
        labelAnterior.setVisible(true);
        labelNueva.setVisible(false);
             BttLimpiar.setVisible(true);
       
        pass.setVisible(true);
        Editar();
    }//GEN-LAST:event_CambiarPassActionPerformed

    private void passKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_passKeyReleased

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
     labelNueva.setVisible(true);

        int row = tabla.getSelectedRow();
        if (row >= 0) {
            System.out.println(pass.getText());
            Obtener_Pass(tabla.getValueAt(row, 0).toString(), cn);
        }
    }//GEN-LAST:event_passActionPerformed

    private void BttLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BttLimpiar2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    public javax.swing.JTextField ApellidoUser;
    private javax.swing.JButton BttLimpiar;
    private javax.swing.JButton BttLimpiar1;
    private javax.swing.JButton BttLimpiar2;
    private javax.swing.JMenuItem CambiarPass;
    public javax.swing.JTextField CorreoUser;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JTextField ID;
    public javax.swing.JTextField NombreUser;
    private javax.swing.JPasswordField NuevaPass;
    public javax.swing.JTextField TelefonoUser;
    public static javax.swing.JTextField buscar;
    private javax.swing.JButton edit;
    private javax.swing.JLabel incorrecta;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel labelAnterior;
    private javax.swing.JLabel labelNueva;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField puesto;
    private javax.swing.JTextField rol;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
