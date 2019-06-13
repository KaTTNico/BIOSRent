/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import MVC.modelo.entidades.beans.datatypes.Empleado;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Nicolas
 */
public interface ILogicaEmpleado {
    Empleado buscar(String pNomUser) throws ExcepcionPersonalizada;
    Empleado logueo(String pNomUser, String pPassUser) throws ExcepcionPersonalizada;
   
    
}
