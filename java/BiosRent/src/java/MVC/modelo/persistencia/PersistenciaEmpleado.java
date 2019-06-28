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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    protected Empleado crearEmp(ResultSet datos) throws SQLException{
        Empleado unEmp = new Empleado();
        unEmp.setNombreUser(datos.getString("NombreUser"));
        unEmp.setPassUser(datos.getString("Pass"));
       // unEmp.setSucursalEmp(PersistenciaSucursal.getInstancia().buscar(datos.getInt("CodigoSucursal")));
          return unEmp;      
                
    }
    

    @Override
    public Empleado buscar(String pNomUser) throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Empleado unEmp = null;

        try {
            conexion = Utilidades.getConnection();
            ps = conexion.prepareStatement("Select * From Empleado where NombreUser = ?;");
            ps.setString(1, pNomUser);
            rs = ps.executeQuery();

            
            

            if (rs.next()) {
                
              unEmp = crearEmp(rs);
            }
            
        } catch (Exception ex) {
            throw new ExcepcionPersonalizada("No se pudo bucar el empleado", ex);
        } finally {
            Utilidades.CloseResources(rs, ps, conexion);

        }
        return unEmp;

    }

   

}
