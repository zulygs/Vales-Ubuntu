/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import static Escritorio.Escritorio.CentrarInternal;
import static Escritorio.Escritorio.DeskTop;
import OtrosDriver.Conexion;
import OtrosDriver.Driver;
import Visitante.Controlador_Visitante;
import Visitante.Modelo_Visitante;
import java.awt.TextField;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import jdk.nashorn.internal.objects.NativeString;

/**
 *
 * @author Zuly Galicia
 */
public class Consulta_Cita extends javax.swing.JInternalFrame {

    /**
     * Creates new form Consulta_Cita
     */
    public Consulta_Cita() {
        initComponents();
        inicio();
        def();

    }

    Conexion cx = new Conexion();
    Connection cn;
    Controlador_cita cita = new Controlador_cita();
    Controlador_Visitante cv = new Controlador_Visitante();
    Driver ser = new Driver();

    void inicio() {
        cn = cx.Conectar();
        Limpiar();
        fecha1.setEnabled(false);
        fecha2.setEnabled(false);
        bus.setEnabled(false);
        hora.setEnabled(false);
        ((JTextField) this.fecha1.getDateEditor()).setEditable(false);
        ((JTextField) this.fecha2.getDateEditor()).setEditable(false);
        ((JTextField) this.ff.getDateEditor()).setEditable(false);
        ((JSpinner.DefaultEditor) txtHora.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) txtMin.getEditor()).getTextField().setEditable(false);
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

        String[] titulos = {"Codigo Cita", "Fecha", "Solicito A", "Motivo", "Visitante", "Estado"};
        M.setColumnIdentifiers(titulos);
        tabla.setModel(M);
    }

    void Limpiar() {
        bus.setText("");
        id.setText("");
        solicita.setText("");
        motivo.setText("");
        hora.setText("");
        idVisitante.setText("");
        nombreVisitante.setText("");
        observaciones.setText("");
        Estado_Cita.removeAllItems();
        Estado_Cita.addItem("Pendiente");//todavia no se ha hecho 
        Estado_Cita.addItem("Realizada");
        Estado_Cita.addItem("Pasada");
        Estado_Cita.addItem("Cancelada");
        ff.setVisible(false);
        f.setVisible(false);
        txtHora.setVisible(false);
        txtMin.setVisible(false);
        Reprogramar.setVisible(false);
        edit.setEnabled(true);
        edit.setEnabled(true);
    }

    void tabla(Date busc, Date buscar2) {

        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        String f = form.format(busc);
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
        String[] Titulos = {"Codigo De Cita", "Fecha", "Solicito A", "Motivo", "Visitante", "Estado"};
        M.setColumnIdentifiers(Titulos);
        String[] datos = new String[6];
        ArrayList<Modelo_Cita> array = new ArrayList<>();
        if (hoy.isSelected()) {
            array = cita.ConsultaHoy(cn);
        } else if (fecha.isSelected()) {
            array = cita.ConsultaFecha(f, cn);
        } else if (rango.isSelected()) {
            array = cita.ConsultaRango(f, g, cn);
        } else if (realizadas.isSelected()) {
            array = cita.ConsultaRealizadas(cn);
        } else if (canceladas.isSelected()) {
            array = cita.Canceladas(cn);
        } else if (pendientes.isSelected()) {
            array = cita.Pendientes(cn);
        } else if (proceso.isSelected()) {
            array = cita.Proceso(cn);
        }
        for (int x = 0; x < array.size(); x++) {
            datos[0] = String.valueOf(array.get(x).getId_cita());
            datos[1] = array.get(x).getFecha();
            datos[2] = array.get(x).getSolicita();
            datos[3] = array.get(x).getMotivo();
            datos[4] = array.get(x).getNombres();
            if (array.get(x).getEstado() == 0) {
                datos[5] = "Pendiente";
            } else if (array.get(x).getEstado() == 1) {
                datos[5] = "Realizada";
            } else if (array.get(x).getEstado() == 2) {
                datos[5] = "Pasada";
            } else if (array.get(x).getEstado() == 3) {
                datos[5] = "Cancelada";
            }

            M.addRow(datos);
        }
        tabla.setModel(M);

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
        String[] Titulos = {"Codigo De Cita", "Fecha", "Solicito A", "Motivo", "Visitante", "Estado"};
        modelo.setColumnIdentifiers(Titulos);

    }

    void tabla2(String busc, Connection cn) {

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
        String[] Titulos = {"Codigo De Cita", "Fecha", "Solicito A", "Motivo", "Visitante", "Estado"};
        M.setColumnIdentifiers(Titulos);
        String[] datos = new String[6];
        ArrayList<Modelo_Cita> array = cita.COnsulta_General(busc, cn);

        for (int x = 0; x < array.size(); x++) {
            datos[0] = String.valueOf(array.get(x).getId_cita());
            datos[1] = array.get(x).getFecha();
            datos[2] = array.get(x).getSolicita();
            datos[3] = array.get(x).getMotivo();
            datos[4] = array.get(x).getNombres();
            if (array.get(x).getEstado() == 0) {
                datos[5] = "Pendiente";
            } else if (array.get(x).getEstado() == 1) {
                datos[5] = "Realizada";
            } else if (array.get(x).getEstado() == 2) {
                datos[5] = "Pasada";
            } else if (array.get(x).getEstado() == 3) {
                datos[5] = "Cancelada";

            }

            M.addRow(datos);
        }
        tabla.setModel(M);

    }

    void campos() {
        reiniciarJTable(tabla);
        tabla(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH));
        fecha1.setEnabled(false);
        fecha2.setEnabled(false);
        bus.setEnabled(false);
    }

    void buscarVisitante(String dato, Connection cn) {

        ArrayList<Modelo_Visitante> Visitante = cv.Consulta(dato, cn);
        if (Visitante.size() > 0) {

            idVisitante.setText(String.valueOf(Visitante.get(0).getId_Visitante()));
            nombreVisitante.setText(Visitante.get(0).getNombreVisitante());

        }
    }

    void traer_Datos(String buscar) throws ParseException {
        ArrayList<Modelo_Cita> mod = cita.COnsulta_General(buscar, cn);
        if (mod.size() > 0) {
            id.setText(String.valueOf(mod.get(0).getId_cita()));
            solicita.setText(mod.get(0).getSolicita());
            motivo.setText(mod.get(0).getMotivo());
            hora.setText(NativeString.substring(mod.get(0).getFecha(), 0, 19));

            idVisitante.setText(String.valueOf(mod.get(0).getId_visitante()));
            observaciones.setText(mod.get(0).getObservaciones());
            hora.setEditable(false);

        }
        if (mod.get(0).getEstado() == 0) {
            CompararHora();
        }
    }

    int ids = 0;

    void editar() throws ParseException {
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            ids = Integer.parseInt(tabla.getValueAt(row, 0).toString());

            id.setText(String.valueOf(ids));

            jTabbedPane1.setSelectedIndex(1);
            traer_Datos(tabla.getValueAt(row, 0).toString());

        }

    }

    int estados() {
        int es = 0;
        if (Estado_Cita.getSelectedItem().equals("Pendiente")) {
            es = 0;
        } else if (Estado_Cita.getSelectedItem().equals("Realizada")) {
            es = 1;
        } else if (Estado_Cita.getSelectedItem().equals("Cancelada")) {
            es = 3;
        }
        return es;
    }

    public static int Estado;

    public int CompararHora() throws ParseException {
        SimpleDateFormat Hora1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat Hora2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date HoraActual = Hora1.parse(ser.Fecha("%Y-%m-%d %H:%m:%s"));
        Date HoraTarde = Hora2.parse(hora.getText());
        if (HoraActual.compareTo(HoraTarde) > 0) {
            cita.editar(ids, hora.getText(), solicita.getText(), motivo.getText(), observaciones.getText(), 2, 2, Integer.parseInt(idVisitante.getText()), cn);
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Esta Cita Se Paso De Su Hora Asignada!</font></html>");
            tabla2("", cn);
            Estado_Cita.setSelectedItem("Pasada");
        }
        return 0;

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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        ver = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel2 = new javax.swing.JPanel();
        a = new javax.swing.JLabel();
        buscar = new javax.swing.JButton();
        hoy = new javax.swing.JRadioButton();
        fecha = new javax.swing.JRadioButton();
        rango = new javax.swing.JRadioButton();
        realizadas = new javax.swing.JRadioButton();
        canceladas = new javax.swing.JRadioButton();
        pendientes = new javax.swing.JRadioButton();
        proceso = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        Todo = new javax.swing.JRadioButton();
        fecha1 = new com.toedter.calendar.JDateChooser();
        fecha2 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        bus = new javax.swing.JTextField();
        a1 = new javax.swing.JLabel();
        Add = new javax.swing.JButton();
        BttLimpiar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jPanel6 = new javax.swing.JPanel();
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
        Estado_Cita = new javax.swing.JComboBox();
        edit = new javax.swing.JButton();
        BttLimpiar = new javax.swing.JButton();
        Reprogramar = new javax.swing.JButton();
        ff = new com.toedter.calendar.JDateChooser();
        f = new javax.swing.JLabel();
        txtHora = new javax.swing.JSpinner();
        txtMin = new javax.swing.JSpinner();
        REpro = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        idVisitante = new javax.swing.JTextField();
        nombreVisitante = new javax.swing.JTextField();

        ver.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Search Folder_25px.png"))); // NOI18N
        ver.setText("Consultar/Editar");
        ver.setComponentPopupMenu(jPopupMenu1);
        ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ver);

        setBackground(new java.awt.Color(255, 255, 255));
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

        a.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        a.setForeground(new java.awt.Color(255, 255, 255));
        a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a.setText("A");

        buscar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setText("Buscar Fecha");
        buscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buscar.setContentAreaFilled(false);
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        buttonGroup1.add(hoy);
        hoy.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        hoy.setForeground(new java.awt.Color(255, 255, 255));
        hoy.setText("Hoy");
        hoy.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        hoy.setBorderPainted(true);
        hoy.setContentAreaFilled(false);
        hoy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Calendar_35px.png"))); // NOI18N
        hoy.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Calendar_45px.png"))); // NOI18N
        hoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoyActionPerformed(evt);
            }
        });

        buttonGroup1.add(fecha);
        fecha.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setText("Fecha");
        fecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fecha.setBorderPainted(true);
        fecha.setContentAreaFilled(false);
        fecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Pay Date_35px.png"))); // NOI18N
        fecha.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Pay Date_45px.png"))); // NOI18N
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rango);
        rango.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        rango.setForeground(new java.awt.Color(255, 255, 255));
        rango.setText("Rango");
        rango.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rango.setBorderPainted(true);
        rango.setContentAreaFilled(false);
        rango.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Week View_35px.png"))); // NOI18N
        rango.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Week View_45px.png"))); // NOI18N
        rango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangoActionPerformed(evt);
            }
        });

        buttonGroup1.add(realizadas);
        realizadas.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        realizadas.setForeground(new java.awt.Color(255, 255, 255));
        realizadas.setText("Realizadas");
        realizadas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        realizadas.setBorderPainted(true);
        realizadas.setContentAreaFilled(false);
        realizadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Today_35px.png"))); // NOI18N
        realizadas.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Today_45px.png"))); // NOI18N
        realizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizadasActionPerformed(evt);
            }
        });

        buttonGroup1.add(canceladas);
        canceladas.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        canceladas.setForeground(new java.awt.Color(255, 255, 255));
        canceladas.setText("Canceladas");
        canceladas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        canceladas.setBorderPainted(true);
        canceladas.setContentAreaFilled(false);
        canceladas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Calendar Delete_35px.png"))); // NOI18N
        canceladas.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Calendar Delete_45px.png"))); // NOI18N
        canceladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canceladasActionPerformed(evt);
            }
        });

        buttonGroup1.add(pendientes);
        pendientes.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        pendientes.setForeground(new java.awt.Color(255, 255, 255));
        pendientes.setText("Pasadas");
        pendientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pendientes.setBorderPainted(true);
        pendientes.setContentAreaFilled(false);
        pendientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Schedule_35px.png"))); // NOI18N
        pendientes.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Schedule_45px.png"))); // NOI18N
        pendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendientesActionPerformed(evt);
            }
        });

        buttonGroup1.add(proceso);
        proceso.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        proceso.setForeground(new java.awt.Color(255, 255, 255));
        proceso.setText("Pendientes");
        proceso.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        proceso.setBorderPainted(true);
        proceso.setContentAreaFilled(false);
        proceso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Date From_35px.png"))); // NOI18N
        proceso.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Date From_45px.png"))); // NOI18N
        proceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesoActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(Todo);
        Todo.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        Todo.setForeground(new java.awt.Color(255, 255, 255));
        Todo.setText("Todo");
        Todo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Todo.setBorderPainted(true);
        Todo.setContentAreaFilled(false);
        Todo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Data Backup_35px.png"))); // NOI18N
        Todo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Data Backup_45px.png"))); // NOI18N
        Todo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(hoy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rango, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(buscar))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 22, Short.MAX_VALUE)
                                        .addComponent(Todo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54))
                                    .addComponent(fecha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fecha2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(canceladas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pendientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(proceso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(realizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Todo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(a)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscar))
                    .addComponent(rango, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(realizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canceladas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proceso, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        kGradientPanel1.add(jPanel2);
        jPanel2.setBounds(10, 20, 370, 530);

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
        jScrollPane1.setViewportView(tabla);

        bus.setText("jTextField1");
        bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busKeyReleased(evt);
            }
        });

        a1.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        a1.setForeground(new java.awt.Color(255, 255, 255));
        a1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a1.setText("ID Visita/Visitante ");

        Add.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Add.setForeground(new java.awt.Color(255, 255, 255));
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Add_35px.png"))); // NOI18N
        Add.setText("Añadir Cita");
        Add.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Add.setContentAreaFilled(false);
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(a1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bus, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BttLimpiar1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Add)
                            .addComponent(BttLimpiar1))))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        kGradientPanel1.add(jPanel3);
        jPanel3.setBounds(410, 20, 640, 530);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Consultar Citas", new javax.swing.ImageIcon(getClass().getResource("/iconos/Schedule_45px.png")), jPanel1); // NOI18N

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));

        kGradientPanel2.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel2.setStartColor(new java.awt.Color(0, 102, 204));

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID De Cita:");

        id.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        id.setText("jTextField2");
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Solicita A: ");

        solicita.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        solicita.setText("jTextField2");

        motivo.setColumns(20);
        motivo.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        motivo.setRows(5);
        jScrollPane2.setViewportView(motivo);

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Motivo: ");

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hora de la Cita:");

        hora.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        hora.setText("jTextField1");

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Estado :");

        Estado_Cita.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Estado_Cita.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Estado_Cita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Estado_CitaActionPerformed(evt);
            }
        });

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

        Reprogramar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Reprogramar.setText("Reprogramar");
        Reprogramar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReprogramarActionPerformed(evt);
            }
        });

        ff.setDateFormatString("yyy-MM-dd");

        f.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        f.setForeground(new java.awt.Color(255, 255, 255));
        f.setText("Fecha :");

        txtHora.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        txtHora.setModel(new javax.swing.SpinnerNumberModel(1, 1, 24, 1));

        txtMin.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        txtMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 15));

        REpro.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        REpro.setText("Reprogramar Cita");
        REpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REproActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ff, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Reprogramar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(solicita, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Estado_Cita, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 68, Short.MAX_VALUE)
                                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BttLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(REpro))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 5, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(REpro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(solicita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(Estado_Cita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edit)
                        .addComponent(BttLimpiar))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(24, 24, 24))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Reprogramar))
                    .addComponent(f))
                .addContainerGap())
        );

        kGradientPanel2.add(jPanel6);
        jPanel6.setBounds(120, 10, 750, 250);

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setOpaque(false);

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
        jLabel8.setText("Visitante:");

        idVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        idVisitante.setText("jTextField3");
        idVisitante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idVisitanteKeyReleased(evt);
            }
        });

        nombreVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        nombreVisitante.setText("jTextField4");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nombreVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 44, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(idVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        kGradientPanel2.add(jPanel7);
        jPanel7.setBounds(120, 260, 750, 255);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Detalles", new javax.swing.ImageIcon(getClass().getResource("/iconos/More Details_45px.png")), jPanel4); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Consultar Citas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoyActionPerformed
        // TODO add your handling code here:
        campos();
    }//GEN-LAST:event_hoyActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
        reiniciarJTable(tabla);
        fecha1.setEnabled(true);
        fecha2.setEnabled(false);
        bus.setEnabled(false);
    }//GEN-LAST:event_fechaActionPerformed

    private void rangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangoActionPerformed
        // TODO add your handling code here:
        reiniciarJTable(tabla);
        fecha1.setEnabled(true);
        fecha2.setEnabled(true);
        bus.setEnabled(false);
    }//GEN-LAST:event_rangoActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
        reiniciarJTable(tabla);
        if (fecha.isSelected()) {
            tabla(fecha1.getDate(), Date.from(Instant.EPOCH));
        } else if (rango.isSelected()) {
            tabla(fecha1.getDate(), fecha2.getDate());
        }

    }//GEN-LAST:event_buscarActionPerformed

    private void realizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizadasActionPerformed
        // TODO add your handling code here:
        campos();
    }//GEN-LAST:event_realizadasActionPerformed

    private void canceladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canceladasActionPerformed
        // TODO add your handling code here:
        campos();
    }//GEN-LAST:event_canceladasActionPerformed

    private void pendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendientesActionPerformed
        // TODO add your handling code here:
        campos();
    }//GEN-LAST:event_pendientesActionPerformed

    private void procesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesoActionPerformed
        // TODO add your handling code here:
        campos();
    }//GEN-LAST:event_procesoActionPerformed

    private void TodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TodoActionPerformed
        // TODO add your handling code here:
        reiniciarJTable(tabla);
        tabla2("", cn);
        bus.setEnabled(true);
    }//GEN-LAST:event_TodoActionPerformed

    private void busKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busKeyReleased
        // TODO add your handling code here:
        tabla2(bus.getText(), cn);
    }//GEN-LAST:event_busKeyReleased

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        try {
            // TODO add your handling code here:
            traer_Datos(id.getText());
        } catch (ParseException ex) {
            Logger.getLogger(Consulta_Cita.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setEnabled(false);
        solicita.setEnabled(false);
        hora.setEnabled(false);
    }//GEN-LAST:event_idActionPerformed

    private void idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyReleased
        try {
            // TODO add your handling code here:
            traer_Datos(id.getText());
        } catch (ParseException ex) {
            Logger.getLogger(Consulta_Cita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_idKeyReleased

    private void Estado_CitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Estado_CitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Estado_CitaActionPerformed

    private void idVisitanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idVisitanteKeyReleased
        // TODO add your handling code here:
        buscarVisitante(idVisitante.getText(), cn);
        idVisitante.setEnabled(false);
        nombreVisitante.setEnabled(false);
    }//GEN-LAST:event_idVisitanteKeyReleased

    private void verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verActionPerformed
        try {
            // TODO add your handling code here:
            editar();
        } catch (ParseException ex) {
            Logger.getLogger(Consulta_Cita.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setEditable(false);
        idVisitante.setEditable(false);
        nombreVisitante.setEditable(false);
    }//GEN-LAST:event_verActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
            cita.editar(ids, hora.getText(), solicita.getText(), motivo.getText(), observaciones.getText(), estados(), estados(), Integer.parseInt(idVisitante.getText()), cn);
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado!</font></html>");
       
            Limpiar();
    }//GEN-LAST:event_editActionPerformed

    private void BttLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiarActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_BttLimpiarActionPerformed

    private void REproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REproActionPerformed
        // TODO add your handling code here:
        edit.setEnabled(false);
        ff.setVisible(true);
        f.setVisible(true);
        txtHora.setVisible(true);
        txtMin.setVisible(true);
        Reprogramar.setVisible(true);
        edit.setEnabled(false);
    }//GEN-LAST:event_REproActionPerformed

    private void ReprogramarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReprogramarActionPerformed
        // TODO add your handling code here:
        String Hora = txtHora.getValue().toString();
        String Minutos = txtMin.getValue().toString();
        if (txtHora.getValue().toString().length() < 2) {
            Hora = "0" + Hora;
        }
        if (Minutos.length() < 2) {
            Minutos = "0" + Minutos;
        }
           if(((JTextField) ff.getDateEditor()).getText().compareTo(ser.Fecha("%Y-%m-%s"))<0){
          JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Fecha Caducada!</font></html>");
       return;
     
        }
  /*  if(String.valueOf(Hora + ":" + Minutos + ":00").compareTo(ser.Fecha("%H:%i:%s"))>-1){
        if( ((JTextField) ff.getDateEditor()).getText().compareTo(ser.Fecha("%Y-%m-%d")) >- 1){
         
        cita.editar(ids, ((JTextField) ff.getDateEditor()).getText() + " " + Hora + ":" + Minutos + ":00", solicita.getText(), motivo.getText(), observaciones.getText(), 0, 0, Integer.parseInt(idVisitante.getText()), cn);

            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡La Ha Sido Cita Reprogramada!</font></html>");
            Limpiar();
        }} */else {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Fecha NO Aceptada!</font></html>");
        }
    }//GEN-LAST:event_ReprogramarActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        Vista_Cita NU = new Vista_Cita();
        DeskTop.add(NU);
        CentrarInternal(NU);
        NU.show();
    }//GEN-LAST:event_AddActionPerformed

    private void BttLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiar1ActionPerformed
        // TODO add your handling code here:
        tabla2("", cn);
    }//GEN-LAST:event_BttLimpiar1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton BttLimpiar;
    private javax.swing.JButton BttLimpiar1;
    private javax.swing.JComboBox Estado_Cita;
    private javax.swing.JRadioButton REpro;
    private javax.swing.JButton Reprogramar;
    private javax.swing.JRadioButton Todo;
    private javax.swing.JLabel a;
    private javax.swing.JLabel a1;
    private javax.swing.JTextField bus;
    private javax.swing.JButton buscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton canceladas;
    private javax.swing.JButton edit;
    private javax.swing.JLabel f;
    private javax.swing.JRadioButton fecha;
    private com.toedter.calendar.JDateChooser fecha1;
    private com.toedter.calendar.JDateChooser fecha2;
    private com.toedter.calendar.JDateChooser ff;
    private javax.swing.JTextField hora;
    private javax.swing.JRadioButton hoy;
    private javax.swing.JTextField id;
    private javax.swing.JTextField idVisitante;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTextArea motivo;
    private javax.swing.JTextField nombreVisitante;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JRadioButton pendientes;
    private javax.swing.JRadioButton proceso;
    private javax.swing.JRadioButton rango;
    private javax.swing.JRadioButton realizadas;
    private javax.swing.JTextField solicita;
    private javax.swing.JTable tabla;
    private javax.swing.JSpinner txtHora;
    private javax.swing.JSpinner txtMin;
    private javax.swing.JMenuItem ver;
    // End of variables declaration//GEN-END:variables
}
