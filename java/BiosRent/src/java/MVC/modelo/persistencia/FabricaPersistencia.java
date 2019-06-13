/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

/**
 *
 * @author Nicolas
 */
public class FabricaPersistencia {
    public static IPersistenciaCliente getPersistenciaCliente(){
        return PersistenciaCliente.getInstancia();
                
    }
    public static IPersistenciaEmpleado getPersistenciaEmpleado(){
        return PersistenciaEmpleado.getInstancia();
    }
}
