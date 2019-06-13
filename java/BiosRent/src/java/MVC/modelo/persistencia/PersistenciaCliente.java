/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import MVC.modelo.entidades.beans.datatypes.Cliente;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersistencia;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nicolas
 */
class PersistenciaCliente implements IPersistenciaCliente {
    
    private static PersistenciaCliente instancia = null;
    
    public static PersistenciaCliente getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciaCliente();
        }
        return instancia;
    }
    
    private PersistenciaCliente() {
        
    }
    
    @Override
    public Cliente buscar(int pCI) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            conexion = Utilidades.getConnection();
            cs = conexion.prepareCall("{CALL BuscarCliente(?)}");
            cs.setInt(1, pCI);
            rs = cs.executeQuery();
            Cliente unCliente = null;
            
            String NombreCompleto;
            String Telefono;
            if (rs.next()) {
                NombreCompleto = rs.getString("NombreCompleto");
                Telefono = rs.getString("Telefono");
                unCliente = new Cliente(pCI, NombreCompleto, Telefono);
            }
            return unCliente;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el empleado.", ex);
        } finally {
            Utilidades.CloseResources(rs, cs, conexion);
        }
        
    }
    
    @Override
    public void agregar(Cliente unCliente) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement cs = null;
        
        try {
            conexion = Utilidades.getConnection();
            cs = conexion.prepareCall("{CALL AgregarCliente(?,?,?)}");
            cs.setInt(1, unCliente.getCI());
            cs.setString(2, unCliente.getNombreCompleto());
            cs.setString(3, unCliente.getTelefono());
            int filasAfectadas = cs.executeUpdate();
            if (filasAfectadas < 1) {
                throw new Exception();
            }
            
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
            
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new ExcepcionPersistencia("El cliente ya esta en el sistema", ex);
            } else {
                throw new ExcepcionPersistencia("No se pudo agregar el cliente", ex);
                
            }
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo agregar el cliente", ex);
        } finally {
            Utilidades.CloseResources(cs, conexion);
        }
    }
    
    @Override
    public void modificar(Cliente unCliente) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement cs = null;
        try {
            conexion = Utilidades.getConnection();
            cs = conexion.prepareCall("{CALL ModificarCliente(?,?,?)}");
            cs.setInt(1, unCliente.getCI());
            cs.setString(2, unCliente.getNombreCompleto());
            cs.setString(3, unCliente.getTelefono());
            
            int filas = cs.executeUpdate();
            if (filas < 1) {
                throw new Exception();
            }
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo modificar el cliente", ex);
        } finally {
            Utilidades.CloseResources(cs, conexion);
            
        }
        
    }
    
    @Override
    public void eliminar(int pCI) throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement cs =null;
        try {
            conexion = Utilidades.getConnection();
            cs = conexion.prepareCall("{CALL EliminarCliente(?)}");
            cs.setInt(1, pCI);
            int filas = cs.executeUpdate();
            if(filas <1 ){
                throw new Exception();
                
            }
        }catch(ExcepcionPersonalizada ex){
            throw ex;
                    
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo eliminar el cliente", ex);
        }finally{
            Utilidades.CloseResources(cs,conexion);
        }
    }
}
