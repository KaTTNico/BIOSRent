/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.entidades.beans.datatypes;

import java.util.Date;

/**
 *
 * @author Nicolas
 */
public class Devolucion {

    private Alquiler alquiler;
    private Sucursal sucursal;
    private Date fechaDevolucion;
    private Double multa;

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public Devolucion() {
    }

    public Devolucion(Alquiler alquiler, Sucursal sucursal, Date fechaDevolucion, Double multa) {
        this.alquiler = alquiler;
        this.sucursal = sucursal;
        this.fechaDevolucion = fechaDevolucion;
        this.multa = multa;
    }
}
