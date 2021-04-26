/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author familia
 */
public class Driver_Usuario {

    public int GuardarUsuario(String Nombre, String Apellido, String Correo, String Password, int Telefono, String Rol, String Puesto, Connection cn) {
        int bandera = -1;
        try {
            String Query = "INSERT INTO usuario(Nombre,Apellido,Correo,Password,Telefono,Rol,Puesto) value(?,?,?,?,?,?,?)";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.setString(1, Nombre);
            pre.setString(2, Apellido);
            pre.setString(3, Correo);
            pre.setString(4, Password);
            pre.setInt(5, Telefono);
            pre.setString(6, Rol);
            pre.setString(7, Puesto);

            bandera = pre.executeUpdate();
         JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Guardado!</font></html>");
            
            pre.close();

        } catch (Exception e) {
            bandera = -1;
         JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Guardo Consulte A Su Admi !</font></html>");
           
            e.printStackTrace();
        }

        return bandera;
    }

  
    
     
    
    
    
    

    public void Eliminar_Usuario(String id_Usuario, Connection cn) {
        try {
            String Query = "delete from usuario where Id_Usuario='" + id_Usuario + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            pre.executeUpdate();
            pre.close();
         JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Eliminado!</font></html>");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Elimino Consulte A Su Admi !</font></html>");
           
            e.printStackTrace();
        }

    }
 public int UpdateUser(int idUsuario, String Nombre, String Apellido, String Correo,  int Telefono,String Rol, String Puesto, Connection cn) {
        int bandera = -1;
        try {
            String Query = "UPDATE usuario set Nombre='" + Nombre + "',Apellido='" + Apellido + "',Correo='" + Correo + "',Telefono='" + Telefono + "',Rol='" + Rol + "',Puesto='" + Puesto + "' WHERE Id_Usuario='" + idUsuario + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado!</font></html>");
            pre.close();
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Edito Consulte A Su Admi !</font></html>");
          
            e.printStackTrace();
        }
        return bandera;
    }
 public int UpdatePass(String idUsuario,  String Pass, Connection cn) {
        int bandera = -1;
        try {
            String Query = "UPDATE usuario set password='"+Pass+"' WHERE Id_Usuario='" + idUsuario + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            bandera = pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='25' color='094293'>¡Editado!</font></html>");
            pre.close();
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "<html><font face='Berlin Sans FB' size='15' color='red'>¡NO Se Edito Consulte A Su Admi !</font></html>");
          
            e.printStackTrace();
        }
        return bandera;
    }

    
   public boolean Comparar_Existente(Connection cn){
   boolean bandera=false;
       try {
           String Query="select * from  usuario where Rol='Administrador'";
           PreparedStatement pre=cn.prepareStatement(Query);
           ResultSet rs=pre.executeQuery();
           if(rs.next()){
           bandera=true;
           }
           rs.close();
           pre.close();
       } catch (Exception e) {
           bandera=false;
           e.printStackTrace();
       }
   
   return bandera;
   }

   
   
   public ArrayList Consulta_Usuario(String buscar,Connection cn){
    ArrayList<Modelo_Usuario> array=new ArrayList<>();
        try {
            
            String query="select u.Id_Usuario,u.Nombre,u.Apellido,u.Correo,u.Password,u.Telefono,u.Rol,u.Puesto from usuario u "
                    +"where u.Id_Usuario='"+buscar+"'or  Apellido like '%" + buscar + "%' or  Nombre like '%" + buscar + "%'";
            PreparedStatement prepa=cn.prepareStatement(query);
      ResultSet res=prepa.executeQuery();
      while(res.next()){
         array.add(new Modelo_Usuario(res.getInt("Id_Usuario"), res.getString("Nombre"), res.getString("Apellido"), res.getString("Correo"),res.getString("Password"),res.getInt("Telefono"), res.getString("Rol"),  res.getString("Puesto")));
  }
      
      res.close();
      prepa.close();
              } catch (Exception e) {
                  e.printStackTrace();
        }
        return array;
    }

}
