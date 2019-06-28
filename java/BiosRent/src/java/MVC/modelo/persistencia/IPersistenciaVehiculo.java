/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.Vehiculo;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public interface IPersistenciaVehiculo {
    Vehiculo BuscarVehiculo(String parameterMatricula) throws ExcepcionPersonalizada;
    ArrayList<Vehiculo> ListarVehiculo() throws ExcepcionPersonalizada;
    void AgregarVehiculo(Vehiculo parameterVehiculo) throws ExcepcionPersonalizada;
    void ModificarVehiculo(Vehiculo parameterVehiculo) throws ExcepcionPersonalizada;
    void EliminarVehiculo(String parameterMatricula) throws ExcepcionPersonalizada;
}
