/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.*;
import MVC.modelo.entidades.beans.excepciones.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
class PersistenciaAlquiler implements IPersistenciaAlquiler {

    private static PersistenciaAlquiler instancia = null;

    public static PersistenciaAlquiler getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciaAlquiler();
        }

        return instancia;
    }

    private PersistenciaAlquiler() {

    }

    @Override
    public ArrayList<Vehiculo> listarVehiculosDisponibles() throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;

        try {
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{CALL ListarVehiculosDisponibles()}");
            consulta.execute();
            resultadoConsulta = consulta.getResultSet();
            ArrayList<Vehiculo> vehiculos = new ArrayList();
            Vehiculo vehiculo;

            String matricula;
            String tipo;
            String descripcion;
            double precioAlquilerDiario;

            while (resultadoConsulta.next()) {
                matricula = resultadoConsulta.getString("Matricula");
                tipo = resultadoConsulta.getString("Tipo");
                descripcion = resultadoConsulta.getString("Descripcion");
                precioAlquilerDiario = resultadoConsulta.getDouble("PrecioAlquilerDiario");
                Sucursal sucursalPertenece = PersistenciaSucursal.getInstancia().BuscarSucursal(resultadoConsulta.getInt("CodigoSucursal"));
                vehiculo = new Vehiculo(matricula, tipo, descripcion, precioAlquilerDiario, sucursalPertenece);
                vehiculos.add(vehiculo);
            }
            return vehiculos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo obtener los vehiculos.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
    }
}
