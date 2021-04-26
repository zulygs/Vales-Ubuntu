/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vale;

import Escritorio.Escritorio;
import static Escritorio.Escritorio.CentrarInternal;
import static Escritorio.Escritorio.DeskTop;
import OtrosDriver.Conexion;
import OtrosDriver.Driver;
import static Vale.Vista_Vales.idVehiculo;
import com.toedter.calendar.JYearChooser;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.File;
import java.util.Enumeration;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author Zuly Galicia
 */
public class Consulta_Vales extends javax.swing.JInternalFrame {

    /**
     * Creates new form Consulta_Vales
     */
    public Consulta_Vales() {
        initComponents();
        cn = cx.Conectar();
        limpiar();

        try {
            System.out.println("Directorio actual: " + miDir.getCanonicalPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    File miDir = new File(".");
    Controlador_Vale vale = new Controlador_Vale();
    Conexion cx = new Conexion();
    Connection cn;
    Driver ser = new Driver();
    ArrayList<Modelo_Vale> mode;

    void limpiar() {
        campos();
        buscar.setText("");
        buscar.setVisible(false);
        jLabel9.setVisible(false);
        Generar.setVisible(false);
        jPanel5.setVisible(false);
        ((JTextField) this.fecha1.getDateEditor()).setEditable(false);
        ((JTextField) this.fecha2.getDateEditor()).setEditable(false);
        JSpinner spinner = (JSpinner) year.getSpinner();
        ((javax.swing.JTextField) spinner.getEditor()).setEditable(false);
    }

    void tabla(Date busc, Date buscar2, String bus) {

        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat form2 = new SimpleDateFormat("yyyy-MM");
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
        String[] Titulos = {"Codigo", "Fecha", "Visitante", "Nombre Visitante", "Entrego", "Placa", "Monto", "Empresa", "Estado"};
        M.setColumnIdentifiers(Titulos);
        String[] datos = new String[9];
        ArrayList<Modelo_Vale> array = new ArrayList<>();

        if (hoy.isSelected()) {
            if (anulado.isSelected()) {
                array = vale.Hoy("Anulado", cn);
            } else if (realizados.isSelected()) {
                array = vale.Hoy("Realizado", cn);
            } else if (todos.isSelected()) {
                array = vale.Hoy("", cn);
            } else {
                array = vale.Hoy("", cn);
            }

        } else if (fecha.isSelected()) {
            if (anulado.isSelected()) {
                array = vale.fecha(f, "Anulado", cn);
            } else if (realizados.isSelected()) {
                array = vale.fecha(f, "Realizado", cn);
            } else if (todos.isSelected()) {
                array = vale.fecha(f, "", cn);
            } else {
                array = vale.fecha(f, "", cn);
                System.out.println(vale.fecha(f, "", cn));
            }

        } else if (rango.isSelected()) {
            if (anulado.isSelected()) {
                array = vale.Rango(f, g, "Anulado", cn);
            } else if (realizados.isSelected()) {
                array = vale.Rango(f, g, "Realizado", cn);
            } else if (todos.isSelected()) {
                array = vale.Rango(f, g, "", cn);
            } else {
                array = vale.Rango(f, g, "", cn);
            }

        } else if (mes.isSelected()) {
            if (anulado.isSelected()) {
                tabla2(bus);
            } else if (realizados.isSelected()) {
                array = vale.mes(String.valueOf(bus), "Realizado", cn);
            } else if (todos.isSelected()) {
                array = vale.mes(String.valueOf(bus), "", cn);
            } else {
                array = vale.mes(String.valueOf(bus), "", cn);

            }

        } else if (todos.isSelected()) {
            array = vale.ConsultaGeneral(bus, cn);
        }

        for (int x = 0; x < array.size(); x++) {
            datos[0] = String.valueOf(array.get(x).getId_Vale());
            datos[1] = array.get(x).getFecha();
            datos[2] = String.valueOf(array.get(x).getIdVisitante());
            datos[3] = String.valueOf(array.get(x).getNombreVisitante());
            datos[4] = array.get(x).getDespachador();
            datos[5] = array.get(x).getPlaca();
            datos[6] = String.valueOf(array.get(x).getMonto());
            datos[7] = String.valueOf(array.get(x).getNombreEmpresa());
            datos[8] = array.get(x).getEstado();
            M.addRow(datos);
        }

        tabla.setModel(M);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(150);
    }

    void tabla2(String bus) {
        DefaultTableModel M = new DefaultTableModel();
        String[] Titulos = {"Codigo Vale", "Fecha", "Visitante", "Nombre Visitante", "Placa", "Monto", "Estado"};
        M.setColumnIdentifiers(Titulos);
        String[] datos = new String[9];
        ArrayList<Modelo_Vale> array = vale.ConsultaAnulados(bus, cn);

        for (int x = 0; x < array.size(); x++) {
            datos[0] = String.valueOf(array.get(x).getId_Vale());
            datos[1] = array.get(x).getFecha();
            datos[2] = String.valueOf(array.get(x).getIdVisitante());
            datos[3] = String.valueOf(array.get(x).getNombreVisitante());

            datos[4] = array.get(x).getPlaca();
            datos[5] = String.valueOf(array.get(x).getMonto());

            datos[6] = array.get(x).getEstado();
            M.addRow(datos);
        }
        tabla.setModel(M);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(150);
    }
String ve="01";
String vi="09";
    String fechasss() {
        String va = "";
        if (meses.getMonth() == 0) {
            va = "01";
        } else if (meses.getMonth() == 1) {
            va = "02";
        } else if (meses.getMonth() == 2) {
            va = "03";
        } else if (meses.getMonth() == 3) {
            va = "04";
        } else if (meses.getMonth() == 4) {
            va = "05";
        } else if (meses.getMonth() == 5) {
            va = "06";
        } else if (meses.getMonth() == 6) {
            va = "07";
        } else if (meses.getMonth() == 7) {
            va ="08";
        } else if (meses.getMonth() == 8) {
            va = "09";
        } else if (meses.getMonth() == 9) {
            va = "10";
        } else if (meses.getMonth() == 10) {
            va = "11";
        }
        if (meses.getMonth() == 11) {
            va = "12";

        }
        return va;
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
        String[] Titulos = {"Codigo Vale", "Fecha", "Entrego", "Placa", "Monto"};
        modelo.setColumnIdentifiers(Titulos);

    }

    void campos() {
        reiniciarJTable(tabla);
        tabla(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH), "");
        fecha1.setVisible(false);
        fecha2.setVisible(false);
        meses.setVisible(false);
        year.setVisible(false);
        buscarrango.setVisible(false);
        buscarmes.setVisible(false);
        jLabel3.setVisible(false);
    }

    void generarreporteclienteindividual() {
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            try {
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Report/vale.jasper"));
                Map parame = new HashMap();
                parame.put("id_vale", String.valueOf(tabla.getValueAt(row, 0)));
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parame, cn);
                String ubicacionSave = System.getProperty("user.dir") + "/reportes/" + ser.Fecha("%Y-%m-%d_%H-%i-%s") + "" + "vale No." + tabla.getValueAt(row, 0) + "---.pdf";
                JasperExportManager.exportReportToPdfFile(print, ubicacionSave);
                JasperViewer view = new JasperViewer(print, false);
                view.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }

    public void valeReporte() {
        System.out.println("eee");
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            try {
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Report/ValesGasolina.jasper"));
                Map parame = new HashMap();
                parame.put("id_vale", String.valueOf(tabla.getValueAt(row, 0)));
                parame.put("DirectorioAbsoluto", miDir.getCanonicalPath() + "/00014.jpg");
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parame, cn);
                JasperViewer view = new JasperViewer(print, false);
                view.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }

    public void Reportemes() {

        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Report/ReporteValesMes.jasper"));
            Map parame = new HashMap();
            parame.put("mes", year.getYear() + "-" + fechasss());
            parame.put("img", miDir.getCanonicalPath() + "//Compilar2020//Crear Exe a partir de Jar//src//IMG//logoo.png");
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parame, cn);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
    }

    double funcionSum(double a, double b) {
        return a + b;

    }

    void eliminar() {
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                vale.update_estado_vale(tabla.getValueAt(row, 0).toString(), cn);

                vale.Guardar_ValeAnulados(Integer.parseInt(tabla.getValueAt(row, 0).toString()), tabla.getValueAt(row, 1).toString(), tabla.getValueAt(row, 4).toString(), Integer.parseInt(tabla.getValueAt(row, 2).toString()), tabla.getValueAt(row, 5).toString(), Double.parseDouble(tabla.getValueAt(row, 6).toString()), "Anulado", cn);
                vale.eliminar(tabla.getValueAt(row, 0).toString(), cn);
            }
        }
    }
    public void valeReporteRango() {
        System.out.println(IDvale1Rango.getText());
            try {
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Report/ValesGasolinaRango.jasper"));
                Map parame = new HashMap();
                parame.put("DirectorioAbsoluto", miDir.getCanonicalPath() + "/00014.jpg");
                parame.put("vale",IDvale1Rango.getText());
                parame.put("valee",IDvale2Rango.getText());
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parame, cn);
                JasperViewer view = new JasperViewer(print, false);
                view.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e);
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        Vale = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel2 = new javax.swing.JPanel();
        mes = new javax.swing.JRadioButton();
        todos = new javax.swing.JRadioButton();
        hoy = new javax.swing.JRadioButton();
        fecha = new javax.swing.JRadioButton();
        rango = new javax.swing.JRadioButton();
        Add = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        anulado = new javax.swing.JRadioButton();
        realizados = new javax.swing.JRadioButton();
        Todos = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        meses = new com.toedter.calendar.JMonthChooser();
        Generar = new javax.swing.JButton();
        year = new com.toedter.calendar.JYearChooser();
        buscarmes = new javax.swing.JButton();
        fecha1 = new com.toedter.calendar.JDateChooser();
        fecha2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        buscarrango = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        BttLimpiar1 = new javax.swing.JButton();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jPanel10 = new javax.swing.JPanel();
        ImprimirRango = new javax.swing.JButton();
        IDvale1Rango = new javax.swing.JTextField();
        IDvale2Rango = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        Vale.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Vale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Ticket_35px.png"))); // NOI18N
        Vale.setText("Reporte");
        Vale.setActionCommand("Vale");
        Vale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Vale);
        Vale.getAccessibleContext().setAccessibleName("Vale");

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Trash_25px.png"))); // NOI18N
        Eliminar.setText("Anular Vale");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jTabbedPane2.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        kGradientPanel1.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel1.setStartColor(new java.awt.Color(0, 102, 204));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo De Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);

        buttonGroup1.add(mes);
        mes.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        mes.setForeground(new java.awt.Color(255, 255, 255));
        mes.setText("Mes");
        mes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mes.setBorderPainted(true);
        mes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Month View_35px.png"))); // NOI18N
        mes.setOpaque(false);
        mes.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Month View_45px.png"))); // NOI18N
        mes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesActionPerformed(evt);
            }
        });

        buttonGroup1.add(todos);
        todos.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        todos.setForeground(new java.awt.Color(255, 255, 255));
        todos.setText("Todos");
        todos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        todos.setBorderPainted(true);
        todos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Check All_35px.png"))); // NOI18N
        todos.setOpaque(false);
        todos.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Check All_45px.png"))); // NOI18N
        todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todosActionPerformed(evt);
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

        Add.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Add.setForeground(new java.awt.Color(255, 255, 255));
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Add_35px.png"))); // NOI18N
        Add.setText("Añadir Vale");
        Add.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Add.setContentAreaFilled(false);
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setOpaque(false);

        buttonGroup2.add(anulado);
        anulado.setForeground(new java.awt.Color(255, 255, 255));
        anulado.setText("Anulados");
        anulado.setContentAreaFilled(false);
        anulado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anuladoActionPerformed(evt);
            }
        });

        buttonGroup2.add(realizados);
        realizados.setForeground(new java.awt.Color(255, 255, 255));
        realizados.setText("Realizados");
        realizados.setContentAreaFilled(false);
        realizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizadosActionPerformed(evt);
            }
        });

        buttonGroup2.add(Todos);
        Todos.setForeground(new java.awt.Color(255, 255, 255));
        Todos.setText("Todos");
        Todos.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anulado, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(realizados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Todos, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anulado)
                    .addComponent(realizados)
                    .addComponent(Todos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setOpaque(false);

        meses.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N

        Generar.setText("Reporte");
        Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarActionPerformed(evt);
            }
        });

        buscarmes.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        buscarmes.setForeground(new java.awt.Color(255, 255, 255));
        buscarmes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Search_25px.png"))); // NOI18N
        buscarmes.setText("Buscar");
        buscarmes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buscarmes.setContentAreaFilled(false);
        buscarmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarmesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(meses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Generar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(buscarmes, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Generar)
                    .addComponent(buscarmes))
                .addGap(10, 10, 10))
        );

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("A:");

        buscarrango.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        buscarrango.setForeground(new java.awt.Color(255, 255, 255));
        buscarrango.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Search_25px.png"))); // NOI18N
        buscarrango.setText("Buscar");
        buscarrango.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buscarrango.setContentAreaFilled(false);
        buscarrango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarrangoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(rango, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(todos, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(buscarrango, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todos, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rango, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarrango)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 740, 230);

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Visitante:");

        buscar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        buscar.setText("jTextField1");
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
                .addGap(21, 21, 21)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(BttLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BttLimpiar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        kGradientPanel1.add(jPanel3);
        jPanel3.setBounds(10, 242, 740, 280);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Vales Realizados", new javax.swing.ImageIcon(getClass().getResource("/iconos/Invoice_45px.png")), jPanel1); // NOI18N

        kGradientPanel2.setForeground(new java.awt.Color(255, 255, 255));
        kGradientPanel2.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel2.setStartColor(new java.awt.Color(0, 102, 204));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imprimir Rango", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cantarell", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel10.setOpaque(false);

        ImprimirRango.setBackground(new java.awt.Color(255, 255, 255));
        ImprimirRango.setForeground(new java.awt.Color(255, 255, 255));
        ImprimirRango.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/impresora.png"))); // NOI18N
        ImprimirRango.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        ImprimirRango.setFocusPainted(false);
        ImprimirRango.setOpaque(false);
        ImprimirRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirRangoActionPerformed(evt);
            }
        });

        IDvale1Rango.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        IDvale2Rango.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        IDvale2Rango.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("-");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Desde Id Vale");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hasta Id Vale");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(ImprimirRango, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(IDvale1Rango))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(IDvale2Rango))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDvale1Rango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDvale2Rango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addComponent(ImprimirRango)
                .addGap(32, 32, 32))
        );

        kGradientPanel2.add(jPanel10);
        jPanel10.setBounds(30, 40, 520, 230);
        jPanel10.getAccessibleContext().setAccessibleName("Imprimir Rango");
        jPanel10.getAccessibleContext().setAccessibleDescription("Imprimir Rango");

        jTabbedPane2.addTab("Imprimir Por Rango", kGradientPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ValeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValeActionPerformed
        // TODO add your handling code here:
        valeReporte();
    }//GEN-LAST:event_ValeActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        tabla(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH), "");
    }//GEN-LAST:event_EliminarActionPerformed

    private void BttLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttLimpiar1ActionPerformed
        // TODO add your handling code here:
        tabla(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH), "");
    }//GEN-LAST:event_BttLimpiar1ActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        // TODO add your handling code here:
        tabla(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH), buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void buscarrangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarrangoActionPerformed
        // TODO add your handling code here:
        if (fecha.isSelected()) {
            tabla(fecha1.getDate(), Date.from(Instant.EPOCH), "");
            System.out.println(fecha1.getDate());
        } else if (rango.isSelected()) {
            tabla(fecha1.getDate(), fecha2.getDate(), "");
        }
        jPanel5.setVisible(false);
        Generar.setVisible(false);
        anulado.setVisible(false);
    }//GEN-LAST:event_buscarrangoActionPerformed

    private void buscarmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarmesActionPerformed
        // TODO add your handling code here:+
        tabla(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH), year.getYear() + "-" + fechasss());
        System.out.println(year.getYear() + "-" + fechasss());
    }//GEN-LAST:event_buscarmesActionPerformed

    private void GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarActionPerformed
        // TODO add your handling code here:
        Reportemes();
    }//GEN-LAST:event_GenerarActionPerformed

    private void realizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_realizadosActionPerformed

    private void anuladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anuladoActionPerformed
        // TODO add your handling code here:
        tabla2(year.getYear() + "-" + fechasss());
        System.out.println(fechasss());
    }//GEN-LAST:event_anuladoActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        Vista_Vales NU = new Vista_Vales();
        DeskTop.add(NU);
        CentrarInternal(NU);
        NU.show();
    }//GEN-LAST:event_AddActionPerformed

    private void rangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangoActionPerformed
        // TODO add your handling code here:
        reiniciarJTable(tabla);
        fecha1.setVisible(true);
        fecha2.setVisible(true);
        meses.setVisible(false);
        year.setVisible(false);
        buscarrango.setVisible(true);
        buscarmes.setVisible(false);
        jLabel3.setVisible(true);
        buscar.setVisible(false);
        jLabel9.setVisible(false);
        anulado.setVisible(false);
    }//GEN-LAST:event_rangoActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
        reiniciarJTable(tabla);
        fecha1.setVisible(true);
        fecha2.setVisible(false);
        meses.setVisible(false);
        year.setVisible(false);
        buscarrango.setVisible(true);
        buscarmes.setVisible(false);
        jLabel3.setVisible(false);
        buscar.setVisible(false);
        jLabel9.setVisible(false);
        jPanel5.setVisible(false);
        Generar.setVisible(false);
        anulado.setVisible(false);
    }//GEN-LAST:event_fechaActionPerformed

    private void hoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoyActionPerformed
        // TODO add your handling code here:
        campos();
        jPanel5.setVisible(false);
        Generar.setVisible(false);
        anulado.setVisible(false);
    }//GEN-LAST:event_hoyActionPerformed

    private void todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todosActionPerformed
        // TODO add your handling code here:
        campos();
        buscar.setVisible(true);
        jLabel9.setVisible(true);
        jPanel5.setVisible(false);
        Generar.setVisible(false);
        anulado.setVisible(false);
    }//GEN-LAST:event_todosActionPerformed

    private void mesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesActionPerformed
        // TODO add your handling code here:
        reiniciarJTable(tabla);
        fecha1.setVisible(false);
        fecha2.setVisible(false);
        meses.setVisible(true);
        year.setVisible(true);
        buscarrango.setVisible(false);
        buscarmes.setVisible(true);
        jLabel3.setVisible(false);
        buscar.setVisible(false);
        jLabel9.setVisible(false);
        Generar.setVisible(true);
        jPanel5.setVisible(true);
        anulado.setVisible(true);
    }//GEN-LAST:event_mesActionPerformed

    private void ImprimirRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirRangoActionPerformed
        // TODO add your handling code here:
        valeReporteRango();
    }//GEN-LAST:event_ImprimirRangoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton BttLimpiar1;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JButton Generar;
    private javax.swing.JTextField IDvale1Rango;
    private javax.swing.JTextField IDvale2Rango;
    private javax.swing.JButton ImprimirRango;
    private javax.swing.JRadioButton Todos;
    private javax.swing.JMenuItem Vale;
    private javax.swing.JRadioButton anulado;
    private javax.swing.JTextField buscar;
    private javax.swing.JButton buscarmes;
    private javax.swing.JButton buscarrango;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton fecha;
    private com.toedter.calendar.JDateChooser fecha1;
    private com.toedter.calendar.JDateChooser fecha2;
    private javax.swing.JRadioButton hoy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JRadioButton mes;
    private com.toedter.calendar.JMonthChooser meses;
    private javax.swing.JRadioButton rango;
    private javax.swing.JRadioButton realizados;
    private javax.swing.JTable tabla;
    private javax.swing.JRadioButton todos;
    private com.toedter.calendar.JYearChooser year;
    // End of variables declaration//GEN-END:variables
}
