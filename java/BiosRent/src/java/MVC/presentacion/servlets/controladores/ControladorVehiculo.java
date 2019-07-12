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
                agregar_post(request, response);
                break;

            case "modificar":
                modificar_post(request, response);
                break;

            case "eliminar":
                eliminar_post(request, response);
                break;

            case "trasladar":
                trasladar_post(request, response);
                break;
        }
    }

    protected void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.removeAttribute("vehiculo");
            request.getSession().setAttribute("vehiculos", FabricaLogica.getLogicaVehiculo().ListarVehiculo());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/index.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp");
            return;
        }
        
        String msjSession = (String) request.getSession().getAttribute("mensaje");

        if (msjSession != null) {
            String msj = (String)request.getAttribute("mensaje");
            if(msj == null){
                request.setAttribute("mesaje", msjSession);
                
            }else{
                request.setAttribute("mensaje", msjSession + "<br /> <br />"+ msj);
            }
             request.getSession().removeAttribute("mensaje");
        }
    }

    protected void agregar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/agregar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=index").forward(request, response);
            return;
        }
    }

    protected void modificar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehiculo", ((ArrayList<Vehiculo>) request.getSession().getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))-1));
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/modificar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=index").forward(request, response);
        }
    }

    protected void eliminar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehiculo", ((ArrayList<Vehiculo>) request.getSession().getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))-1));
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/eliminar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=index").forward(request, response);
        }
    }

    protected void ver_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehiculo", ((ArrayList<Vehiculo>) request.getSession().getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))-1));
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/ver.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=index").forward(request, response);
            return;
        }
    }

    protected void trasladar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Vehiculo _vehiculoTraslado = ((ArrayList<Vehiculo>) request.getSession().getAttribute("vehiculos")).get(Integer.parseInt(request.getParameter("vehiculoIndex"))-1);
            
            if(_vehiculoTraslado.getSucursalPertenece() == null)
                throw new Exception("No se puede trasladar un vehiculo alquilado");
            
            request.setAttribute("vehiculo", _vehiculoTraslado);
            request.setAttribute("sucursales", FabricaLogica.getLogicaSucursal().ListarSucursal());
            request.getRequestDispatcher("WEB-INF/vistas/vehiculo/trasladar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=index").forward(request, response);
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
            
            FabricaLogica.getLogicaVehiculo().AgregarVehiculo(_vehiculo);
            
            response.sendRedirect("vehiculo?accion=agregar");
            
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=agregar").forward(request, response);
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
            
            response.sendRedirect("vehiculo?accion=modificar");
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=modificar").forward(request, response);
            return;
        }
    }

    protected void eliminar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            if(request.getParameter("decition").equals("yes"))
                FabricaLogica.getLogicaVehiculo().EliminarVehiculo(((Vehiculo)request.getAttribute("vehiculo")).getMatricula());
            
            response.sendRedirect("vehiculo?accion=index");
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=eliminar").forward(request, response);
            return;
        }
    }

    protected void trasladar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Vehiculo _vehiculoTraslado = ((Vehiculo)request.getAttribute("vehiculo"));
            _vehiculoTraslado.setSucursalPertenece(FabricaLogica.getLogicaSucursal().BuscarSucursal((int)(request.getAttribute("sucursalTraslado"))));
            
            response.sendRedirect("vehiculo?accion=trasladar");
        } catch (Exception ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("vehiculo?accion=trasladar").forward(request, response);
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
