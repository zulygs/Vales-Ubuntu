/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author familia
 */
public class Driver_Vehiculo {

    public int Guardar_Vehiculo(String Placa, String marca, String modelo, int year, String gas, String color, int estado, Connection cn) {
        int bandera = -1;
        try {
            String Query = "insert into vehiculo(Id_Placa,Marca,Modelo,year,Tipo_Gas,Color,Estado) value(?,?,?,?,?,?,?)";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.setString(1, Placa);
            pre.setString(2, marca);
            pre.setString(3, modelo);
            pre.setInt(4, year);
            pre.setString(5, gas);
            pre.setString(6, color);
            pre.setInt(7, estado);
            bandera = pre.executeUpdate();
            pre.close();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='23' color='094293'>¡Guardado!</font></html>");
        } catch (Exception e) {
            bandera = -1;
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Guardo Consulte A Su Admi !</font></html>");
          
            e.printStackTrace();
        }
        return bandera;

    }

    public int editar_Vehiculo(String Placa,String id_placa, String marca, String modelo, int year, String gas, String color, int estado, Connection cn) {
        int bandera = -1;
        try {
            String Query = "update vehiculo set id_placa='"+id_placa+"' , marca='" + marca + "',modelo='" + modelo + "',year='" + year + "',tipo_gas='" + gas + "',color='" + color + "',estado='" + estado + "' where id_placa='" + Placa + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
             JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado!</font></html>");
         
            pre.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡Existen Vales Con Esta Placa!</font></html>");
          
            e.printStackTrace();
        }
        return bandera;
    }

    public void eliminar(String buscar, Connection cn) {
        try {
            String Query = "delete from Vehiculo where id_Placa='" + buscar + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.executeUpdate();
             JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Eliminado!</font></html>");
         
            pre.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Elimino Consulte A Su Admi !</font></html>");
          
            e.printStackTrace();
        }
    }

    
    
    public ArrayList Consultar(String buscar,Connection cn){
    ArrayList<Modelo_Vehiculo> mod=new ArrayList<>();
        try {
            String Query="SELECT * FROM vehiculo where marca  = '"+buscar+"' or Id_Placa LIKE '%"+buscar+"%'";
            PreparedStatement pre=cn.prepareStatement(Query);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
            mod.add(new Modelo_Vehiculo(rs.getString("id_Placa"),rs.getString("marca"), rs.getString("modelo"), rs.getInt("year"), rs.getString("tipo_gas"), rs.getString("color"), rs.getInt("estado")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    return mod;
    }
      
    public ArrayList Consultar2(String buscar,Connection cn){
    ArrayList<Modelo_Vehiculo> mod=new ArrayList<>();
        try {
            String Query="SELECT * FROM vehiculo where marca  = '"+buscar+"' or Id_Placa LIKE '%"+buscar+"%' And estado=1";
            PreparedStatement pre=cn.prepareStatement(Query);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
            mod.add(new Modelo_Vehiculo(rs.getString("id_Placa"),rs.getString("marca"), rs.getString("modelo"), rs.getInt("year"), rs.getString("tipo_gas"), rs.getString("color"), rs.getInt("estado")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    return mod;
    }
    
}
