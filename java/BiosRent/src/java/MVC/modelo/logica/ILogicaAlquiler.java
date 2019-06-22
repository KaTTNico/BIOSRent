/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import MVC.modelo.entidades.beans.datatypes.*;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
/**
 *
 * @author Nicolas
 */
public interface ILogicaAlquiler {
    void validar(Alquiler alquiler) throws ExcepcionPersonalizada;
    void alta(Alquiler alquiler) throws ExcepcionPersonalizada;
    void eliminar(int id) throws ExcepcionPersonalizada;
    void modificar(Alquiler alquiler) throws ExcepcionPersonalizada;
}
