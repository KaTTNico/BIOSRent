/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.Sucursal;
import MVC.modelo.entidades.beans.datatypes.Vehiculo;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersistencia;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas
 */
class PersistenciaSucursal implements IPersistenciaSucursal {

    private static PersistenciaSucursal instancia = null;

    public static PersistenciaSucursal getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciaSucursal();
        }

        return instancia;
    }

    private PersistenciaSucursal() {
    }

    @Override
    public Sucursal BuscarSucursal(int parameterCodigo) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;
        Sucursal _sucursalEncontrada = null;

        try {

            //Preparar consulta
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{ call BuscarSucursal(?) }");
            consulta.setInt(1, parameterCodigo);

            //Ejecutar y obtener result set
            consulta.execute();
            resultadoConsulta = consulta.getResultSet();

            if (resultadoConsulta.next()) {
                _sucursalEncontrada = new Sucursal();
                _sucursalEncontrada.setCodigo(resultadoConsulta.getInt("Codigo"));
                _sucursalEncontrada.setNombre(resultadoConsulta.getString("Nombre"));
            }

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar la sucursal.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }

        return _sucursalEncontrada;
    }

    @Override
    public ArrayList<Sucursal> ListarSucursal() throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;
        ArrayList<Sucursal> _sucursalesEncontradas = new ArrayList<Sucursal>();
        Sucursal _sucursal = null;

        try {

            //Preparar consulta
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{ call ListarSucursal() }");

            //Ejecutar y obtener result set
            consulta.execute();
            resultadoConsulta = consulta.getResultSet();

            while (resultadoConsulta.next()) {
                _sucursal = new Sucursal();
                _sucursal.setCodigo(resultadoConsulta.getInt("Codigo"));
                _sucursal.setNombre(resultadoConsulta.getString("Nombre"));
                _sucursalesEncontradas.add(_sucursal);
            }

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar las sucursales.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }

        return _sucursalesEncontradas;
    }
}
