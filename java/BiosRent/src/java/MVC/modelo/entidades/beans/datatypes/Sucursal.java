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
public class Sucursal {
    //Atributos
    
    private int _codigo;
    private String _nombre;

    //Constructores
    public Sucursal() { }

    public Sucursal(int _codigo, String _nombre) {
        this._codigo = _codigo;
        this._nombre = _nombre;
    }

    //Propiedades
    public int getCodigo() {
        return _codigo;
    }

    public void setCodigo(int _codigo) {
        this._codigo = _codigo;
    }

    public String getNombre() {
        return _nombre;
    }
    
    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }
    
    @Override
    public String toString() {
        return "Sucursal{" + "_codigo=" + _codigo + ", _nombre=" + _nombre + '}';
    }
}
