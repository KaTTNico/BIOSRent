/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

import MVC.modelo.entidades.beans.datatypes.Sucursal;
import MVC.modelo.entidades.beans.excepciones.ExcepcionLogica;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersistencia;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import MVC.modelo.persistencia.FabricaPersistencia;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
class LogicaSucursal implements ILogicaSucursal{
    
    private static LogicaSucursal instancia = null;

    public static LogicaSucursal getInstancia() {
        if (instancia == null) {
            instancia = new LogicaSucursal();
        }

        return instancia;
    }

    private LogicaSucursal() {
    }

    @Override
    public Sucursal BuscarSucursal(int parameterCodigo) throws ExcepcionPersonalizada {
        try {
            return FabricaPersistencia.getPersistenciaSucursal().BuscarSucursal(parameterCodigo);
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new ExcepcionLogica(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Sucursal> ListarSucursal() throws ExcepcionPersonalizada {
        try {
            return FabricaPersistencia.getPersistenciaSucursal().ListarSucursal();
        } catch (ExcepcionPersonalizada ex){
            throw ex;
        } 
        catch (Exception ex) {
            throw new ExcepcionLogica(ex.getMessage());
        }
    }
    
}
