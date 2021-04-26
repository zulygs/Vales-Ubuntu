/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitas;

/**
 *
 * @author familia
 */
public class Modelo_Visita {

   
 

    /**
     * @return the id_visita
     */
    public int getId_visita() {
        return id_visita;
    }

    /**
     * @param id_visita the id_visita to set
     */
    public void setId_visita(int id_visita) {
        this.id_visita = id_visita;
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
     * @return the solicita
     */
    public String getSolicita() {
        return solicita;
    }

    /**
     * @param solicita the solicita to set
     */
    public void setSolicita(String solicita) {
        this.solicita = solicita;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the id_Visitante
     */
    public int getId_Visitante() {
        return id_Visitante;
    }

    /**
     * @param id_Visitante the id_Visitante to set
     */
    public void setId_Visitante(int id_Visitante) {
        this.id_Visitante = id_Visitante;
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

    public Modelo_Visita(int id_visita, String fecha, String solicita, String motivo, String observaciones, int estado, int id_Visitante, String Nombre) {
        this.id_visita = id_visita;
        this.fecha = fecha;
        this.solicita = solicita;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.id_Visitante = id_Visitante;
        this.Nombre = Nombre;
    }
    
     public Modelo_Visita(int id_visita, String fecha, String solicita, String motivo, String observaciones, int estado, int id_Visitante) {
        this.id_visita = id_visita;
        this.fecha = fecha;
        this.solicita = solicita;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.id_Visitante = id_Visitante;
    
    }
    
       private int id_visita;
    private String fecha;
     private String solicita;
     private String motivo;
     private String observaciones;
    private int estado;
    private int id_Visitante;
    private String Nombre;

}
