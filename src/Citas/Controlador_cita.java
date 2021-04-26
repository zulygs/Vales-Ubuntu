/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Zuly Galicia
 */
public class Controlador_cita {

    public int Guardar_Cita(String fecha, String solicita, String motivo, String observaciones, int Estado, int asistencia, int id_visitante, Connection cn) {
        int bandera = -1;// es una variable que no contiene nada de informaciones útliles
        try {
            String Query = "insert into Cita(fechahora,solicitaa,motivo,observaciones,estado,Asistencia,id_visitante)value(?,?,?,?,?,?,?)";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.setString(1, fecha);
            pre.setString(2, solicita);
            pre.setString(3, motivo);
            pre.setString(4, observaciones);
            pre.setInt(5, Estado);
            pre.setInt(6, asistencia);
            pre.setInt(7, id_visitante);
            bandera = pre.executeUpdate();
             JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Cita Guardada!</font></html>");
            pre.close();
        } catch (Exception e) {
            bandera = -1;
            e.printStackTrace();
        }
        return bandera;
    }

    public int editar(int id_Cita, String fecha, String solicita, String motivo, String observaciones, int Estado, int asistencia, int id_visitante, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update cita set fechahora='" + fecha + "',SolicitaA='" + solicita + "',Motivo='" + motivo + "',Observaciones='" + observaciones + "',estado='" + Estado + "',asistencia='" + asistencia + "',id_visitante='" + id_visitante + "' where id_Cita='" + id_Cita + "'";
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
            String Query = "delete from Cita where id_Cita='" + buscar + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Eliminado!</font></html>");
            pre.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='094293'>¡NO Elimino Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }

    }

    public ArrayList ConsultaHoy(Connection cn) {
        ArrayList<Modelo_Cita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.Id_Cita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado,C.Asistencia,Vi.Id_Visitante,Vi.Nombre from cita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d')"
                    + "ORDER BY Id_Cita asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Cita(rs.getInt("id_cita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado"), rs.getInt("asistencia"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ConsultaFecha(String buscar, Connection cn) {
        ArrayList<Modelo_Cita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.Id_Cita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado,C.Asistencia,Vi.Id_Visitante,Vi.Nombre from cita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d')='" + buscar + "'"
                    + "ORDER BY Id_Cita asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Cita(rs.getInt("id_cita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado"), rs.getInt("asistencia"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ConsultaRango(String buscar, String buscar2, Connection cn) {
        ArrayList<Modelo_Cita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.Id_Cita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado,C.Asistencia,Vi.Id_Visitante,Vi.Nombre from cita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d') BETWEEN '" + buscar + "' AND '" + buscar2 + "'"
                    + "ORDER BY Id_Cita asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Cita(rs.getInt("id_cita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado"), rs.getInt("asistencia"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ConsultaRealizadas(Connection cn) {
        ArrayList<Modelo_Cita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.Id_Cita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado,C.Asistencia,Vi.Id_Visitante,Vi.Nombre from cita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante) "
                    + "where C.estado=1 "
                    + "ORDER BY Id_Cita asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Cita(rs.getInt("id_cita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado"), rs.getInt("asistencia"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList Proceso(Connection cn) {
        ArrayList<Modelo_Cita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.Id_Cita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado,C.Asistencia,Vi.Id_Visitante,Vi.Nombre from cita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante) "
                    + "where C.estado=0 "
                    + "ORDER BY Id_Cita asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Cita(rs.getInt("id_cita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado"), rs.getInt("asistencia"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList Canceladas(Connection cn) {
        ArrayList<Modelo_Cita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.Id_Cita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado,C.Asistencia,Vi.Id_Visitante,Vi.Nombre from cita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante) "
                    + "where C.estado=3 "
                    + "ORDER BY Id_Cita asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Cita(rs.getInt("id_cita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado"), rs.getInt("asistencia"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList Pendientes(Connection cn) {
        ArrayList<Modelo_Cita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.Id_Cita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado,C.Asistencia,Vi.Id_Visitante,Vi.Nombre from cita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante) "
                    + "where C.estado=2 "
                    + "ORDER BY Id_Cita asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Cita(rs.getInt("id_cita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado"), rs.getInt("asistencia"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList COnsulta_General(String buscar, Connection cn) {
        ArrayList<Modelo_Cita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.Id_Cita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado,C.Asistencia,Vi.Id_Visitante,Vi.Nombre from cita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante)"
                    + "where id_cita like '%" + buscar + "%' or Vi.Nombre like '%" + buscar + "%' "
                    + "ORDER BY Id_Cita asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Cita(rs.getInt("id_cita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado"), rs.getInt("asistencia"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

}
