/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.presentacion.servlets.controladores;

import MVC.modelo.entidades.beans.datatypes.Vehiculo;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import MVC.modelo.logica.FabricaLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicolas
 */
public class ControladorVehiculo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "index";
        }

        switch (accion) {
            case "index":
                index_get(request, response);
                break;

            case "agregar":
                agregar_get(request, response);
                break;

            case "modificar":
                modificar_get(request, response);
                break;

            case "eliminar":
                eliminar_get(request, response);
                break;

            case "ver":
                ver_get(request, response);
                break;

            case "trasladar":
                trasladar_get(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "index";
        }

        switch (accion) {
            
            case "agregar":
                agregar_get(request, response);
                break;

            case "modificar":
                modificar_get(request, response);
                break;

            case "eliminar":
                eliminar_get(request, response);
                break;

            case "trasladar":
                trasladar_get(request, response);
                break;
        }
    }

    protected void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setAttribute("vehiculos", FabricaLogica.getLogicaVehiculo().ListarVehiculo());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp");
            return;
        }
    }

    protected void agregar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/agregar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp");
            return;
        }
    }

    protected void modificar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehiculoModificar", ((ArrayList<Vehiculo>) request.getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))));
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/modificar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.removeAttribute("vehiculoModificar");
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp");
            return;
        }
    }

    protected void eliminar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehiculoEliminar", ((ArrayList<Vehiculo>) request.getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))));
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/eliminar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.removeAttribute("vehiculoEliminar");
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp");
            return;
        }
    }

    protected void ver_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehiculoVer", ((ArrayList<Vehiculo>) request.getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))));
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/ver.jsp").forward(request, response);
        } catch (Exception ex) {
            request.removeAttribute("vehiculoVer");
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp");
            return;
        }
    }

    protected void trasladar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehiculoTrasladar", ((ArrayList<Vehiculo>) request.getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))));
            response.sendRedirect("WEB-INF/vistas/cliente/trasladar.jsp");
        } catch (Exception ex) {
            request.removeAttribute("vehiculoTrasladar");
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/trasladar.jsp");
            return;
        }
    }
    
    protected void agregar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            Vehiculo _vehiculo = new Vehiculo();
            _vehiculo.setMatricula(request.getParameter("matricula"));
            _vehiculo.setDescripcion(request.getParameter("descripcion"));
            _vehiculo.setTipo(request.getParameter("tipo"));
            
            try {
                _vehiculo.setPrecioAlquilerDiario(Double.parseDouble(request.getParameter("precioAlquilerDiario")));
            } catch (Exception ex) {
                throw new Exception("Precio alquiler diario debe ser numérico");
            }
            
            try {
                _vehiculo.setSucursalPertenece(FabricaLogica.getLogicaSucursal().BuscarSucursal(Integer.parseInt(request.getParameter("sucursal"))));
            } catch (ExcepcionPersonalizada ex){
                throw ex;
            } catch (Exception ex) {
                throw new Exception("Codigo de sucursal debe ser un numero");
            }
            
            FabricaLogica.getLogicaVehiculo().AgregarVehiculo(_vehiculo);
            
            response.sendRedirect("WEB-INF/vistas/vehiculo/agregar.jsp");
            
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/agregar.jsp");
            return;
        }
    }

    protected void modificar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Vehiculo _vehiculo = (Vehiculo)request.getAttribute("vehiculoModificar");
            _vehiculo.setDescripcion(request.getParameter("descripcion"));
            _vehiculo.setTipo(request.getParameter("tipo"));
            
            try {
                _vehiculo.setPrecioAlquilerDiario(Double.parseDouble(request.getParameter("precioAlquilerDiario")));
            } catch (Exception ex) {
                throw new Exception("Precio alquiler diario debe ser numérico");
            }
            
            try {
                _vehiculo.setSucursalPertenece(FabricaLogica.getLogicaSucursal().BuscarSucursal(Integer.parseInt(request.getParameter("sucursal"))));
            } catch (ExcepcionPersonalizada ex){
                throw ex;
            } catch (Exception ex) {
                throw new Exception("Codigo de sucursal debe ser un numero");
            }
            
            FabricaLogica.getLogicaVehiculo().ModificarVehiculo(_vehiculo);
            
            response.sendRedirect("WEB-INF/vistas/vehiculo/modificar.jsp");
        } catch (Exception ex) {
            request.removeAttribute("vehiculoModificar");
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp");
            return;
        }
    }

    protected void eliminar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FabricaLogica.getLogicaVehiculo().EliminarVehiculo(((Vehiculo)request.getAttribute("vehiculo")).getMatricula());
            
            response.sendRedirect("WEB-INF/vistas/vehiculo/eliminar.jsp");
        } catch (Exception ex) {
            request.removeAttribute("vehiculoEliminar");
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp");
            return;
        }
    }

    protected void trasladar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehiculoTrasladar", ((ArrayList<Vehiculo>) request.getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))));
            request.getRequestDispatcher("WEB-INF/vistas/cliente/trasladar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.removeAttribute("vehiculoTrasladar");
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp");
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
