/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import MVC.modelo.entidades.beans.datatypes.Empleado;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import MVC.modelo.persistencia.FabricaPersistencia;
import MVC.modelo.persistencia.IPersistenciaCliente;
import MVC.modelo.persistencia.IPersistenciaEmpleado;

/**
 *
 * @author Nicolas
 */
class LogicaEmpleado implements ILogicaEmpleado {

    private static LogicaEmpleado instancia = null;

    public static LogicaEmpleado getInstancia() {
        if (instancia == null) {
            instancia = new LogicaEmpleado();

        }
        return instancia;
    }

    public LogicaEmpleado() {
    }
    private IPersistenciaEmpleado persistencia = FabricaPersistencia.getPersistenciaEmpleado();

    @Override
    public Empleado buscar(String pNomUser) throws ExcepcionPersonalizada {
        return persistencia.buscar(pNomUser);

    }

    @Override
    public Empleado logueo(String pNomUser, String pPassUser) throws ExcepcionPersonalizada {
        Empleado unEmp = persistencia.buscar(pNomUser);
        if (unEmp != null && !unEmp.getPassUser().equals(pPassUser)) {
            unEmp =null;
        }
        return unEmp;
    }

}
