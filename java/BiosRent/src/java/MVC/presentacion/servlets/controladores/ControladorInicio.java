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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicolas
 */
public class ControladorInicio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
<<<<<<< HEAD

        
    
=======
    private void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp").forward(request, response);
    }
>>>>>>> bcab0398e825bf7dd60379433b9948a286b2bca5

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
<<<<<<< HEAD
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
            response.sendRedirect("vehiculo?accion=ver");
        }
    }

    private void logIn_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String nomUser = request.getParameter("NombreUser");
            String passUser = request.getParameter("Pass");
            Empleado unEmp = FabricaLogica.getLogicaEmpleado().logueo(nomUser, passUser);
            if(unEmp !=null){
                request.getSession().setAttribute("empleadoLogueado",unEmp);
                
                Alquiler unAlquiler = new Alquiler();
              //  unAlquiler.setEmpleado(unEmp);
                request.getSession().setAttribute("alquiler", unAlquiler);
                response.sendRedirect("vehiculo?accion=ver");
                        
            }else{
                request.setAttribute("mensaje", "Las credenciales ingresadas no son correctas");
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
    
=======

        if (accion == null) {
            accion = "index";
        }

        switch (accion) {
            case "index":
                index_get(request, response);
                break;
        }
    }
>>>>>>> bcab0398e825bf7dd60379433b9948a286b2bca5
}
