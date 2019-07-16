/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nicolas
 */
class Utilidades {

    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/biosRent";
    //Juan
    //private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    //private static final String CONTRASENA_BASE_DATOS = "password";

    //Nico
    //private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    //private static final String CONTRASENA_BASE_DATOS = "root";
    //Kufa
    private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    private static final String CONTRASENA_BASE_DATOS = "Password01";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception ex) {
            System.out.println("No se pudo instanciar el driver JDBC");

        }
    }

    protected static Connection getConnection() throws SQLException {
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
