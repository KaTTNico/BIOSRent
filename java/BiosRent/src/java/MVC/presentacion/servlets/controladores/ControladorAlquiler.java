/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.presentacion.servlets.controladores;

import MVC.modelo.entidades.beans.excepciones.*;
import MVC.modelo.logica.FabricaLogica;
import MVC.modelo.entidades.beans.datatypes.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicolas
 */
public class ControladorAlquiler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*GETS*/
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
            case "devolver":
                devolver_get(request, response);
                break;
            case "ver":
                ver_get(request, response);
                break;
        }
    }

    public void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/alquiler/index.jsp").forward(request, response);
    }

    public void agregar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //obtener vehiculos disponibles
            List<Vehiculo> vehiculos = FabricaLogica.getLogicaAlquiler().listarVehiculosDisponibles();
            request.setAttribute("vehiculos", vehiculos);

            if (!vehiculos.isEmpty()) {
                request.setAttribute("mensaje", "Cantidad de vehiculos: " + vehiculos.size());
            } else {
                request.setAttribute("mensaje", "No hay vehiculos disponibles.");
            }

            //obtener clientes as JSON
            List<Cliente> clientes = FabricaLogica.getLogicaCliente().ListaCompleta();
            String clientesStringified = "{clientes:[";
            int counter = 0;
            for (Cliente cliente : clientes) {
                
                clientesStringified += "{ci:'" + cliente.getCI() + "',nombreCompleto:'" + cliente.getNombreCompleto() + "'";
                clientesStringified += "}" + ((clientes.indexOf(cliente) == clientes.size() - 1) ? "" : ",");
            }
            clientesStringified += ("]}");
            request.setAttribute("clientes", clientesStringified);

        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "No se pudo listar los vehiculos.");
        }

        String mensajeSesion = (String) request.getSession().getAttribute("mensaje");

        if (mensajeSesion != null) {
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje == null) {
                request.setAttribute("mensaje", mensajeSesion);
            } else {
                request.setAttribute("mensaje", mensajeSesion + "<br /><br />" + mensaje);
            }
            request.getSession().removeAttribute("mensaje");
        }
        request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);
    }

    public void devolver_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/alquiler/devolver.jsp").forward(request, response);
    }

    public void ver_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/alquiler/ver.jsp").forward(request, response);
    }

    /*POSTS*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "agregar":
                //agregar_post(request, response);
                break;
            case "devolver":
                //devolver_post(request, response);
                break;
        }
    }

    public void agregar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date fechaAlquiler;
        int cantidadDias = 0;
        Double costoSeguro = 0d;
        Double total = 0d;
        Double depositoGarantia = 0d;
        int sucursal = 0;

        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/DD");
            fechaAlquiler = parser.parse(request.getParameter("fechaAlquiler"));
        } catch (ParseException ex) {
            request.setAttribute("mensaje", "¡ERROR! La fecha no es válida.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

            return;
        }

        try {
            cantidadDias = Integer.parseInt(request.getParameter("cantidadDias"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La cantidad de dias no es válida.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

            return;
        }

        try {
            costoSeguro = Double.parseDouble(request.getParameter("costoSeguro"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El costo del seguro no es válido.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

            return;
        }

        try {
            total = Double.parseDouble(request.getParameter("total"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El total no es válido.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

            return;
        }

        try {
            depositoGarantia = Double.parseDouble(request.getParameter("depositoGarantia"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El deposito en garantia no es válido.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

            return;
        }

        try {
            sucursal = Integer.parseInt(request.getParameter("sucursal"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La sucursal no es válida.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

            return;
        }

        try {
//            Cliente cliente = FabricaLogica.getLogicaCliente().buscar(request.getParameter("cedula"));
//            Vehiculo vehiculo = FabricaLogica.getLogicaVehiculo().buscar(request.getParameter("matricula"));
//            if (cliente == null) {
//                request.setAttribute("mensaje", "¡ERROR! El cliente no existe.");
//                request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);
//                return;
//            }
//
//            if (vehiculo == null) {
//                request.setAttribute("mensaje", "¡ERROR! El vehiculo no existe.");
//                request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);
//                return;
//            }

            Alquiler alquiler = new Alquiler();
            FabricaLogica.getLogicaAlquiler().alta(alquiler);
            request.getSession().setAttribute("mensaje", "¡Alquiler agregado con éxito!");

            response.sendRedirect("alquiler");
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al agregar el alquiler.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);
        }
    }
}
