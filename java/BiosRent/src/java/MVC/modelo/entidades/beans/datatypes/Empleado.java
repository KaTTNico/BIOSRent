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
public class Empleado {

    private String nombreUser;
    private String passUser;
    private Sucursal sucursalEmp;

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public Sucursal getSucursalEmp() {
        return sucursalEmp;
    }

    public void setSucursalEmp(Sucursal sucursalEmp) {
        this.sucursalEmp = sucursalEmp;
    }

    public Empleado() {
    }

    public Empleado(String nombreUser, String passUser, Sucursal sucursalEmp) {
        this.nombreUser = nombreUser;
        this.passUser = passUser;
        this.sucursalEmp = sucursalEmp;
    }

}
