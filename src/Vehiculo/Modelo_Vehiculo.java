/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehiculo;

/**
 *
 * @author familia
 */
public class Modelo_Vehiculo {

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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the gas
     */
    public String getGas() {
        return gas;
    }

    /**
     * @param gas the gas to set
     */
    public void setGas(String gas) {
        this.gas = gas;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
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

    private String Placa;
    private String marca;
    private String modelo;
    private int year;
    private String gas;
    private String color;
    private int estado;

    public Modelo_Vehiculo(String Placa, String marca, String modelo, int year, String gas, String color, int estado) {
        this.Placa = Placa;
        this.marca = marca;
        this.modelo = modelo;
        this.year = year;
        this.gas = gas;
        this.color = color;
        this.estado = estado;
    }

}
