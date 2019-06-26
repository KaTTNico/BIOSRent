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
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas
 */
class PersistenciaVehiculo implements IPersistenciaVehiculo{
    
    private static PersistenciaVehiculo instancia = null;

    public static PersistenciaVehiculo getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciaVehiculo();
        }

        return instancia;
    }

    private PersistenciaVehiculo() {
    }

    @Override
    public Vehiculo BuscarVehiculo(String parameterMatricula) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;
        Vehiculo _vehiucloEncontrado = null;

        try {

            //Preparar consulta
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{ call BuscarVehiculo(?) }");
            consulta.setString(1, parameterMatricula);

            //Ejecutar y obtener result set
            consulta.execute();
            resultadoConsulta = consulta.getResultSet();

            if (resultadoConsulta.next()) {
                _vehiucloEncontrado = new Vehiculo();
                _vehiucloEncontrado.setMatricula(parameterMatricula);
                _vehiucloEncontrado.setDescripcion(resultadoConsulta.getString("Descripcion"));
                _vehiucloEncontrado.setPrecioAlquilerDiario(resultadoConsulta.getDouble("PrecioAlquilerDiario"));
                _vehiucloEncontrado.setSucursalPertenece(PersistenciaSucursal.getInstancia().BuscarSucursal(resultadoConsulta.getInt("SucursalCodigo")));
                _vehiucloEncontrado.setTipo(resultadoConsulta.getString("Tipo"));
            }

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el vehiculo.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
        
        return _vehiucloEncontrado;
    }
    
    Vehiculo BuscarVehiculoTodos(String parameterMatricula) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;
        Vehiculo _vehiucloEncontrado = null;

        try {

            //Preparar consulta
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{ call BuscarVehiculoTodos(?) }");
            consulta.setString(1, parameterMatricula);

            //Ejecutar y obtener result set
            consulta.execute();
            resultadoConsulta = consulta.getResultSet();

            if (resultadoConsulta.next()) {
                _vehiucloEncontrado = new Vehiculo();
                _vehiucloEncontrado.setMatricula(parameterMatricula);
                _vehiucloEncontrado.setDescripcion(resultadoConsulta.getString("Descripcion"));
                _vehiucloEncontrado.setPrecioAlquilerDiario(resultadoConsulta.getDouble("PrecioAlquilerDiario"));
                _vehiucloEncontrado.setSucursalPertenece(PersistenciaSucursal.getInstancia().BuscarSucursal(resultadoConsulta.getInt("SucursalCodigo")));
                _vehiucloEncontrado.setTipo(resultadoConsulta.getString("Tipo"));
            }

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el vehiculo (Todos).", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
        
        return _vehiucloEncontrado;
    }

    @Override
    public ArrayList<Vehiculo> ListarVehiculo() throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;
        ArrayList<Vehiculo> _vehiculosEncontrados = new ArrayList<Vehiculo>();
        Vehiculo _vehiucloEncontrado = null;

        try {

            //Preparar consulta
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{ call ListarVehiculo(?) }");

            //Ejecutar y obtener result set
            consulta.execute();
            resultadoConsulta = consulta.getResultSet();

            while (resultadoConsulta.next()) {
                _vehiucloEncontrado = new Vehiculo();
                _vehiucloEncontrado.setMatricula(resultadoConsulta.getString("Matricula"));
                _vehiucloEncontrado.setDescripcion(resultadoConsulta.getString("Descripcion"));
                _vehiucloEncontrado.setPrecioAlquilerDiario(resultadoConsulta.getDouble("PrecioAlquilerDiario"));
                _vehiucloEncontrado.setSucursalPertenece(PersistenciaSucursal.getInstancia().BuscarSucursal(resultadoConsulta.getInt("SucursalCodigo")));
                _vehiucloEncontrado.setTipo(resultadoConsulta.getString("Tipo"));
                _vehiculosEncontrados.add(_vehiucloEncontrado);
            }

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los vehiculos.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
        
        return _vehiculosEncontrados;
    }

    @Override
    public void AgregarVehiculo(Vehiculo parameterVehiculo) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;
        int p = 0;

        try {

            //Preparar consulta
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{ call AgregarVehiculo(?,?,?,?,?,?) }");
            consulta.setString(++p, parameterVehiculo.getMatricula());
            consulta.setString(++p, parameterVehiculo.getTipo());
            consulta.setString(++p, parameterVehiculo.getTipo());
            consulta.setDouble(++p, parameterVehiculo.getPrecioAlquilerDiario());
            consulta.setInt(++p, parameterVehiculo.getSucursalPertenece().getCodigo());
            consulta.registerOutParameter(++p, java.sql.JDBCType.VARCHAR);
            
            //Ejecutar y obtener result set
            consulta.executeUpdate();
            String mensajeError = consulta.getString(p);

            if (mensajeError != null) throw new Exception(mensajeError);

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo agregar el vehiculo.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
    }

    @Override
    public void ModificarVehiculo(Vehiculo parameterVehiculo) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;
        int p = 0;

        try {

            //Preparar consulta
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{ call ModificarVehiculo(?,?,?,?,?,?) }");
            consulta.setString(++p, parameterVehiculo.getMatricula());
            consulta.setString(++p, parameterVehiculo.getTipo());
            consulta.setString(++p, parameterVehiculo.getTipo());
            consulta.setDouble(++p, parameterVehiculo.getPrecioAlquilerDiario());
            consulta.setInt(++p, parameterVehiculo.getSucursalPertenece().getCodigo());
            consulta.registerOutParameter(++p, java.sql.JDBCType.VARCHAR);
            
            //Ejecutar y obtener result set
            consulta.executeUpdate();
            String mensajeError = consulta.getString(p);

            if (mensajeError != null) throw new Exception(mensajeError);

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo agregar el vehiculo.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
    }

    @Override
    public void EliminarVehiculo(String parameterMatricula) throws ExcepcionPersonalizada{
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;
        int p = 0;

        try {

            //Preparar consulta
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{ call EliminarVehiculo(?,?) }");
            consulta.setString(++p, parameterMatricula);
            consulta.registerOutParameter(++p, java.sql.JDBCType.VARCHAR);
            
            //Ejecutar y obtener result set
            consulta.executeUpdate();
            String mensajeError = consulta.getString(p);

            if (mensajeError != null) throw new Exception(mensajeError);

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo eliminar el vehiculo.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
    }
    
}
