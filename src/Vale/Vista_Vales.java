/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vale;

import Citas.Consulta_VisitanteVisitas;
import Empresa.Modelo_Empresa;
import Escritorio.Escritorio;
import static Escritorio.Escritorio.CentrarInternal;
import static Escritorio.Escritorio.DeskTop;
import OtrosDriver.Conexion;
import OtrosDriver.Driver;
import Vehiculo.Driver_Vehiculo;
import Vehiculo.Modelo_Vehiculo;
import Vehiculo.Vehiculo;
import Visitante.Controlador_Visitante;
import Visitante.Modelo_Visitante;
import Visitante.Registrar_Visitante;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author familia
 */
public class Vista_Vales extends javax.swing.JInternalFrame {

    /**
     * Creates new form Vista_Vales
     */
    public Vista_Vales() {
        initComponents();
        inicio();

    }

    Conexion cx = new Conexion();
    Connection cn;
    Controlador_Vale vale = new Controlador_Vale();
    Controlador_Visitante cv = new Controlador_Visitante();
    Driver_Vehiculo dv = new Driver_Vehiculo();
    Driver ser = new Driver();
    ArrayList<Modelo_Vale> mod;
    ArrayList<Modelo_Visitante> mode;
    Consulta_Vales cc = new Consulta_Vales();

    void inicio() {
        limpiar();
        cn = cx.Conectar();
        fecha.setText(ser.Fecha("%d-%m-%Y"));
        buscarVisitante(Consulta_Visitante3.bus, cn);
        ver();
        ser.OnlyNumbers(monto);
    }

    void limpiar() {
        idVisitante.setText("");
        nombreVisitante.setText("");
        idVehiculo.setText("");
        NombreVehiculo.setText("");
        gas.setText("");
        saldo.setText("");
        monto.setText("0");
        Total.setText("");
        MontoMes.setText("");
        empresa.setText("");
        idEmpresa.setText("");
    }

    void limpiar2() {
        idVehiculo.setText("");
        NombreVehiculo.setText("");
        gas.setText("");
        saldo.setText("");
        monto.setText("");
        Total.setText("");
        MontoMes.setText("");
    }

    void Guardar(String fecha, String firma, String despachador, String Admin, String idVisitante, String Placa, String Monto, String estado, Connection cn) {
        int idvisitante = Integer.parseInt(idVisitante);
        double monto = Double.parseDouble(Monto);
        if (idVisitante.equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca ID Visitante!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Monto.equals("0")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Monto!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Placa.equals("")) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='6' color='094293'>¡Introduzca Nombre!</font></html>", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            vale.Guardar_Vale(fecha, firma, despachador, Admin, idvisitante, Placa, monto, estado, cn);
            limpiar();
        }
    }

    void buscarVisitante(String dato, Connection cn) {
        ArrayList<Modelo_Visitante> Visitante = cv.Consulta(dato, cn);
        if (Visitante.size() > 0) {
            idVisitante.setText(String.valueOf(Visitante.get(0).getId_Visitante()));
            nombreVisitante.setText(Visitante.get(0).getNombreVisitante());
            empresa.setText(String.valueOf(Visitante.get(0).getEmpresa()));
            idEmpresa.setText(String.valueOf(Visitante.get(0).getIdEmpresa()));
            LugarMonto.setText(Visitante.get(0).getLugarMonto());
        }
    }

    void TraerSaldo(String empresa, String d, String dato, Connection cn) {
        ArrayList<Modelo_Vale> val = vale.Saldo(empresa, d, dato, cn);
        if (val.size() > 0) {
            MontoMes.setText(String.valueOf(val.get(0).getMonto()));
            if (val.get(0).getSaldo() == 0) {
                 saldo.setText("Sin Saldo");
                 
                 monto.setEditable(false);
                //saldo.setText(String.valueOf(val.get(0).getMonto()));
            } else {
                monto.setEditable(true);
                saldo.setText(String.valueOf(val.get(0).getSaldo()));
            }
        }
    }

    void TraerSaldoPlomero(String d, String dato, Connection cn) {
        ArrayList<Modelo_Vale> val = vale.SaldoPlomero(d, dato, cn);
        if (val.size() > 0) {
            MontoMes.setText(String.valueOf(val.get(0).getMonto()));
            if (val.get(0).getSaldo() == 0) {
                saldo.setText("Sin Saldo");
                monto.setEditable(false);
              //  saldo.setText(String.valueOf(val.get(0).getMonto()));
            } else {
                monto.setEditable(true);
                saldo.setText(String.valueOf(val.get(0).getSaldo()));
            }
        }
    }

    void TraerSaldoLeonel(String d, String dato, Connection cn) {
        ArrayList<Modelo_Vale> val = vale.SaldoLeonel(d, dato, cn);
        if (val.size() > 0) {
            MontoMes.setText(String.valueOf(val.get(0).getMonto()));
            if (val.get(0).getSaldo() == 0) {
                saldo.setText(String.valueOf(val.get(0).getMonto()));
            } else {
                saldo.setText(String.valueOf(val.get(0).getSaldo()));
            }
        }
    }

    void ver() {
        if (idVisitante.getText().equals("42")) {
            buscarAuto3(Consulta_Vehiculos.bus, cn);
        } else if (LugarMonto.getText().equals("E")) {
            buscarAuto(Consulta_Vehiculos.bus, cn);
        } else {
            buscarAuto2(Consulta_Vehiculos.bus, cn);
        }
    }

    void buscarAuto(String dato, Connection cn) {
        ArrayList<Modelo_Vehiculo> auto = dv.Consultar(dato, cn);
        if (auto.size() > 0) {
            idVehiculo.setText(String.valueOf(auto.get(0).getPlaca()));
            NombreVehiculo.setText(auto.get(0).getMarca());
            gas.setText(auto.get(0).getGas());
            // if(LugarMonto.equals("E")){
            TraerSaldo(idEmpresa.getText(), idVehiculo.getText(), ser.Fecha("%Y-%m"), cn);
            //}else{
            // TraerSaldoPlomero(idVisitante.getText(), ser.Fecha("%Y-%m"), cn);         
            //  }
        }
    }

    void buscarAuto2(String dato, Connection cn) {
        ArrayList<Modelo_Vehiculo> auto = dv.Consultar(dato, cn);
        if (auto.size() > 0) {
            idVehiculo.setText(String.valueOf(auto.get(0).getPlaca()));
            NombreVehiculo.setText(auto.get(0).getMarca());
            gas.setText(auto.get(0).getGas());
            //if(LugarMonto.equals("E")){
            // TraerSaldo(idEmpresa.getText(), idVehiculo.getText(), ser.Fecha("%Y-%m"), cn);       
            // }else{
            TraerSaldoPlomero(idVisitante.getText(), ser.Fecha("%Y-%m"), cn);
            // }
        }
    }

    void buscarAuto3(String dato, Connection cn) {
        ArrayList<Modelo_Vehiculo> auto = dv.Consultar(dato, cn);
        if (auto.size() > 0) {
            idVehiculo.setText(String.valueOf(auto.get(0).getPlaca()));
            NombreVehiculo.setText(auto.get(0).getMarca());
            gas.setText(auto.get(0).getGas());
            //if(LugarMonto.equals("E")){
            // TraerSaldo(idEmpresa.getText(), idVehiculo.getText(), ser.Fecha("%Y-%m"), cn);       
            // }else{
            TraerSaldoLeonel(idVisitante.getText(), ser.Fecha("%Y-%m"), cn);
            // }
        }
    }

    public void restar(double a, double b) {
        Total.setText(String.valueOf(a - b));
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
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        mes2 = new javax.swing.JPanel();
        Buscar_Visita2 = new javax.swing.JButton();
        BuscarVehiculo2 = new javax.swing.JButton();
        mes = new javax.swing.JPanel();
        ADD = new javax.swing.JButton();
        AddVehiculo = new javax.swing.JButton();
        mes1 = new javax.swing.JPanel();
        Guardar = new javax.swing.JButton();
        Guardar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        idVisitante = new javax.swing.JTextField();
        nombreVisitante = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        idVehiculo = new javax.swing.JTextField();
        NombreVehiculo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        gas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        MontoMes = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        saldo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        Total = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        firma = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        admin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        empresa = new javax.swing.JTextField();
        idEmpresa = new javax.swing.JTextField();
        LugarMonto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jTabbedPane1.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        kGradientPanel1.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel1.setStartColor(new java.awt.Color(0, 102, 204));

        mes2.setBackground(new java.awt.Color(0, 204, 204));
        mes2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mes2.setOpaque(false);

        Buscar_Visita2.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Buscar_Visita2.setForeground(new java.awt.Color(255, 255, 255));
        Buscar_Visita2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Find User Male_45px.png"))); // NOI18N
        Buscar_Visita2.setText("Buscar Visitante");
        Buscar_Visita2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Buscar_Visita2.setContentAreaFilled(false);
        Buscar_Visita2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Buscar_Visita2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Buscar_Visita2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Find User Male_45px.png"))); // NOI18N
        Buscar_Visita2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Find User Male_35px.png"))); // NOI18N
        Buscar_Visita2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_Visita2ActionPerformed(evt);
            }
        });

        BuscarVehiculo2.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        BuscarVehiculo2.setForeground(new java.awt.Color(255, 255, 255));
        BuscarVehiculo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Car Rental_45px.png"))); // NOI18N
        BuscarVehiculo2.setText(" Buscar Vehículo");
        BuscarVehiculo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BuscarVehiculo2.setContentAreaFilled(false);
        BuscarVehiculo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BuscarVehiculo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarVehiculo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mes2Layout = new javax.swing.GroupLayout(mes2);
        mes2.setLayout(mes2Layout);
        mes2Layout.setHorizontalGroup(
            mes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mes2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Buscar_Visita2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscarVehiculo2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        mes2Layout.setVerticalGroup(
            mes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mes2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Buscar_Visita2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BuscarVehiculo2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel1.add(mes2);
        mes2.setBounds(30, 80, 180, 130);

        mes.setBackground(new java.awt.Color(0, 204, 204));
        mes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mes.setOpaque(false);

        ADD.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        ADD.setForeground(new java.awt.Color(255, 255, 255));
        ADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Add User Male_45px.png"))); // NOI18N
        ADD.setText("Añadir Visitante");
        ADD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ADD.setContentAreaFilled(false);
        ADD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });

        AddVehiculo.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        AddVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        AddVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Car Badge_45px.png"))); // NOI18N
        AddVehiculo.setText("Añadir Vehículo");
        AddVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        AddVehiculo.setContentAreaFilled(false);
        AddVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mesLayout = new javax.swing.GroupLayout(mes);
        mes.setLayout(mesLayout);
        mesLayout.setHorizontalGroup(
            mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mesLayout.setVerticalGroup(
            mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ADD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        kGradientPanel1.add(mes);
        mes.setBounds(30, 220, 177, 138);

        mes1.setBackground(new java.awt.Color(0, 204, 204));
        mes1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mes1.setOpaque(false);

        Guardar.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Guardar.setForeground(new java.awt.Color(255, 255, 255));
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Save_45px.png"))); // NOI18N
        Guardar.setText("Guardar Vale");
        Guardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Guardar.setContentAreaFilled(false);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        Guardar1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Guardar1.setForeground(new java.awt.Color(255, 255, 255));
        Guardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/broom_45px.png"))); // NOI18N
        Guardar1.setText("Limpiar ");
        Guardar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Guardar1.setContentAreaFilled(false);
        Guardar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mes1Layout = new javax.swing.GroupLayout(mes1);
        mes1.setLayout(mes1Layout);
        mes1Layout.setHorizontalGroup(
            mes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mes1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Guardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        mes1Layout.setVerticalGroup(
            mes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mes1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Guardar1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        kGradientPanel1.add(mes1);
        mes1.setBounds(30, 370, 177, 149);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vale", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Berlin Sans FB", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);

        idVisitante.setEditable(false);
        idVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        idVisitante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idVisitante.setText("ID");
        idVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idVisitanteActionPerformed(evt);
            }
        });

        nombreVisitante.setEditable(false);
        nombreVisitante.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        nombreVisitante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombreVisitante.setText("Nombre");

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Placa:");

        idVehiculo.setEditable(false);
        idVehiculo.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        idVehiculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idVehiculo.setText("jTextField1");

        NombreVehiculo.setEditable(false);
        NombreVehiculo.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        NombreVehiculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NombreVehiculo.setText("jTextField1");

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tipo Gas:");

        gas.setEditable(false);
        gas.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        gas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gas.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Guatemala,");

        fecha.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setText("0000/00/00");

        jLabel15.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ID Visitante:");

        jLabel16.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nombre:");

        jLabel17.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Marca:");

        MontoMes.setEditable(false);
        MontoMes.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        MontoMes.setText("jTextField1");

        jLabel13.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Monto Por mes: ");

        saldo.setEditable(false);
        saldo.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        saldo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saldo.setText("jTextField2");

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Saldo:");

        jLabel11.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Monto:");

        monto.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        monto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monto.setText("jTextField3");
        monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                montoKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("SaldoTotal:");

        Total.setEditable(false);
        Total.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Total.setText("jTextField4");

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Firma");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        firma.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        firma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        firma.setText("Candy Corado");
        firma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firmaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Administrador ");

        admin.setEditable(false);
        admin.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        admin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        admin.setText("Leonel Rodriguez");

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Empresa:");

        empresa.setEditable(false);
        empresa.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        empresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        empresa.setText("jTextField1");

        idEmpresa.setEditable(false);
        idEmpresa.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        idEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idEmpresa.setText("jTextField1");

        LugarMonto.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        LugarMonto.setForeground(new java.awt.Color(0, 102, 204));
        LugarMonto.setText("jTextField1");
        LugarMonto.setBorder(null);
        LugarMonto.setEnabled(false);
        LugarMonto.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(gas, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(idVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(NombreVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(LugarMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(idVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(idEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(empresa)
                                                .addComponent(nombreVisitante, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))))
                                    .addGap(27, 27, 27))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(29, 29, 29)
                                        .addComponent(MontoMes, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(28, 28, 28)
                                                .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jLabel12)
                                            .addGap(37, 37, 37)
                                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha)
                                .addGap(8, 8, 8)))
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel6)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(firma, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(admin, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(fecha)))
                    .addComponent(LugarMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(idVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(idVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(NombreVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(gas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(MontoMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(admin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        idVisitante.getAccessibleContext().setAccessibleName("ID");
        firma.getAccessibleContext().setAccessibleName("Shary");

        kGradientPanel1.add(jPanel2);
        jPanel2.setBounds(240, 0, 420, 550);

        jButton2.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Query_35px.png"))); // NOI18N
        jButton2.setText("Consultar Registros");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(jButton2);
        jButton2.setBounds(30, 10, 180, 50);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vales", new javax.swing.ImageIcon(getClass().getResource("/iconos/Check_45px.png")), jPanel1); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idVisitanteActionPerformed
        // TODO add your handling code here:
        buscarVisitante(idVisitante.getText(), cn);
    }//GEN-LAST:event_idVisitanteActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
        // TODO add your handling code here:
        Registrar_Visitante d = new Registrar_Visitante();
        DeskTop.add(d);
        d.show();
        CentrarInternal(d);
    }//GEN-LAST:event_ADDActionPerformed

    private void Buscar_Visita2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_Visita2ActionPerformed
        // TODO add your handling code here

        this.dispose();
        Consulta_Visitante3 d = new Consulta_Visitante3();
        DeskTop.add(d);
        d.show();
        CentrarInternal(d);

    }//GEN-LAST:event_Buscar_Visita2ActionPerformed

    private void BuscarVehiculo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarVehiculo2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Consulta_Vehiculos d = new Consulta_Vehiculos();
        DeskTop.add(d);
        d.show();
        CentrarInternal(d);
    }//GEN-LAST:event_BuscarVehiculo2ActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        // TODO add your handling code here:
        if (Double.parseDouble(monto.getText()) > Double.parseDouble(saldo.getText())) {
            JOptionPane.showMessageDialog(null, "Saldo No Disponible");
            return;
        } else {
            Guardar(ser.Fecha("%Y-%m-%d %H:%i:%s"), firma.getText(), Escritorio.LblNombre.getText(), admin.getText(), idVisitante.getText(), idVehiculo.getText(), monto.getText(), "Realizado", cn);
            cc.valeReporte();
            cc.tabla(Date.from(Instant.EPOCH), Date.from(Instant.EPOCH), "");
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void AddVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddVehiculoActionPerformed
        // TODO add your handling code here:
        Vehiculo d = new Vehiculo();
        DeskTop.add(d);
        d.show();
        CentrarInternal(d);
    }//GEN-LAST:event_AddVehiculoActionPerformed

    private void Guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar1ActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_Guardar1ActionPerformed

    private void montoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoKeyReleased
        // TODO add your handling code here:  
        if (Double.parseDouble(monto.getText()) > Double.parseDouble(saldo.getText())) {
            JOptionPane.showMessageDialog(null, "Saldo No Disponible");
            return;
        } else {
            restar(Double.parseDouble(saldo.getText()), Double.parseDouble(monto.getText() + ".0"));
        }
    }//GEN-LAST:event_montoKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Consulta_Vales NU = new Consulta_Vales();
        DeskTop.add(NU);
        CentrarInternal(NU);
        NU.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void firmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firmaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JButton AddVehiculo;
    private javax.swing.JButton BuscarVehiculo2;
    private javax.swing.JButton Buscar_Visita2;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Guardar1;
    private javax.swing.JTextField LugarMonto;
    public static javax.swing.JTextField MontoMes;
    public static javax.swing.JTextField NombreVehiculo;
    private javax.swing.JTextField Total;
    private javax.swing.JTextField admin;
    private javax.swing.JTextField empresa;
    private javax.swing.JLabel fecha;
    private javax.swing.JTextField firma;
    public static javax.swing.JTextField gas;
    private javax.swing.JTextField idEmpresa;
    public static javax.swing.JTextField idVehiculo;
    private javax.swing.JTextField idVisitante;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JPanel mes;
    private javax.swing.JPanel mes1;
    private javax.swing.JPanel mes2;
    private javax.swing.JTextField monto;
    private javax.swing.JTextField nombreVisitante;
    public static javax.swing.JTextField saldo;
    // End of variables declaration//GEN-END:variables
}
