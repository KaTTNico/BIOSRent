/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.Empleado;
import MVC.modelo.entidades.beans.datatypes.Sucursal;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import com.sun.webkit.LoadListenerClient;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Nicolas
 */
class PersistenciaEmpleado implements IPersistenciaEmpleado {

    private static PersistenciaEmpleado instancia = null;

    public static PersistenciaEmpleado getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciaEmpleado();

        }
        return instancia;

    }

    public PersistenciaEmpleado() {
    }

    @Override
    public Empleado buscar(String pNomUser) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conexion = Utilidades.getConnection();
            cs = conexion.prepareCall("{CALL BuscarEmpleado(?)}");
            cs.setString(1, pNomUser);
            rs = cs.executeQuery();

            Empleado unEmp = null;
            String PassUser;
            int codigoSucursal;

            if (rs.next()) {
                PassUser = rs.getString("Pass");
                codigoSucursal = rs.getInt("CodigoSucursal");
              //  unEmp= new Empleado(pNomUser,PassUser,PersistenciaSucursal.getInstancia().buscar(codigoSucursal));
            }
            return unEmp;
        } catch (Exception ex) {
            throw new ExcepcionPersonalizada("No se pudo bucar el empleado", ex);
        } finally {
            Utilidades.CloseResources(rs, cs, conexion);

        }

    }

    @Override
    public Empleado logueo(String pNomUser, String pPassUser) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conexion = Utilidades.getConnection();
            cs = conexion.prepareCall("{CALL LogueoEmpleado(?,?)");
            rs = cs.executeQuery();

            Empleado unEmp = null;
            String PassUser;
            int codigoSucursal;

            if (rs.next()) {
                PassUser = rs.getString("Pass");
                codigoSucursal = rs.getInt("CodigoSucursal");
              //  unEmp= new Empleado(pNomUser,PassUser,PersistenciaSucursal.getInstancia().buscar(codigoSucursal));
            }
            return unEmp;
        } catch (Exception ex) {
            throw new ExcepcionPersonalizada("No se pudo bucar el empleado", ex);
        } finally {
            Utilidades.CloseResources(rs, cs, conexion);

        }

    }

}
