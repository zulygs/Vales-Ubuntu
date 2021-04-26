/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

/**
 *
 * @author Zuly Galicia
 */
public class Modelo_Usuario {

   

    /**
     * @return the Id_Usuario
     */
    public int getId_Usuario() {
        return Id_Usuario;
    }

    /**
     * @param Id_Usuario the Id_Usuario to set
     */
    public void setId_Usuario(int Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
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
     * @return the Telefono
     */
    public int getTelefono() {
        return Telefono;
    }

    /**
     * @param Telefono the Telefono to set
     */
    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * @return the Rol
     */
    public String getRol() {
        return Rol;
    }

    /**
     * @param Rol the Rol to set
     */
    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    /**
     * @return the Puesto
     */
    public String getPuesto() {
        return Puesto;
    }

    /**
     * @param Puesto the Puesto to set
     */
    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }
 

 
    private int Id_Usuario;
     private String Nombre;
  private String Apellido;
    private String Correo;

    public Modelo_Usuario(int Id_Usuario, String Password) {
        this.Id_Usuario = Id_Usuario;
        this.Password = Password;
    }
  private String Password;
   private int Telefono;
   private String Rol;
     private String Puesto;
     
    public Modelo_Usuario(int Id_Usuario, String Nombre, String Apellido, String Correo, String Password, int Telefono, String Rol, String Puesto) {
        this.Id_Usuario = Id_Usuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Password = Password;
        this.Telefono = Telefono;
        this.Rol = Rol;
        this.Puesto = Puesto;
    }
}
