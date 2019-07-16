/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import MVC.modelo.entidades.beans.datatypes.*;
import MVC.modelo.entidades.beans.excepciones.ExcepcionLogica;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import MVC.modelo.persistencia.FabricaPersistencia;
import MVC.modelo.persistencia.IPersistenciaAlquiler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas
 */
class LogicaAlquiler implements ILogicaAlquiler {

    private static LogicaAlquiler instancia = null;

    public static LogicaAlquiler getInstancia() {
        if (instancia == null) {
            instancia = new LogicaAlquiler();
        }

        return instancia;
    }

    private IPersistenciaAlquiler persistencia = FabricaPersistencia.getPersistenciaAlquiler();

    private LogicaAlquiler() {

    }

    @Override
    public void validar(Alquiler alquiler) throws ExcepcionPersonalizada {
        if (alquiler == null) {
            throw new ExcepcionLogica("El alquiler es nulo.");
        }

        if (alquiler.getFechaAlquiler() == null) {
            throw new ExcepcionLogica("La fecha es nula.");
        }

        if (alquiler.getCantidadDias() <= 0) {
            throw new ExcepcionLogica("La cantidad de dias debe ser mayor a cero.");
        }

        if (alquiler.getCostoSeguro() < 0) {
            throw new ExcepcionLogica("El costo del seguro no puede ser menor a cero.");
        }

        if (alquiler.getCostoTotal() < 0) {
            throw new ExcepcionLogica("El costo total no puede ser menor a cero.");
        }

        if (alquiler.getDepositoEnGarantia() < 0) {
            throw new ExcepcionLogica("El deposito en garantia no puede ser menor a cero.");
        }
    }

    @Override
    public void alta(Alquiler alquiler) throws ExcepcionPersonalizada {
        try {
            validar(alquiler);
            persistencia.alta(alquiler);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void ver(int id) throws ExcepcionPersonalizada {

    }

    @Override
    public ArrayList<Vehiculo> listarVehiculosDisponibles(String usuario) throws ExcepcionPersonalizada {
        return persistencia.listarVehiculosDisponibles(usuario);
    }

    @Override
    public Alquiler obtenerAlquilerPendiente(int cedula) throws ExcepcionPersonalizada {
        return persistencia.obtenerAlquilerPendiente(cedula);
    }

    @Override
    public double obtenerMulta(int id) throws ExcepcionPersonalizada {
        return persistencia.obtenerMulta(id);
    }
}
