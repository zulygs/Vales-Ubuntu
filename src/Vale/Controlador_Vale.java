/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Zuly Galicia
 */
public class Controlador_Vale {

    public int Guardar_Vale(String fecha, String firma, String despachador, String Admin, int idVisitante, String Placa, double Monto, String Estado, Connection cn) {
        int bandera = -1;// es una variable que no contiene nada de informaciones útliles
        try {
            String Query = "insert into Vales(fechahora,firma,despachador,administrador,id_visitante,id_placa,monto,estado)value(?,?,?,?,?,?,?,?)";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.setString(1, fecha);
            pre.setString(2, firma);
            pre.setString(3, despachador);
            pre.setString(4, Admin);
            pre.setInt(5, idVisitante);
            pre.setString(6, Placa);
            pre.setDouble(7, Monto);
            pre.setString(8, Estado);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Guardado!</font></html>");

            pre.close();
        } catch (Exception e) {
            bandera = -1;
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='094293'>¡NO Guardo Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }
        return bandera;
    }

    public int Guardar_ValeAnulados(int id_vale, String fecha, String despachador, int idVisitante, String Placa, double Monto, String Estado, Connection cn) {
        int bandera = -1;// es una variable que no contiene nada de informaciones útliles
        try {
            String Query = "insert into anulados(id_vale ,fechahora,despachador,id_visitante,id_placa,monto,estado)value(?,?,?,?,?,?,?)";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.setInt(1, id_vale);
            pre.setString(2, fecha);
            pre.setString(3, despachador);
            pre.setInt(4, idVisitante);
            pre.setString(5, Placa);
            pre.setDouble(6, Monto);
            pre.setString(7, Estado);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Anulado!</font></html>");

            pre.close();
        } catch (Exception e) {
            bandera = -1;
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='094293'>¡NO Guardo Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }
        return bandera;
    }

    public int editar(int id_Vale, String fecha, String firma, String despachador, String Admin, int idVisitante, String Placa, double Monto, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update Anulados set fechahora='" + fecha + "',firma='" + firma + "',despachador='" + despachador + "',administrador='" + Admin + "',id_visitante='" + idVisitante + "',id_placa='" + Placa + "',monto='" + Monto + "' where id_vale='" + id_Vale + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado!</font></html>");

            pre.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='094293'>¡NO Edito Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }
        return bandera;

    }

    public int editar_Monto_Empresa(int id_Empresa, double Nuevo_Monto, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update empresa set monto='" + Nuevo_Monto + "'"
                    + "where id_Empresa='" + id_Empresa + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado!</font></html>");

            pre.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='094293'>¡NO Edito Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }
        return bandera;

    }

    public int update_estado_vale(String id_Visitante, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update vales set estado='Anulado' "
                    + "where id_vale='" + id_Visitante + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado!</font></html>");

            pre.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='094293'>¡NO Edito Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }
        return bandera;

    }

    public void eliminar(String buscar, Connection cn) {
        try {
            String Query = "delete from vales where id_vale='" + buscar + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Eliminado!</font></html>");

            pre.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='094293'>¡NO Elimino Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }

    }

    public ArrayList fecha(String buscar, String estado, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "select v.Id_Vale,v.FechaHora,Firma,v.despachador,Administrador,vi.Id_Visitante,vi.Nombre,v.Id_Placa,ve.Marca,v.Monto,vi.Id_Empresa,(select nombreEmpresa from empresa e where vi.Id_Empresa=e.Id_Empresa)'Empresa',v.estado from vales v "
                    + "join vehiculo ve on(v.Id_Placa=ve.Id_Placa)"
                    + "join visitante vi on(v.Id_Visitante=vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d')='" + buscar + "' AND v.estado like '%" + estado + "%' ORDER BY v.Id_Vale desc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getInt("id_vale"), rs.getString("fechahora"), rs.getString("firma"), rs.getString("Despachador"), rs.getString("administrador"), rs.getInt("id_visitante"), rs.getString("Nombre"), rs.getString("id_placa"), rs.getString("marca"), rs.getDouble("monto"), rs.getString("empresa"), rs.getString("estado"), rs.getInt("id_empresa")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ConsultaGeneral(String buscar, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "SELECT v.Id_Vale,v.FechaHora,v.Firma,v.Despachador,v.Administrador,vi.Id_Visitante,vi.Nombre,p.Id_Placa,p.Marca,v.Monto,vi.id_empresa,(select nombreEmpresa from empresa e where vi.id_empresa=e.id_Empresa)'empresa',v.estado from vales v "
                    + "join visitante vi on(v.Id_Visitante=vi.Id_Visitante)"
                    + "join vehiculo p on(v.Id_Placa=p.Id_Placa)"
                    + "where v.Id_Vale='%" + buscar + "%' or vi.id_visitante like '%" + buscar + "%'   ORDER BY Id_Vale desc ";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getInt("id_vale"), rs.getString("fechahora"), rs.getString("firma"), rs.getString("Despachador"), rs.getString("administrador"), rs.getInt("id_visitante"), rs.getString("Nombre"), rs.getString("id_placa"), rs.getString("marca"), rs.getDouble("monto"), rs.getString("empresa"), rs.getString("estado"), rs.getInt("id_empresa")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ConsultaAnulados(String buscar, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "select v.Id_Vale,v.FechaHora,v.Despachador,v.Id_Placa,v.id_visitante,(select nombre from visitante vi where v.Id_Visitante=vi.Id_Visitante)'nombre',v.Id_Placa,v.Monto,v.estado FROM anulados v  "
                    + "                     where DATE_FORMAT(FechaHora,'%Y-%m')='" + buscar + "'  ORDER BY Id_Vale desc ";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getInt("id_vale"), rs.getString("fechahora"), rs.getString("Despachador"), rs.getInt("id_visitante"), rs.getString("Nombre"), rs.getString("id_placa"), rs.getDouble("monto"), rs.getString("estado")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList Saldo(String empresa, String b, String buscar, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "SELECT e.id_empresa,e.monto,(e.Monto - SUM(va.monto))'Saldo',v.LugarMonto from visitante v "
                    + "join empresa e on(v.Id_Empresa=e.Id_Empresa)"
                    + "join vales va on(v.Id_Visitante=va.Id_Visitante)"
                    + "where e.Id_Empresa=" + empresa + "  AND ( va.estado='Realizado' AND (Id_Placa='" + b + "' And (DATE_FORMAT(FechaHora,'%Y-%m-%d') BETWEEN '" + buscar + "-01' And '" + buscar + "-31')))";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getDouble("e.monto"), rs.getDouble("saldo")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList SaldoPlomero(String IdVisitante, String Fecha, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "select v.Id_Empresa,v.Monto,v.LugarMonto,"
                    + "IFNULL( (v.Monto - (SELECT SUM(va.monto) from vales va where v.Id_Visitante=va.Id_Visitante and (DATE_FORMAT(va.FechaHora,'%Y-%m-%d') BETWEEN '" + Fecha + "-01' And '" + Fecha + "-31' and (v.LugarMonto='V' And (va.Estado='Realizado' and v.Id_Visitante='" + IdVisitante + "'))))),v.Monto)as 'Saldo' "
                    + "from visitante v "
                    + "where v.Id_Visitante='" + IdVisitante + "' and LugarMonto='V'";

            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getDouble("monto"), rs.getDouble("saldo")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList SaldoLeonel(String IdVisitante, String Fecha, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "select v.Id_Empresa,v.Monto,v.LugarMonto,"
                    + "(v.Monto - (SELECT SUM(va.monto) from vales va where v.Id_Visitante=va.Id_Visitante and (DATE_FORMAT(va.FechaHora,'%Y-%m-%d') BETWEEN '"+Fecha+"-01' And '"+Fecha+"-31' and (v.LugarMonto='e' And (va.Estado='Realizado' and v.Id_Visitante='"+IdVisitante+"')))))'Saldo' "
                    + "from visitante v "
                    + "where v.Id_Visitante='"+IdVisitante+"' and LugarMonto='v'";

            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getDouble("monto"), rs.getDouble("saldo")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList Hoy(String estado, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "select v.Id_Vale,v.FechaHora,Firma,v.despachador,Administrador,vi.Id_Visitante,vi.Nombre,v.Id_Placa,ve.Marca,v.Monto,vi.id_empresa,(select nombreEmpresa from empresa e where vi.id_empresa=e.id_Empresa)'empresa',v.estado from vales v "
                    + "join vehiculo ve on(v.Id_Placa=ve.Id_Placa)"
                    + "join visitante vi on(v.Id_Visitante=vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d') ORDER BY v.Id_Vale desc ";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getInt("id_vale"), rs.getString("fechahora"), rs.getString("firma"), rs.getString("Despachador"), rs.getString("administrador"), rs.getInt("id_visitante"), rs.getString("Nombre"), rs.getString("id_placa"), rs.getString("marca"), rs.getDouble("monto"), rs.getString("empresa"), rs.getString("estado"), rs.getInt("id_empresa")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ConsultEmpresa(String buscar, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "select id_empresa from visitante where id_Visitante='" + buscar + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getInt("id_empresa")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public double Monto_Visitante(String buscar, Connection cn) {
        double Monto = 0.00;
        try {

            String query = "select monto from visitante v"
                    + "where id_visitante='" + buscar + "' ";
            try (PreparedStatement pre = cn.prepareStatement(query);
                    ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    Monto = rs.getDouble("monto");
                }

                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Monto;
    }

    public ArrayList Rango(String buscar, String buscar2, String estado, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "select v.Id_Vale,v.FechaHora,Firma, v.Despachador,Administrador,vi.Id_Visitante,vi.Nombre,v.Id_Placa,ve.Marca,v.Monto,vi.Id_Empresa,(select nombreEmpresa from empresa e where vi.id_empresa=e.id_Empresa)'empresa',v.estado from vales v "
                    + "join vehiculo ve on(v.Id_Placa=ve.Id_Placa)"
                    + "join visitante vi on(v.Id_Visitante=vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d') between '" + buscar + "' AND '" + buscar2 + "' and v.estado like '%" + estado + "%' ORDER BY v.Id_Vale desc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getInt("id_vale"), rs.getString("fechahora"), rs.getString("firma"), rs.getString("Despachador"), rs.getString("administrador"), rs.getInt("id_visitante"), rs.getString("Nombre"), rs.getString("id_placa"), rs.getString("marca"), rs.getDouble("monto"), rs.getString("empresa"), rs.getString("estado"), rs.getInt("id_empresa")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList mes(String buscar, String estado, Connection cn) {
        ArrayList<Modelo_Vale> modelo = new ArrayList<>();
        try {
            String Query = "select v.Id_Vale,v.FechaHora,Firma,v.despachador,Administrador,vi.Id_Visitante,vi.Nombre,v.Id_Placa,ve.Marca,v.Monto,vi.id_empresa,(select nombreEmpresa from empresa e where vi.id_empresa=e.id_Empresa)'empresa',v.estado from vales v "
                    + "                   join vehiculo ve on(v.Id_Placa=ve.Id_Placa) "
                    + "                   join visitante vi on(v.Id_Visitante=vi.Id_Visitante) "
                    + "                   where DATE_FORMAT(FechaHora,'%Y-%m')='" + buscar + "' and v.estado like '%" + estado + "%' ORDER BY v.Id_Vale desc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Vale(rs.getInt("id_vale"), rs.getString("fechahora"), rs.getString("firma"), rs.getString("Despachador"), rs.getString("administrador"), rs.getInt("id_visitante"), rs.getString("Nombre"), rs.getString("id_placa"), rs.getString("marca"), rs.getDouble("monto"), rs.getString("empresa"), rs.getString("estado"), rs.getInt("id_empresa")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

}
