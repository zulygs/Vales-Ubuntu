/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empresa;

/**
 *
 * @author Zuly Galicia
 */
public class Modelo_Empresa {

    public Modelo_Empresa(int id_Empresa, String NombreEmpresa, String ubicacion, String Persona, int vales, double monto) {
        this.id_Empresa = id_Empresa;
        this.NombreEmpresa = NombreEmpresa;
        this.ubicacion = ubicacion;
        this.Persona = Persona;
        this.vales = vales;
        this.monto = monto;
    }

    public Modelo_Empresa(int id_Empresa, String NombreEmpresa, String ubicacion, String Persona, int vales, double monto, double saldo) {
        this.id_Empresa = id_Empresa;
        this.NombreEmpresa = NombreEmpresa;
        this.ubicacion = ubicacion;
        this.Persona = Persona;
        this.vales = vales;
        this.monto = monto;
        this.saldo = saldo;
    }

   
    private int id_Empresa;
    private String NombreEmpresa;
    private String ubicacion;
   private String Persona;
     private int vales;
   private double monto;
     private double saldo;

    /**
     * @return the id_Empresa
     */
    public int getId_Empresa() {
        return id_Empresa;
    }

    /**
     * @param id_Empresa the id_Empresa to set
     */
    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    /**
     * @return the NombreEmpresa
     */
    public String getNombreEmpresa() {
        return NombreEmpresa;
    }

    /**
     * @param NombreEmpresa the NombreEmpresa to set
     */
    public void setNombreEmpresa(String NombreEmpresa) {
        this.NombreEmpresa = NombreEmpresa;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the Persona
     */
    public String getPersona() {
        return Persona;
    }

    /**
     * @param Persona the Persona to set
     */
    public void setPersona(String Persona) {
        this.Persona = Persona;
    }

    /**
     * @return the vales
     */
    public int getVales() {
        return vales;
    }

    /**
     * @param vales the vales to set
     */
    public void setVales(int vales) {
        this.vales = vales;
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
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
