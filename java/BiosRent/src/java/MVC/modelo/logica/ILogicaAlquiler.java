/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import java.util.List;
import MVC.modelo.entidades.beans.datatypes.*;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public interface ILogicaAlquiler {

    void validar(Alquiler alquiler) throws ExcepcionPersonalizada;

    void alta(Alquiler alquiler) throws ExcepcionPersonalizada;

    void ver(int id) throws ExcepcionPersonalizada;

    ArrayList<Vehiculo> listarVehiculosDisponibles(String usuario) throws ExcepcionPersonalizada;
}
