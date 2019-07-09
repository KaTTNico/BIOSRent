/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.logica;

/**
 *
 * @author Nicolas
 */
public class FabricaLogica {

    public static ILogicaCliente getLogicaCliente(){
        return LogicaCliente.getInstancia();
    }
    public static ILogicaEmpleado getLogicaEmpleado(){
        return LogicaEmpleado.getInstancia();
    }

    public static ILogicaAlquiler getLogicaAlquiler(){
        return LogicaAlquiler.getInstancia();

    }
}
