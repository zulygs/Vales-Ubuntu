/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitas;

import static Escritorio.Escritorio.CentrarInternal;
import static Escritorio.Escritorio.DeskTop;
import OtrosDriver.Conexion;
import Visitante.Controlador_Visitante;
import Visitante.Modelo_Visitante;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author familia
 */
public class Historial_Visitas extends javax.swing.JInternalFrame {

    /**
     * Creates new form Historial_Visitas
     */
    public Historial_Visitas() {
        initComponents();
        inicio();
        def();
    }
    Controlador_Visita CV = new Controlador_Visita();
    Controlador_Visitante Cv = new Controlador_Visitante();
    Conexion cx = new Conexion();
    Connection cn;

    void inicio() {
        cn = cx.Conectar();
        LoadTable(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH));
        limpiar();

    }

    void def() {
        DefaultTableModel M = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas < 1) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        String[] titulos = {"Id Visita", "Fecha", "Solicito A", "Motivo", "Visitante", "Estado"};
        M.setColumnIdentifiers(titulos);
        tabla.setModel(M);

    }

    void limpiar() {
        solicita.setText("");
        motivo.setText("");
        idVisitante.setText("");
        nombreVisitante.setText("");
        observaciones.setText("");
        id.setText("");
        hora.setText("");
        Estado_Visita.removeAllItems();
        Estado_Visita.addItem("En Proceso");
        Estado_Visita.addItem("Finalizada");
        id.setEnabled(true);
        solicita.setEnabled(true);
        hora.setEnabled(true);
        idVisitante.setEnabled(true);
        nombreVisitante.setEnabled(true);
        ((JTextField) this.fecha1.getDateEditor()).setEditable(false);
        ((JTextField) this.fecha2.getDateEditor()).setEditable(false);

    }

    void LoadTable(Date buscar, Date buscar2) {

        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        String f = form.format(buscar);
        String g = form.format(buscar2);
        DefaultTableModel M = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas < 1) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        String[] titulos = {"Codigo", "Fecha", "Solicito A", "Motivo", "Visitante", "Visita"};
        M.setColumnIdentifiers(titulos);
        String[] data = new String[6];
        ArrayList<Modelo_Visita> array = new ArrayList<>();
        if (Hoy.isSelected()) {
            array = CV.ConsultaHoy(cn);
        } else if (fecha.isSelected()) {
            array = CV.ConsultaFecha(f, cn);
        } else if (rangos.isSelected()) {
            array = CV.ConsultaRango(f, g, cn);
        }
        for (int x = 0; x < array.size(); x++) {
            data[0] = String.valueOf(array.get(x).getId_visita());
            data[1] = String.valueOf(array.get(x).getFecha());
            data[2] = String.valueOf(array.get(x).getSolicita());
            data[3] = String.valueOf(array.get(x).getMotivo());
            data[4] = String.valueOf(array.get(x).getNombre());
            if (array.get(x).getEstado() == 1) {
                data[5] = String.valueOf("En Proceso");
            } else if (array.get(x).getEstado() == 0) {

                data[5] = String.valueOf("Finalizada");
            }

            M.addRow(data);
        }
        tabla.setModel(M);
    }

    void buscarVisitante(String dato, Connection cn) {

        ArrayList<Modelo_Visitante> Visitante = Cv.Consulta(dato, cn);
        if (Visitante.size() > 0) {

            idVisitante.setText(String.valueOf(Visitante.get(0).getId_Visitante()));
            nombreVisitante.setText(Visitante.get(0).getNombreVisitante());

        }
    }

    void traer_Datos(String buscar) {
        ArrayList<Modelo_Visita> mod = CV.ObtenerDatos(buscar, cn);
        if (mod.size() > 0) {
            id.setText(String.valueOf(mod.get(0).getId_visita()));
            solicita.setText(mod.get(0).getSolicita());
            motivo.setText(mod.get(0).getMotivo());
            hora.setText(mod.get(0).getFecha());
            idVisitante.setText(String.valueOf(mod.get(0).getId_Visitante()));
            observaciones.setText(mod.get(0).getObservaciones());

        }

    }

    int ids = 0;

    void editar() {
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            ids = Integer.parseInt(tabla.getValueAt(row, 0).toString());
            nombreVisitante.setText(tabla.getValueAt(row, 4).toString());
            id.setText(String.valueOf(ids));

            jTabbedPane1.setSelectedIndex(1);
            traer_Datos(tabla.getValueAt(row, 0).toString());
        }
    }

    int estados() {
        int es = 0;
        if (Estado_Visita.getSelectedItem().equals("En Proceso")) {
            es = 1;
        } else if (Estado_Visita.getSelectedItem().equals("Finalizada")) {

            es = 0;
        }
        return es;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jTextField3 = new javax.swing.JTextField();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        editar = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel2 = new javax.swing.JPanel();
        Hoy = new javax.swing.JRadioButton();
        fecha = new javax.swing.JRadioButton();
        rangos = new javax.swing.JRadioButton();
        fecha1 = new com.toedter.calendar.JDateChooser();
        a = new javax.swing.JLabel();
        fecha2 = new com.toedter.calendar.JDateChooser();
        edit = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        BttLimpiar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        solicita = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        motivo = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        hora = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Estado_Visita = new javax.swing.JComboBox();
        edit1 = new javax.swing.JButton();
        BttLimpiar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        idVisitante = new javax.swing.JTextField();
        nombreVisitante = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        jTextField3.setText("jTextField3");

        editar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_25px.png"))); // NOI18N
        editar.setText("Editar/Consultar");
        editar.setComponentPopupMenu(jPopupMenu1);
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(editar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jTabbedPane1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        kGradientPanel1.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel1.setStartColor(new java.awt.Color(0, 102, 204));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo De Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);

        buttonGroup2.add(Hoy);
        Hoy.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Hoy.setForeground(new java.awt.Color(255, 255, 255));
        Hoy.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Hoy.setBorderPainted(true);
        Hoy.setContentAreaFilled(false);
        Hoy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Hoy.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Hoy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Today_45px.png"))); // NOI18N
        Hoy.setLabel("HOY");
        Hoy.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Today_45px.png"))); // NOI18N
        Hoy.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Today_55px.png"))); // NOI18N
        Hoy.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Today_55px.png"))); // NOI18N
        Hoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HoyActionPerformed(evt);
            }
        });

        buttonGroup2.add(fecha);
        fecha.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setText("Fecha Especifica");
        fecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fecha.setBorderPainted(true);
        fecha.setContentAreaFilled(false);
        fecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Event_45px.png"))); // NOI18N
        fecha.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Event_45px.png"))); // NOI18N
        fecha.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Event_55px.png"))); // NOI18N
        fecha.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Event_55px.png"))); // NOI18N
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });

        buttonGroup2.add(rangos);
        rangos.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        rangos.setForeground(new java.awt.Color(255, 255, 255));
        rangos.setText("Rango De Fechas");
        rangos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rangos.setBorderPainted(true);
        rangos.setContentAreaFilled(false);
        rangos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Week View_45px.png"))); // NOI18N
        rangos.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Week View_55px.png"))); // NOI18N
        rangos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangosActionPerformed(evt);
            }
        });

        a.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        a.setForeground(new java.awt.Color(255, 255, 255));
        a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a.setText("A");

        edit.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Search_35px.png"))); // NOI18N
        edit.setText("Buscar");
        edit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        edit.setContentAreaFilled(false);
        edit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Search_45px.png"))); // NOI18N
        edit.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Search_35px.png"))); // NOI18N
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        Add.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Add.setForeground(new java.awt.Color(255, 255, 255));
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Add_35px.png"))); // NOI18N
        Add.setText("Añadir Visita");
        Add.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Add.setContentAreaFilled(false);
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addComponent(rangos, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rangos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a)
                            .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(edit, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        kGradientPanel1.add(jPanel2);
        jPanel2.setBounds(20, 12, 652, 220);

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

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
        jScrollPane1.setViewportView(tabla);

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
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BttLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(BttLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        kGradientPanel1.add(jPanel3);
        jPanel3.setBounds(20, 240, 652, 260);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Consultar Visitas", new javax.swing.ImageIcon(getClass().getResource("/iconos/Query_35px.png")), jPanel1); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel2.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel2.setStartColor(new java.awt.Color(0, 102, 204));

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID De Visita:");

        id.setEditable(false);
        id.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        id.setText("jTextField2");
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Solicita A: ");

        solicita.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        solicita.setText("jTextField2");
        solicita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitaActionPerformed(evt);
            }
        });

        motivo.setColumns(20);
        motivo.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        motivo.setRows(5);
        jScrollPane2.setViewportView(motivo);

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Motivo: ");

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hora de la visita:");

        hora.setEditable(false);
        hora.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        hora.setText("jTextField1");

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Estado :");

        Estado_Visita.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Estado_Visita.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Estado_Visita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Estado_VisitaActionPerformed(evt);
            }
        });

        edit1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        edit1.setForeground(new java.awt.Color(255, 255, 255));
        edit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_45px.png"))); // NOI18N
        edit1.setText("Editar");
        edit1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        edit1.setContentAreaFilled(false);
        edit1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_55px.png"))); // NOI18N
        edit1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Edit_45px.png"))); // NOI18N
        edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit1ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Estado_Visita, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(solicita, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edit1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BttLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(solicita, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Estado_Visita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BttLimpiar)))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        kGradientPanel2.add(jPanel5);
        jPanel5.setBounds(20, 20, 650, 230);

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setOpaque(false);

        observaciones.setColumns(20);
        observaciones.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        observaciones.setRows(5);
        observaciones.setText("Ninguna");
        observaciones.setToolTipText("Ninguna");
        observaciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        observaciones.setName("Ninguna"); // NOI18N
        jScrollPane3.setViewportView(observaciones);

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Visitante: ");

        idVisitante.setEditable(false);
        idVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        idVisitante.setText("jTextField3");

        nombreVisitante.setEditable(false);
        nombreVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        nombreVisitante.setText("jTextField4");

        jLabel11.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nombre Visitante:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel11)
                        .addComponent(idVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(nombreVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel2.add(jPanel6);
        jPanel6.setBounds(30, 260, 641, 208);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Detalles De La Visita", new javax.swing.ImageIcon(getClass().getResource("/iconos/More Details_45px.png")), jPanel4); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Consultar Visitas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HoyActionPerformed
        // TODO add your handling code here:
        LoadTable(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH));
        fecha1.setEnabled(false);
        fecha2.setEnabled(false);
    }//GEN-LAST:event_HoyActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:

        fecha1.setEnabled(true);
        fecha2.setEnabled(false);
    }//GEN-LAST:event_fechaActionPerformed

    private void rangosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangosActionPerformed
        // TODO add your handling code here:
        fecha1.setEnabled(true);
        fecha2.setEnabled(true);
    }//GEN-LAST:event_rangosActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        editar();
        id.setEnabled(false);
        hora.setEditable(false);
        idVisitante.setEditable(false);
        nombreVisitante.setEditable(false);
    }//GEN-LAST:event_editarActionPerformed

    private void idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyReleased
        // TODO add your handling code here:
        traer_Datos(id.getText());
    }//GEN-LAST:event_idKeyReleased

    private void Estado_VisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Estado_VisitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Estado_VisitaActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        if (fecha.isSelected()) {
            LoadTable(fecha1.getDate(), Date.from(Instant.EPOCH));
        } else if (rangos.isSelected()) {
            if (fecha2.equals("")) {
                JOptionPane.showMessageDialog(null, "debe ingresar el 2do campo");
                return;
            } else {
                LoadTable(fecha1.getDate(), fecha2.getDate());
            }
        }

    }//GEN-LAST:event_editActionPerformed

    private void solicitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solicitaActionPerformed

    private void edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit1ActionPerformed
        // TODO add your handling code here:
        if (idVisitante.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Visitante!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (solicita.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca A Quien Solicita!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (motivo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Motivo!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (observaciones.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Observaciones!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            CV.editar(ids, hora.getText(), solicita.getText(), motivo.getText(), observaciones.getText(), estados(), Integer.parseInt(idVisitante.getText()), cn);
            LoadTable(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH));
            limpiar();
        }
    }//GEN-LAST:event_edit1ActionPerformed

    private void BttLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_BttLimpiarActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:

        Visita NU = new Visita();
        DeskTop.add(NU);
        CentrarInternal(NU);
        NU.show();
    }//GEN-LAST:event_AddActionPerformed

    private void BttLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiar1ActionPerformed
        // TODO add your handling code here:
        LoadTable(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH));
    }//GEN-LAST:event_BttLimpiar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton BttLimpiar;
    private javax.swing.JButton BttLimpiar1;
    private javax.swing.JComboBox Estado_Visita;
    private javax.swing.JRadioButton Hoy;
    private javax.swing.JLabel a;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton edit;
    private javax.swing.JButton edit1;
    private javax.swing.JMenuItem editar;
    private javax.swing.JRadioButton fecha;
    private com.toedter.calendar.JDateChooser fecha1;
    private com.toedter.calendar.JDateChooser fecha2;
    private javax.swing.JTextField hora;
    private javax.swing.JTextField id;
    private javax.swing.JTextField idVisitante;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTextArea motivo;
    private javax.swing.JTextField nombreVisitante;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JRadioButton rangos;
    private javax.swing.JTextField solicita;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
