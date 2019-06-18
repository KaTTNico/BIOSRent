/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.Empleado;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Nicolas
 */
public interface IPersistenciaEmpleado {
    Empleado buscar(String pNomUser) throws ExcepcionPersonalizada;
  
}
