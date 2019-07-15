/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import MVC.modelo.entidades.beans.datatypes.Vehiculo;
import MVC.modelo.entidades.beans.excepciones.ExcepcionLogica;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import MVC.modelo.persistencia.FabricaPersistencia;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
class LogicaVehiculo implements ILogicaVehiculo {

    private static LogicaVehiculo instancia = null;

    public static LogicaVehiculo getInstancia() {
        if (instancia == null) {
            instancia = new LogicaVehiculo();
        }

        return instancia;
    }

    private LogicaVehiculo() {
    }

    @Override
    public Vehiculo BuscarVehiculo(String parameterMatricula) throws ExcepcionPersonalizada {
        try {
            return FabricaPersistencia.getPersistenciaVehiculo().BuscarVehiculo(parameterMatricula);
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionLogica(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Vehiculo> ListarVehiculo() throws ExcepcionPersonalizada {
        try {
            return FabricaPersistencia.getPersistenciaVehiculo().ListarVehiculo();
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionLogica(ex.getMessage());
        }
    }

    @Override
    public void AgregarVehiculo(Vehiculo parameterVehiculo) throws ExcepcionPersonalizada {
        try {
            ValidarVehiculo(parameterVehiculo);
            FabricaPersistencia.getPersistenciaVehiculo().AgregarVehiculo(parameterVehiculo);
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionLogica(ex.getMessage());
        }
    }

    @Override
    public void ModificarVehiculo(Vehiculo parameterVehiculo) throws ExcepcionPersonalizada {
        try {
            ValidarVehiculo(parameterVehiculo);
            FabricaPersistencia.getPersistenciaVehiculo().ModificarVehiculo(parameterVehiculo);
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionLogica(ex.getMessage());
        }
    }

    @Override
    public void EliminarVehiculo(String parameterMatricula) throws ExcepcionPersonalizada {
        try {
            FabricaPersistencia.getPersistenciaVehiculo().EliminarVehiculo(parameterMatricula);
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionLogica(ex.getMessage());
        }
    }

    private void ValidarVehiculo(Vehiculo parameterVehiculo) throws Exception {
        if (!(parameterVehiculo.getTipo().equals("AUTO") || parameterVehiculo.getTipo().equals("CAMIONETA") || parameterVehiculo.getTipo().equals("OTROS"))) {
            throw new Exception("Tipo incorrecto");
        }
        if (!parameterVehiculo.getMatricula().matches("^[a-zA-Z]{3}[0-9]{4}$")) {
            throw new Exception("El formato de la matricula es incorrecto");
        }
        if (parameterVehiculo.getPrecioAlquilerDiario() <= 0) {
            throw new Exception("Precio incorrecto");
        }
    }
}
