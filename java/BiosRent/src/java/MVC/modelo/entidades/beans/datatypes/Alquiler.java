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
public class Alquiler {
    private int id;
    private Date fechaAlquiler;
    private int cantidadDias;
    private Double costoSeguro;
    private Double costoTotal;
    private Double depositoEnGarantia;
    private Cliente clientel;
    private Sucursal sucursal;
    private Vehiculo vehiculo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public Double getCostoSeguro() {
        return costoSeguro;
    }

    public void setCostoSeguro(Double costoSeguro) {
        this.costoSeguro = costoSeguro;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Double getDepositoEnGarantia() {
        return depositoEnGarantia;
    }

    public void setDepositoEnGarantia(Double depositoEnGarantia) {
        this.depositoEnGarantia = depositoEnGarantia;
    }

    public Cliente getClientel() {
        return clientel;
    }

    public void setClientel(Cliente clientel) {
        this.clientel = clientel;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
