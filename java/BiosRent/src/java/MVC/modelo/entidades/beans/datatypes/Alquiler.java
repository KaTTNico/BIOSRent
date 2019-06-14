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
    int id;
    Date fechaAlquiler;
    int cantidadDias;
    Double costoSeguro;
    Double costoTotal;
    Double depositoEnGarantia;
    Cliente clientel;
    Sucursal sucursal;
    Vehiculo vehiculo;
}
