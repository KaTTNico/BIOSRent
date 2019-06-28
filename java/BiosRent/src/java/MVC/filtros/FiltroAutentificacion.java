/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.filtros;

import MVC.modelo.entidades.beans.datatypes.Empleado;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan
 */
public class FiltroAutentificacion implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession sesion = ((HttpServletRequest)request).getSession();
        Empleado unEmp = (Empleado)sesion.getAttribute("empleadoLogueado");
        
        if(unEmp != null){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendRedirect("empleado?accion=logIn");
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
