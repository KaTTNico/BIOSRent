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
public class Vehiculo {
    
    //Atributos
    private String _matricula;
    private String _tipo;
    private String _descripcion;
    private double _precioAlquilerDiario;
    
    private Sucursal _sucursalPertenece;

    //Constructores
    public Vehiculo(String _matricula, String _tipo, String _descripcion, double _precioAlquilerDiario, Sucursal _sucursalPertenece) {
        this._matricula = _matricula;
        this._tipo = _tipo;
        this._descripcion = _descripcion;
        this._precioAlquilerDiario = _precioAlquilerDiario;
        this._sucursalPertenece = _sucursalPertenece;
    }

    public Vehiculo() { }

    //Propiedades
    public String getMatricula() {
        return _matricula;
    }

    public void setMatricula(String _matricula) {
        this._matricula = _matricula;
    }

    public String getTipo() {
        return _tipo;
    }

    public void setTipo(String _tipo) {
        this._tipo = _tipo;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public double getPrecioAlquilerDiario() {
        return _precioAlquilerDiario;
    }

    public void setPrecioAlquilerDiario(double _precioAlquilerDiario) {
        this._precioAlquilerDiario = _precioAlquilerDiario;
    }
    
    public Sucursal getSucursalPertenece() {
        return _sucursalPertenece;
    }

    public void setSucursalPertenece(Sucursal _sucursalPertenece) {
        this._sucursalPertenece = _sucursalPertenece;
    }
    
    @Override
    public String toString() {
        return "Vehiculo{" + "_matricula=" + _matricula + ", _tipo=" + _tipo + ", _descripcion=" + _descripcion + ", _precioAlquilerDiario=" + _precioAlquilerDiario + '}';
    }
}
