/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

import Usuario.Modelo_Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Zuly Galicia
 */
public class Driver_Login {

    public ArrayList Comparacion(String Correo, String Password, Connection cn) {
        ArrayList<Modelo_Login> mod = new ArrayList<>();
        try {
            String Query = "SELECT Id_Usuario,Nombre,Apellido,Correo,Password,Rol,Puesto FROM usuario u";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                mod.add(new Modelo_Login(rs.getInt("Id_Usuario"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("Correo"), rs.getString("Password"), rs.getString("Rol"), rs.getString("puesto")));

            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mod;

    }

    public ArrayList Comparacionpass(String id, Connection cn) {
        ArrayList<Modelo_Usuario> mod = new ArrayList<>();
        try {
            String Query = "SELECT id_usuario,Password  FROM usuario u "
                    + "where id_usuario='" + id + "'";
            PreparedStatement pre = cn.prepareStatement(Query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                mod.add(new Modelo_Usuario(rs.getInt("Id_Usuario"), rs.getString("password")));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mod;

    }
}
