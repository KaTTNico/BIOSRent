/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.*;
import MVC.modelo.entidades.beans.excepciones.*;
import MVC.modelo.logica.FabricaLogica;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public void alta(Alquiler alquiler) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        try {
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{CALL AgregarAlquiler(?,?,?,?,?,?,?,?,?)}");

            java.sql.Date javadate = new java.sql.Date(alquiler.getFechaAlquiler().getTime());
            consulta.setDate(1, javadate);
            consulta.setInt(2, alquiler.getCantidadDias());
            consulta.setDouble(3, alquiler.getCostoSeguro());
            consulta.setDouble(4, alquiler.getCostoTotal());
            consulta.setDouble(5, alquiler.getDepositoEnGarantia());
            consulta.setInt(6, alquiler.getClientel().getCI());
            consulta.setInt(7, alquiler.getSucursal().getCodigo());
            consulta.setString(8, alquiler.getVehiculo().getMatricula());
            consulta.registerOutParameter(9, java.sql.Types.VARCHAR);

            consulta.executeUpdate();
            String error = consulta.getString(9);
            if (error != null) {
                throw new ExcepcionPersistencia(error);
            }

        } catch (Exception e) {
            throw new ExcepcionPersistencia("No se pudo alquilar el vehiculo.", e);
        } finally {
            Utilidades.CloseResources(consulta, conexion);
        }
    }

    @Override
    public ArrayList<Vehiculo> listarVehiculosDisponibles(String usuario) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;

        try {
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{CALL ListarVehiculosDisponibles(?)}");
            consulta.setString(1, usuario);
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

    @Override
    public Alquiler obtenerAlquilerPendiente(int cedula) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;

        try {
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{CALL ObtenerAlquilerPendiente(?)}");
            consulta.setInt(1, cedula);
            consulta.execute();
            resultadoConsulta = consulta.getResultSet();

            Alquiler alquiler = new Alquiler();
            int id;
            Date fecha;
            int cantidadDias;
            double costoSeguro;
            double total;
            double depositoGarantia;
            int sucursalCodigo;
            String matricula;

            while (resultadoConsulta.next()) {
                id = resultadoConsulta.getInt("Id");
                fecha = resultadoConsulta.getDate("FechaAlquiler");
                cantidadDias = resultadoConsulta.getInt("CantidadDias");
                costoSeguro = resultadoConsulta.getDouble("CostoSeguro");
                total = resultadoConsulta.getDouble("Total");
                depositoGarantia = resultadoConsulta.getDouble("DepositoEnGarantia");
                sucursalCodigo = resultadoConsulta.getInt("SucursalRetiraCodigo");
                matricula = resultadoConsulta.getString("VehiculoMatricula");

                Cliente cliente = FabricaLogica.getLogicaCliente().buscar(cedula);
                Sucursal sucursal = FabricaLogica.getLogicaSucursal().BuscarSucursal(sucursalCodigo);
                Vehiculo vehiculo = FabricaLogica.getLogicaVehiculo().BuscarVehiculo(matricula);
                alquiler = new Alquiler(id, fecha, cantidadDias, costoSeguro, total, depositoGarantia, cliente, sucursal, vehiculo);
            }

            return alquiler;

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo obtener los vehiculos.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
    }

    @Override
    public double obtenerMulta(int cedula) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
        ResultSet resultadoConsulta = null;

        double multa = 0d;

        try {
            conexion = Utilidades.getConnection();
            consulta = conexion.prepareCall("{CALL obtenerMulta(?)}");
            consulta.setInt(1, cedula);
            consulta.execute();
            resultadoConsulta = consulta.getResultSet();

            while (resultadoConsulta.next()) {
                multa = resultadoConsulta.getDouble("Multa");
            }

            return multa;

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo obtener la multa.", ex);
        } finally {
            Utilidades.CloseResources(resultadoConsulta, consulta, conexion);
        }
    }
}
