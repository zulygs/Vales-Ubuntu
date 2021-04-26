/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitante;

/**
 *
 * @author Zuly Galicia
 */
public class Modelo_Visitante {

    /**
     * @return the montoEmpresa
     */
    public double getMontoEmpresa() {
        return montoEmpresa;
    }

    /**
     * @param montoEmpresa the montoEmpresa to set
     */
    public void setMontoEmpresa(double montoEmpresa) {
        this.montoEmpresa = montoEmpresa;
    }

    public Modelo_Visitante(int Id_Visitante, String NombreVisitante, String Apellido, int telefono, double monto, int idUsuario, int idEmpresa,String empresa,String LugarMonto) {
        this.Id_Visitante = Id_Visitante;
        this.NombreVisitante = NombreVisitante;
        this.Apellido = Apellido;
        this.telefono = telefono;
        this.monto = monto;
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
        this.empresa=empresa;
        this.LugarMonto=LugarMonto;
    }
    private int Id_Visitante;
    private String NombreVisitante;
    private String Apellido;
    private int telefono;
    private double monto;
    private int idUsuario;
    private int idEmpresa;
    private String empresa;
    private double montoEmpresa;

    public Modelo_Visitante(int Id_Visitante, String NombreVisitante, String Apellido, int telefono, double monto, int idUsuario, int idEmpresa, String empresa, double montoEmpresa,String LugarMonto) {
        this.Id_Visitante = Id_Visitante;
        this.NombreVisitante = NombreVisitante;
        this.Apellido = Apellido;
        this.telefono = telefono;
        this.monto = monto;
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
        this.empresa = empresa;
        this.montoEmpresa = montoEmpresa;
        this.LugarMonto=LugarMonto;
    }
    private String LugarMonto;

    /**
     * @return the Id_Visitante
     */
    public int getId_Visitante() {
        return Id_Visitante;
    }

    /**
     * @param Id_Visitante the Id_Visitante to set
     */
    public void setId_Visitante(int Id_Visitante) {
        this.Id_Visitante = Id_Visitante;
    }

    /**
     * @return the NombreVisitante
     */
    public String getNombreVisitante() {
        return NombreVisitante;
    }

    /**
     * @param NombreVisitante the NombreVisitante to set
     */
    public void setNombreVisitante(String NombreVisitante) {
        this.NombreVisitante = NombreVisitante;
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
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

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
     * @return the idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the LugarMonto
     */
    public String getLugarMonto() {
        return LugarMonto;
    }

    /**
     * @param LugarMonto the LugarMonto to set
     */
    public void setLugarMonto(String LugarMonto) {
        this.LugarMonto = LugarMonto;
    }

}
