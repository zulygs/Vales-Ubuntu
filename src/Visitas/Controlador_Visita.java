/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author familia
 */
public class Controlador_Visita {

    public int Guardar_Visita(String fecha, String solicita, String motivo, String observaciones, int estado, int id_Visitante, Connection cn) {
        int bandera = -1;
        try {
            String Query = "insert into visita(fechahora,solicitaa,motivo,observaciones,estado_visita,id_visitante)value(?,?,?,?,?,?)";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.setString(1, fecha);
            pre.setString(2, solicita);
            pre.setString(3, motivo);
            pre.setString(4, observaciones);
            pre.setInt(5, estado);
            pre.setInt(6, id_Visitante);
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

    public int editar(int id_visita, String fecha, String solicita, String motivo, String observaciones, int estado, int id_Visitante, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update visita set fechahora='" + fecha + "',SolicitaA='" + solicita + "',Motivo='" + motivo + "',Observaciones='" + observaciones + "',estado_Visita='" + estado + "',id_visitante='" + id_Visitante + "' where id_Visita='" + id_visita + "'";
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
            String Query = "delete from visita where id_visita='" + buscar + "'";
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
        ArrayList<Modelo_Visita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT v.Id_Visita,v.FechaHora,v.SolicitaA,v.Motivo,v.Observaciones,v.Estado_Visita,Vi.Id_Visitante,Vi.Nombre from visita v "
                    + "join visitante Vi on(v.Id_Visitante=Vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d')";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Visita(rs.getInt("id_visita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado_visita"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ConsultaFecha(String buscar, Connection cn) {
        ArrayList<Modelo_Visita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT v.Id_Visita,v.FechaHora,v.SolicitaA,v.Motivo,v.Observaciones,v.Estado_Visita,Vi.Id_Visitante,Vi.Nombre from visita v "
                    + "join visitante Vi on(v.Id_Visitante=Vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d')='" + buscar + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Visita(rs.getInt("id_visita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado_visita"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ConsultaRango(String buscar, String buscar2, Connection cn) {
        ArrayList<Modelo_Visita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT v.Id_Visita,v.FechaHora,v.SolicitaA,v.Motivo,v.Observaciones,v.Estado_Visita,Vi.Id_Visitante,Vi.Nombre from visita v "
                    + "join visitante Vi on(v.Id_Visitante=Vi.Id_Visitante)"
                    + "where DATE_FORMAT(FechaHora,'%Y-%m-%d') BETWEEN '" + buscar + "' AND '" + buscar2 + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Visita(rs.getInt("id_visita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado_visita"), rs.getInt("id_visitante"), rs.getString("Nombre")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList ObtenerDatos(String buscar, Connection cn) {
        ArrayList<Modelo_Visita> modelo = new ArrayList<>();
        try {
            String Query = "SELECT C.id_visita,C.FechaHora,C.SolicitaA,C.Motivo,C.Observaciones,C.Estado_visita,Vi.Id_Visitante from Visita C "
                    + "join visitante Vi on(C.Id_Visitante=Vi.Id_Visitante)"
                    + "where id_Visita='" + buscar + "' or Vi.id_Visitante like '%" + buscar + "%'";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                modelo.add(new Modelo_Visita(rs.getInt("id_visita"), rs.getString("fechahora"), rs.getString("solicitaa"), rs.getString("motivo"), rs.getString("observaciones"), rs.getInt("estado_visita"), rs.getInt("id_visitante")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }
}
