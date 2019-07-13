/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.presentacion.servlets.controladores;

import MVC.modelo.entidades.beans.datatypes.Alquiler;
import MVC.modelo.entidades.beans.datatypes.Empleado;
import MVC.modelo.entidades.beans.excepciones.ExcepcionPersonalizada;
import MVC.modelo.logica.FabricaLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicolas
 */
public class ControladorEmpleado extends HttpServlet {

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
        String accion = request.getParameter("accion");
                if(accion ==null) accion = "logIn";
                switch(accion){
                    case "logIn":
                        logIn_get(request,response);
                        break;
                }
        
      
    }

     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch(accion){
            case "logIn":
                logIn_post(request,response);
                break;
            case "logOut":
                logOut_post(request,response);
                break;
        }
        
    }

    private void logIn_get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(request.getSession().getAttribute("empleadoLogueado")== null){
            request.getRequestDispatcher("WEB-INF/vistas/empleado/login.jsp").forward(request, response);
            
        }else{
            response.sendRedirect("cliente?accion=index");
        }
    }

    private void logIn_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String nomUser = request.getParameter("NombreUser");
            String passUser = request.getParameter("Pass");
            Empleado unEmp = FabricaLogica.getLogicaEmpleado().logueo(nomUser, passUser);
            if(unEmp !=null){
                request.getSession().setAttribute("empleadoLogueado",unEmp);
                
                
                response.sendRedirect("inicio?accion=index");
                        
            }else{
                request.setAttribute("mensaje", "Error, las credenciales ingresadas no son correctas.");
                request.getRequestDispatcher("WEB-INF/vistas/empleado/login.jsp").forward(request, response);
            }
        }catch(ExcepcionPersonalizada ex){
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/vistas/empleado/login.jsp").forward(request, response);
        }catch (Exception e) {
            request.setAttribute("mensaje", "No se pudo loguear el empleado");
            request.getRequestDispatcher("WEB-INF/vistas/empleado/login.jsp").forward(request, response);
        }
    }

    private void logOut_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("empleadoLogueado");
        request.getSession().removeAttribute("alquiler");
        response.sendRedirect("empleado?accion=logIn");
        
    }



}
