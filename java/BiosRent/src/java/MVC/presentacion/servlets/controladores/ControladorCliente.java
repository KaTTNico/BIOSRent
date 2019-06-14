/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.presentacion.servlets.controladores;

import MVC.modelo.entidades.beans.datatypes.Cliente;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import MVC.modelo.logica.FabricaLogica;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicolas
 */
public class ControladorCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void index_get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void agregar_get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/cliente/registroCliente.jsp").forward(request, response);
    }

    public void agregar_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ci;
        try {
            ci = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "La cédula no es válida.");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/registroCliente.jsp");
            return;
        }
        String nombreCliente = request.getParameter("NombreCliente");
        String Tel = request.getParameter("Telefono");

        if (!(Tel.matches("^09[0-9]{7}||2[0-9]{7}$"))) {
            request.setAttribute("mensaje", "Error, el formato del telefono no es correcto");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/registroCliente.jsp").forward(request, response);
            return;
        }
        Cliente unCliente = new Cliente(ci, nombreCliente, Tel);

        try {
            FabricaLogica.getLogicaCliente().agregar(unCliente);
            request.getSession().setAttribute("mensaje", "Cliente agregado con éxito");
            response.sendRedirect("ControladorCliente");
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "Error " + ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/cliente/registroCliente.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", "Error, se produjo un error al agregar un cliente");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/registroCliente.jsp").forward(request, response);
        }

    }

    public void modificar_get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ci;
        try {
            ci = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "La cédula no es válida.");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/modificar.jsp");
            return;
        }
        String nombreCliente = request.getParameter("NombreCliente");
        String Tel = request.getParameter("Telefono");

        if (!(Tel.matches("^09[0-9]{7}||2[0-9]{7}$"))) {
            request.setAttribute("mensaje", "Error, el formato del telefono no es correcto");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/modificar.jsp").forward(request, response);
            return;
        }

        try {
            Cliente unCliente = FabricaLogica.getLogicaCliente().buscar(ci);
            if (unCliente != null) {
                request.setAttribute("cliente", unCliente);
                request.setAttribute("mensaje", "Cliente encontrado");
            } else {
                request.setAttribute("mensaje", "Error, no se encontro un cliente con la cédula ingresada");
                request.setAttribute("ocultarform", true);
            }

        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "Error " + ex.getMessage());

        } catch (Exception ex) {
            request.setAttribute("mensaje", "Error, se produjo un error al buscar un cliente");

        }
        request.getRequestDispatcher("WEB-INF/vistas/cliente/modificar.jsp").forward(request, response);

    }

    public void modificar_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ci;
        try {
            ci = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "La cédula no es válida.");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/modificar.jsp");
            return;
        }
        String nombreCliente = request.getParameter("NombreCliente");
        String Tel = request.getParameter("Telefono");

        if (!(Tel.matches("^09[0-9]{7}||2[0-9]{7}$"))) {
            request.setAttribute("mensaje", "Error, el formato del telefono no es correcto");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/modificar.jsp").forward(request, response);
            return;
        }
        Cliente unCliente = new Cliente(ci, nombreCliente, Tel);

        try {
            FabricaLogica.getLogicaCliente().modificar(unCliente);
            request.getSession().setAttribute("mensaje", "Cliente modificado con éxito");
            response.sendRedirect("ControladorCliente");

        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "Error " + ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/cliente/modificar.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("mensaje", "Error, se produjo un error al modificar un cliente");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/modificar.jsp").forward(request, response);

        }

    }

    public void eliminar_get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ci;
        try {
            ci = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "La cédula no es válida.");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/eliminar.jsp");
            return;
        }
        String nombreCliente = request.getParameter("NombreCliente");
        String Tel = request.getParameter("Telefono");

        if (!(Tel.matches("^09[0-9]{7}||2[0-9]{7}$"))) {
            request.setAttribute("mensaje", "Error, el formato del telefono no es correcto");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/eliminar.jsp").forward(request, response);
            return;
        }

        try {
            Cliente unCliente = FabricaLogica.getLogicaCliente().buscar(ci);
            if (unCliente != null) {
                request.setAttribute("cliente", unCliente);
                request.setAttribute("mensaje", "Cliente encontrado");
            } else {
                request.setAttribute("mensaje", "Error, no se encontro un cliente con la cédula ingresada");
                request.setAttribute("ocultarform", true);
            }

        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "Error " + ex.getMessage());

        } catch (Exception ex) {
            request.setAttribute("mensaje", "Error, se produjo un error al buscar un cliente");

        }
        request.getRequestDispatcher("WEB-INF/vistas/cliente/eliminar.jsp").forward(request, response);
    }

    public void eliminar_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ci;
        try {
            ci = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "La cédula no es válida.");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/eliminar.jsp");
            return;
        }
        try {
            FabricaLogica.getLogicaCliente().eliminar(ci);
            request.getSession().setAttribute("mensaje", "Eliminación realizada con éxito");
            response.sendRedirect("ControladorCliente");
        }catch(ExcepcionPersonalizada ex){
            request.setAttribute("mensaje", "Error " + ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/cliente/eliminar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", "Error, se produjo un error al eliminar un cliente");
            request.getRequestDispatcher("WEB-INF/vistas/cliente/eliminar.jsp").forward(request, response);
                    
        }
    }
}
