/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.Vehiculo;
import MVC.modelo.entidades.beans.excepciones.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public interface IPersistenciaAlquiler {

    ArrayList<Vehiculo> listarVehiculosDisponibles() throws ExcepcionPersonalizada;
}
