/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Zuly Galicia
 */
public class Controlador_Visitante {

    public int Guardar_Visitante(String NombreVisitante, String Apellido, int telefono, double monto, int idUsuario, int idEmpresa, String LugarMonto,Connection cn) {
        int bandera = -1;
        try {
            String Query = "insert into Visitante(Nombre,Apellido,Telefono,Monto,Id_Usuario,Id_Empresa,LugarMonto)value(?,?,?,?,?,?,?)";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.setString(1, NombreVisitante);
            pre.setString(2, Apellido);
            pre.setInt(3, telefono);
            pre.setDouble(4, monto);
            pre.setInt(5, idUsuario);
            pre.setInt(6, idEmpresa);
            pre.setString(7, LugarMonto);
           bandera= pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Guardado !</font></html>");

            pre.close();
        } catch (Exception e) {
            bandera = -1;
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Guardo Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }
        return bandera;
    }

    public void Eliminar_Visitante(String id, Connection cn) {
        try {
            String Query = "delete from Visitante where Id_Visitante='" + id + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Eliminado !</font></html>");

            pre.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Elimino Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }
    }

    public int Editar_Visitante(int Id_Visitante, String NombreVisitante, String Apellido, int telefono, double monto, int idUsuario, int idEmpresa,String LugarMonto, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update Visitante set Nombre='" + NombreVisitante + "',Apellido='" + Apellido + "',Telefono='" + telefono + "',Monto='" + monto + "',Id_Usuario='" + idUsuario + "',Id_Empresa='" + idEmpresa + "',LugarMonto='"+LugarMonto+"' where Id_Visitante='" + Id_Visitante + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado !</font></html>");
            pre.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Edito Consulte A Su Admi !</font></html>");

            e.printStackTrace();

        }
        return bandera;
    }

     public int Editar_Monto(int Empresa,  double monto, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update Visitante set Monto='" + monto + "' where id_Empresa='" + Empresa + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
            pre.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Edito Consulte A Su Admi !</font></html>");

            e.printStackTrace();

        }
        return bandera;
    }
    public ArrayList Consulta(String buscar, Connection cn) {
        ArrayList<Modelo_Visitante> mod = new ArrayList<>();
        try {
            String Query = "select v.id_Visitante,v.nombre,v.Apellido,v.telefono,v.monto,u.id_usuario,e.id_empresa,e.nombreEmpresa,v.LugarMonto from Visitante v "
                    + "join usuario u on(V.id_usuario=u.id_usuario)"
                    + "join empresa e on(v.id_empresa=e.id_empresa)"
                    + "where v.id_visitante='"+buscar +"' or v.Apellido like '%" + buscar + "%' or v.Nombre like '%" + buscar + "%'"
                    + "order by v.Id_Visitante asc";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                mod.add(new Modelo_Visitante(rs.getInt("v.id_visitante"), rs.getString("v.nombre"), rs.getString("apellido"), rs.getInt("telefono"), rs.getDouble("monto"), rs.getInt("id_Usuario"), rs.getInt("id_Empresa"),rs.getString("nombreEmpresa"),rs.getString("LugarMonto")));

            }
            rs.close();
            pre.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mod;

    }
    
     public ArrayList ConsultaVisitante(String buscar, Connection cn) {
        ArrayList<Modelo_Visitante> mod = new ArrayList<>();
        try {
            String Query = "select v.id_Visitante,v.nombre,v.Apellido,v.telefono,v.monto,e.monto,u.id_usuario,e.id_empresa,e.nombreEmpresa,LugarMonto from Visitante v "
                    + "join usuario u on(V.id_usuario=u.id_usuario)"
                    + "join empresa e on(v.id_empresa=e.id_empresa)"
                    + "where v.id_visitante='"+buscar +"' "
                  ;
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                mod.add(new Modelo_Visitante(rs.getInt("v.id_visitante"), rs.getString("v.nombre"), rs.getString("apellido"), rs.getInt("telefono"), rs.getDouble("v.monto"), rs.getInt("id_Usuario"), rs.getInt("id_Empresa"),rs.getString("nombreEmpresa"),rs.getDouble("e.Monto"),rs.getString("lugarmonto")));

            }
            rs.close();
            pre.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mod;

    }
     
       public  boolean comparacion(String Nombre,String Apellido,Connection cn){
    boolean bandera=false;
        try {
            
             String query="select * from visitante where nombre= '"+Nombre+"' and apellido='"+Apellido+"'";
            PreparedStatement pre=cn.prepareStatement(query);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
            bandera=true;
            }
            rs.close();
            pre.close();               
        } catch (Exception e) {
            e.printStackTrace();
             bandera=false;
            
        }
        return bandera;
    
    }
                    
}
