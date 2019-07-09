/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.entidades.beans.datatypes;

/**
 *
 * @author Nicolas
 */
public class Cliente {
   private  int CI;
   private String NombreCompleto;
   private String Telefono;

   
    public int getCI() {
        return CI;
    }

    public void setCI(int CI) {
        this.CI = CI;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    public Cliente() {
    }

    public Cliente(int CI, String NombreCompleto, String Telefono) {
        this.CI = CI;
        this.NombreCompleto = NombreCompleto;
        this.Telefono = Telefono;
    }

   
    
}
