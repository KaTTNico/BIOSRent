/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Nicolas
 */
public class Utilidades {

    private static final String URL_CONEXION;
    //Juan
    private static final String NOMBRE_USUARIO_BASE_DATOS;
    private static final String CONTRASENA_BASE_DATOS;

    //Nico
    //private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    // private static final String CONTRASENA_BASE_DATOS = "root";
    //Kufa
    //private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    //private static final String CONTRASENA_BASE_DATOS = "password";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, "No se pudo instanciar el driver JDBC.", ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        URL_CONEXION = "jdbc:mysql://localhost:3306/biosRent?useSSL=false";
        //Juan
        NOMBRE_USUARIO_BASE_DATOS = "root";
        CONTRASENA_BASE_DATOS = "password";
    }

    protected static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL_CONEXION, NOMBRE_USUARIO_BASE_DATOS, CONTRASENA_BASE_DATOS);

    }

    protected static void CloseResources(AutoCloseable... resource) {
        try {
            for (AutoCloseable r : resource) {
                if (r != null) {
                    r.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Error, ocurrió un problema al cerrar recursos");
        }
    }
}
