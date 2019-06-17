/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.entidades.beans.excepciones;

import java.io.Serializable;

/**
 *
 * @author Nicolas
 */
public class ExcepcionPresentacion extends ExcepcionPersonalizada implements Serializable {
    
    public ExcepcionPresentacion() {
        
    }
    
    public ExcepcionPresentacion(String mensaje) {
        super(mensaje);
    }
    
    public ExcepcionPresentacion(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
    
}