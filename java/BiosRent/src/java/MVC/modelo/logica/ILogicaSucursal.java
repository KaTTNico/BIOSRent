/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import MVC.modelo.entidades.beans.datatypes.Sucursal;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public interface ILogicaSucursal {
    Sucursal BuscarSucursal(int parameterCodigo) throws ExcepcionPersonalizada;
    ArrayList<Sucursal> ListarSucursal() throws ExcepcionPersonalizada;
}
