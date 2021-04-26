/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

/**
 *
 * @author Zuly Galicia
 */
public class Modelo_Login {
    

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the Correo
     */
    public String getCorreo() {
        return Correo;
    }

    /**
     * @param Correo the Correo to set
     */
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the idRol
     */
    public String getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    /**
     * @return the idPuesto
     */
    public String getIdPuesto() {
        return idPuesto;
    }

    /**
     * @param idPuesto the idPuesto to set
     */
    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Modelo_Login(int idUsuario, String Nombre, String Apellido, String Correo, String Password, String idRol, String idPuesto) {
        this.idUsuario = idUsuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Password = Password;
        this.idRol = idRol;
        this.idPuesto = idPuesto;
    }
    
    private int idUsuario;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Password;
    private String idRol;
    private String idPuesto;
    
}
