/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

/**
 *
 * @author familia
 */
public class Modelo_Cita {

    /**
     * @return the id_cita
     */
    public int getId_cita() {
        return id_cita;
    }

    /**
     * @param id_cita the id_cita to set
     */
    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
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
     * @return the Estado
     */
    public int getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the asistencia
     */
    public int getAsistencia() {
        return asistencia;
    }

    /**
     * @param asistencia the asistencia to set
     */
    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    /**
     * @return the id_visitante
     */
    public int getId_visitante() {
        return id_visitante;
    }

    /**
     * @param id_visitante the id_visitante to set
     */
    public void setId_visitante(int id_visitante) {
        this.id_visitante = id_visitante;
    }

    /**
     * @return the Nombres
     */
    public String getNombres() {
        return Nombres;
    }

    /**
     * @param Nombres the Nombres to set
     */
    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public Modelo_Cita(int id_cita, String fecha) {
        this.id_cita = id_cita;
        this.fecha = fecha;
    }
    private int id_cita;
    private String fecha;
    private String solicita;
    private String motivo;
    private String observaciones;
    private int Estado;
    private int asistencia;
    private int id_visitante;
    private String Nombres;

    public Modelo_Cita(int id_cita, String fecha, String solicita, String motivo, String observaciones, int Estado, int asistencia, int id_visitante, String Nombres) {
        this.id_cita = id_cita;
        this.fecha = fecha;
        this.solicita = solicita;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.Estado = Estado;
        this.asistencia = asistencia;
        this.id_visitante = id_visitante;
        this.Nombres = Nombres;
    }

}
