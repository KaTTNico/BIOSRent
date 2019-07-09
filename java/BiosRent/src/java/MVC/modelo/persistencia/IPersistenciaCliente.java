/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.Cliente;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public interface IPersistenciaCliente {
    
    Cliente buscar(int pCI) throws ExcepcionPersonalizada;
    void agregar(Cliente unCliente) throws ExcepcionPersonalizada;
    void modificar(Cliente unCliente) throws ExcepcionPersonalizada;
    void eliminar(int pCI) throws ExcepcionPersonalizada;
    List<Cliente> ListaDeClientes(String pCriterio) throws ExcepcionPersonalizada;
    List<Cliente> ListaCompleta() throws ExcepcionPersonalizada;
}
