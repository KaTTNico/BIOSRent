/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.*;
import MVC.modelo.entidades.beans.excepciones.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;

        try {
            conexion = Utilidades.getConexion();
            consulta = conexion.prepareStatement("SELECT * FROM Empleados WHERE Cedula = ? OR Nombre LIKE ?;");
            resultadoConsulta = consulta.executeQuery();
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
                //Sucursal  sucursalPertenece = PersistenciaSucursal.buscar(resultadoConsulta.getInteger("SucursalCodigo");
                //cuando este hecho el buscar sucursal u obtener sucursal crear el vehiculo completo
                vehiculo = new Vehiculo();
                vehiculos.add(vehiculo);
            }
            return vehiculos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar los vehiculos.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
    }
}