/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Zuly Galicia
 */
public class Controlador_Empresa {

    public int Guardar_empresa(String NombreEmpresa, String ubicacion, String Persona, int vales, double monto, Connection cn) {
        int bandera = -1;
        try {
            String Query = "insert into empresa(NombreEmpresa,Ubicacion,Persona,Vales_Mes,Monto) value(?,?,?,?,?)";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.setString(1, NombreEmpresa);
            pre.setString(2, ubicacion);
            pre.setString(3, Persona);
            pre.setInt(4, vales);
            pre.setDouble(5, monto);
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Guardado!</font></html>");

            pre.close();
        } catch (Exception e) {
            bandera = -1;
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Guardo Consulte A Su Admi !</font></html>");

            //   e.printStackTrace();
        }
        return bandera;

    }

    public void Eliminar_Empresa(String Id, Connection cn) {
        try {
            String Query = "delete from empresa where Id_Empresa='" + Id + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Eliminado!</font></html>");

            pre.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Edito Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }

    }

    public int Editar_Empresa(int id_Empresa, String NombreEmpresa, String ubicacion, String Persona, int vales, double monto, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update empresa set NombreEmpresa='" + NombreEmpresa + "', Ubicacion='" + ubicacion + "',Persona='" + Persona + "',Vales_Mes='" + vales + "',Monto='" + monto + "' where Id_Empresa='" + id_Empresa + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado!</font></html>");

            pre.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Elimino Consulte A Su Admi !</font></html>");

            e.printStackTrace();
        }
        return bandera;
    }

    boolean comparar_Existente(int id, Connection cn) {
        boolean bandera = false;
        try {
            String Query = "select * from empresa where Id_Empresa='" + id + "' ";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                bandera = true;
            }
        } catch (Exception e) {
            bandera = false;
        }
        return bandera;

    }

  
    public ArrayList Consulta_Empresa2(String buscar, Connection cn) {
        ArrayList<Modelo_Empresa> array = new ArrayList<>();
        try {

            String query = "select * from empresa e "
                    + "where e.Id_Empresa =  '" + buscar + "' ";
            PreparedStatement pre = cn.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                array.add(new Modelo_Empresa(rs.getInt("Id_Empresa"), rs.getString("NombreEmpresa"), rs.getString("Ubicacion"), rs.getString("Persona"), rs.getInt("vales_mes"), rs.getDouble("Monto")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    
       public ArrayList Consulta_( Connection cn) {
        ArrayList<Modelo_Empresa> array = new ArrayList<>();
        try {

            String query = "select * from empresa e "
                    + "where e.id_empresa  ";
            PreparedStatement pre = cn.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                array.add(new Modelo_Empresa(rs.getInt("Id_Empresa"), rs.getString("NombreEmpresa"), rs.getString("Ubicacion"), rs.getString("Persona"), rs.getInt("vales_mes"), rs.getDouble("Monto")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public ArrayList ConsultaSaldo(String em,Connection cn) {
        ArrayList<Modelo_Empresa> array = new ArrayList<>();
        try {

            String query = "SELECT e.Id_Empresa,e.NombreEmpresa,e.Ubicacion,e.persona,e.vales_mes,e.Monto from empresa e "
                    + "where e.NombreEmpresa like '%"+em+"%' ";
            PreparedStatement pre = cn.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                array.add(new Modelo_Empresa(rs.getInt("Id_Empresa"), rs.getString("NombreEmpresa"), rs.getString("Ubicacion"), rs.getString("Persona"), rs.getInt("vales_mes"), rs.getDouble("Monto")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public ArrayList ConsultaSaldoVale(String buscar, String Fecha, Connection cn) {
        ArrayList<Modelo_Empresa> array = new ArrayList<>();
        try {

            String query = "select  v.Id_Empresa,e.NombreEmpresa,e.Ubicacion,e.persona,e.vales_mes,e.Monto,sum(e.Monto-va.Monto) as saldo " +
"                    from visitante v " +
"                    join vales va    on v.Id_Visitante=va.Id_Visitante " +
"                   join empresa e on v.Id_Empresa=e.Id_Empresa " +
"                   where v.id_empresa='"+buscar+"' and  DATE_FORMAT(va.FechaHora,'%Y-%m-%d') BETWEEN '"+Fecha+"-01' And '"+Fecha+"-31' " ;
            PreparedStatement pre = cn.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                array.add(new Modelo_Empresa(rs.getInt("v.Id_Empresa"), rs.getString("NombreEmpresa"), rs.getString("Ubicacion"), rs.getString("Persona"), rs.getInt("vales_mes"), rs.getDouble("Monto"), rs.getDouble("saldo")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public double   ConsultaSaldoValeSoloValorVales(String buscar, String Fecha, Connection cn) {
        double totalVales =0.00;
       // ArrayList<Modelo_Empresa> array = new ArrayList<>();
        try {

            String query = "select  sum(va.Monto) as saldo " +
"                    from visitante v " +
"                    join vales va    on v.Id_Visitante=va.Id_Visitante " +
"                   join empresa e on v.Id_Empresa=e.Id_Empresa " +
"                   where v.id_empresa='"+buscar+"' and (va.estado='Realizado' and DATE_FORMAT(va.FechaHora,'%Y-%m-%d') BETWEEN '"+Fecha+"-01' And '"+Fecha+"-31' )" ;
            try (PreparedStatement pre = cn.prepareStatement(query);
                    ResultSet rs = pre.executeQuery()) {
                if (rs.next())
                    totalVales=  rs.getDouble("saldo");
                
                    rs.close();
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return totalVales;
    }

    
      public double   ConsultaMonto( Connection cn) {
        double totalVales =0.00;
   
        try {

            String query = "select Monto " +
"                    from empresa e " +
"                   where e.id_empresa=1 " ;
            try (PreparedStatement pre = cn.prepareStatement(query);
                    ResultSet rs = pre.executeQuery()) {
                if (rs.next())
                    totalVales=  rs.getDouble("Monto");
                
                    rs.close();
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return totalVales;
    }

    
    /*
     select e.Id_Empresa,e.NombreEmpresa,e.Ubicacion,e.Monto,(e.Monto-(select sum(Monto) from vales where vales.Id_Visitante=v.Id_Visitante))  as saldo 
     from empresa e
     join visitante v
     on e.Id_Empresa = v.Id_Empresa

     */
}
