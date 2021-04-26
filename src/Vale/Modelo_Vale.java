/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vale;

/**
 *
 * @author familia
 */
public class Modelo_Vale {

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

    public Modelo_Vale(int id_Vale, String fecha, String firma, String despachador, String Admin, int idVisitante, String NombreVisitante, String Placa, String Carro, double Monto,int idEmpresa,String Estado) {
        this.id_Vale = id_Vale;
        this.fecha = fecha;
        this.firma = firma;
        this.despachador = despachador;
        this.Admin = Admin;
        this.idVisitante = idVisitante;
        this.NombreVisitante = NombreVisitante;
        this.Placa = Placa;
        this.Carro = Carro;
        this.Monto = Monto;
         this.idEmpresa = idEmpresa;
        this.Estado=Estado;
        
    }

    public Modelo_Vale(double Monto, double saldo) {
        this.Monto = Monto;
        this.saldo = saldo;
    }

  /*  public Modelo_Vale(int id_Vale, String fecha, String firma, String despachador, String Admin, int idVisitante, String NombreVisitante, String Placa, String Carro, double Monto, double saldo, String Estado) {
        this.id_Vale = id_Vale;
        this.fecha = fecha;
        this.firma = firma;
        this.despachador = despachador;
        this.Admin = Admin;
        this.idVisitante = idVisitante;
        this.NombreVisitante = NombreVisitante;
        this.Placa = Placa;
        this.Carro = Carro;
        this.Monto = Monto;
        this.saldo = saldo;
        this.Estado = Estado;
    }*/
    
    public Modelo_Vale(int id_Vale, String fecha,  String despachador,  int idVisitante, String NombreVisitante, String Placa,  double Monto, String Estado) {
        this.id_Vale = id_Vale;
        this.fecha = fecha;
        this.despachador = despachador;
        this.idVisitante = idVisitante;
        this.NombreVisitante = NombreVisitante;
        this.Placa = Placa;
        this.Monto = Monto;
        this.saldo = saldo;
        this.Estado = Estado;
                
                
    }

    public Modelo_Vale(int id_Vale, String fecha, String firma, String despachador, String Admin, int idVisitante, String NombreVisitante, String Placa, String Carro, double Monto, String NombreEmpresa,  String Estado, int idEmpresa) {
        this.id_Vale = id_Vale;
        this.fecha = fecha;
        this.firma = firma;
        this.despachador = despachador;
        this.Admin = Admin;
        this.idVisitante = idVisitante;
        this.NombreVisitante = NombreVisitante;
        this.Placa = Placa;
        this.Carro = Carro;
        this.Monto = Monto;
        this.NombreEmpresa = NombreEmpresa;
       
        this.Estado = Estado;
        this.idEmpresa = idEmpresa;
    }



    private int id_Vale;
     private String fecha;
      private String firma;
     private String despachador;
     private String Admin;
      private int idVisitante;
     private String NombreVisitante;
    private String Placa;
     private String Carro;
   private double Monto;
   private String NombreEmpresa;

    public Modelo_Vale(double Monto) {
        this.Monto = Monto;
    }
   private double saldo;
   private String Estado;
   private int idEmpresa;

    public Modelo_Vale(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
  
  

    /**
     * @return the id_Vale
     */
    public int getId_Vale() {
        return id_Vale;
    }

    /**
     * @param id_Vale the id_Vale to set
     */
    public void setId_Vale(int id_Vale) {
        this.id_Vale = id_Vale;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the firma
     */
    public String getFirma() {
        return firma;
    }

    /**
     * @param firma the firma to set
     */
    public void setFirma(String firma) {
        this.firma = firma;
    }

    /**
     * @return the despachador
     */
    public String getDespachador() {
        return despachador;
    }

    /**
     * @param despachador the despachador to set
     */
    public void setDespachador(String despachador) {
        this.despachador = despachador;
    }

    /**
     * @return the Admin
     */
    public String getAdmin() {
        return Admin;
    }

    /**
     * @param Admin the Admin to set
     */
    public void setAdmin(String Admin) {
        this.Admin = Admin;
    }

    /**
     * @return the idVisitante
     */
    public int getIdVisitante() {
        return idVisitante;
    }

    /**
     * @param idVisitante the idVisitante to set
     */
    public void setIdVisitante(int idVisitante) {
        this.idVisitante = idVisitante;
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
     * @return the Placa
     */
    public String getPlaca() {
        return Placa;
    }

    /**
     * @param Placa the Placa to set
     */
    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    /**
     * @return the Carro
     */
    public String getCarro() {
        return Carro;
    }

    /**
     * @param Carro the Carro to set
     */
    public void setCarro(String Carro) {
        this.Carro = Carro;
    }

    /**
     * @return the Monto
     */
    public double getMonto() {
        return Monto;
    }

    /**
     * @param Monto the Monto to set
     */
    public void setMonto(double Monto) {
        this.Monto = Monto;
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

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
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

    
   
    
}
