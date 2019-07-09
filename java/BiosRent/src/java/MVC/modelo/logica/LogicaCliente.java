/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import MVC.modelo.entidades.beans.datatypes.Cliente;
import MVC.modelo.entidades.beans.excepciones.ExcepcionLogica;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import MVC.modelo.persistencia.FabricaPersistencia;
import MVC.modelo.persistencia.IPersistenciaCliente;
import static java.lang.Integer.parseInt;
import java.util.List;

/**
 *
 * @author Nicolas
 */
class LogicaCliente implements ILogicaCliente {

    private static LogicaCliente instancia = null;

    public static LogicaCliente getInstancia() {
        if (instancia == null) {
            instancia = new LogicaCliente();
        }
        return instancia;
    }
    private IPersistenciaCliente persistencia = FabricaPersistencia.getPersistenciaCliente();

    public LogicaCliente() {
    }

    @Override
    public Cliente buscar(int pCI) throws ExcepcionPersonalizada {
        return persistencia.buscar(pCI);
    }

    @Override
    public void agregar(Cliente unCliente) throws ExcepcionPersonalizada {
        validar(unCliente);
        persistencia.agregar(unCliente);
    }

    @Override
    public void modificar(Cliente unCliente) throws ExcepcionPersonalizada {
        validar(unCliente);
        persistencia.modificar(unCliente);
    }

    @Override
    public void eliminar(int pCI) throws ExcepcionPersonalizada {
       persistencia.eliminar(pCI);
    }

    @Override
    public void validar(Cliente unCliente) throws ExcepcionPersonalizada {
        if (unCliente == null) {
            throw new ExcepcionLogica("El cliente es nulo.");
        }
        if (unCliente.getCI() < 1) {
            throw new ExcepcionLogica("La cédula del cliente no puede ser negativa");

        }
        if (unCliente.getNombreCompleto().length() > 50) {
            throw new ExcepcionLogica("El nombre del cliente no puede exceder los 50 caracteres");

        }
        try {
            if (!(unCliente.getTelefono().matches("^09[0-9]{7}||2[0-9]{7}$"))) {            
                throw new ExcepcionLogica("El telefono ingresado no es válido");
            }
        } catch (Exception e) {
            throw new ExcepcionLogica("Error en el telefono");

        }

    }

    @Override
    public List<Cliente> ListarClientes(String pCriterio) throws ExcepcionPersonalizada {
        if(pCriterio == null || pCriterio.length() ==0){
           // return listar();
        }
       return  persistencia.ListaDeClientes(pCriterio);
    }

}
