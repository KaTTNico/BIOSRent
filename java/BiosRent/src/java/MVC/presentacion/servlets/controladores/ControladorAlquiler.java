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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;

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
        try {
            String usuario = request.getParameter("NombreUser");

            //obtener vehiculos disponibles
            List<Vehiculo> vehiculos = FabricaLogica.getLogicaAlquiler().listarVehiculosDisponibles(usuario);
            request.setAttribute("vehiculos", vehiculos);

            if (!vehiculos.isEmpty()) {
                request.setAttribute("mensaje", "Cantidad de vehiculos: " + vehiculos.size());
            } else {
                request.setAttribute("mensaje", "No hay vehiculos disponibles.");
            }

        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "No se pudo listar los vehiculos.");
        }
        request.getRequestDispatcher("WEB-INF/vistas/alquiler/index.jsp").forward(request, response);
    }

    public void agregar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //obtener clientes as JSON
            List<Cliente> clientes = FabricaLogica.getLogicaCliente().ListaCompleta();
            String JSONClientes = "{clientes:[";
            int counter = 0;

            for (Cliente cliente : clientes) {
                JSONClientes += "{ci:'" + cliente.getCI() + "',nombreCompleto:'" + cliente.getNombreCompleto() + "'";
                JSONClientes += "}" + ((clientes.indexOf(cliente) == clientes.size() - 1) ? "" : ",");
            }

            JSONClientes += ("]}");
            request.setAttribute("clientes", JSONClientes);

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
        try {
            //obtener clientes as JSON
            List<Cliente> clientes = FabricaLogica.getLogicaCliente().ListaCompleta();
            String JSONClientes = "{clientes:[";
            int counter = 0;

            for (Cliente cliente : clientes) {
                JSONClientes += "{ci:'" + cliente.getCI() + "',nombreCompleto:'" + cliente.getNombreCompleto() + "'";
                JSONClientes += "}" + ((clientes.indexOf(cliente) == clientes.size() - 1) ? "" : ",");
            }

            JSONClientes += ("]}");
            request.setAttribute("clientes", JSONClientes);

        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "No se pudo listar los vehiculos.");
        }
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
                agregar_post(request, response);
                break;
            case "devolver":
                //devolver_post(request, response);
                break;
        }
    }

    public void agregar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date fechaAlquiler = new Date();
        int cantidadDias = 0;
        double costoSeguro = 0d;
        double depositoGarantia = obtenerTarifa("garantia");
        double total = 0d;

        int sucursalCodigo = 0;

        if (Boolean.parseBoolean(request.getParameter("contratoSeguro"))) {
            costoSeguro = obtenerTarifa("seguro");
        }

        try {
            cantidadDias = Integer.parseInt(request.getParameter("cantidadDias"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La cantidad de dias no es válida.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

            return;
        }

        try {
            sucursalCodigo = Integer.parseInt(request.getParameter("sucursal"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La sucursal no es válida.");

            request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

            return;
        }

        try {
            //CLIENTE
            int cedula = 0;

            try {
                cedula = Integer.parseInt(request.getParameter("cliente"));
            } catch (NumberFormatException ex) {
                request.setAttribute("mensaje", "¡ERROR! La cedula no es válida.");

                request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);

                return;
            }
            Cliente cliente = FabricaLogica.getLogicaCliente().buscar(cedula);
            if (cliente == null) {
                request.setAttribute("mensaje", "¡ERROR! El cliente no existe.");
                request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);
                return;
            }

            //VEHICULO
            Vehiculo vehiculo = FabricaLogica.getLogicaVehiculo().BuscarVehiculo(request.getParameter("matricula"));
            if (vehiculo == null) {
                request.setAttribute("mensaje", "¡ERROR! El vehiculo no existe.");
                request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);
                return;
            }

            //SUCURSAL
            Sucursal sucursal = FabricaLogica.getLogicaSucursal().BuscarSucursal(sucursalCodigo);
            if (sucursal == null) {
                request.setAttribute("mensaje", "¡ERROR! La sucursal no existe.");
                request.getRequestDispatcher("WEB-INF/vistas/alquiler/agregar.jsp").forward(request, response);
                return;
            }

            //CALCULAR TOTAL
            total = vehiculo.getPrecioAlquilerDiario() * cantidadDias + costoSeguro;

            //ALQUILER
            Alquiler alquiler = new Alquiler(0, fechaAlquiler, cantidadDias, costoSeguro, total, depositoGarantia, cliente, sucursal, vehiculo);
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

    //Operaciones 
    public double obtenerTarifa(String valorBuscado) {
        ServletContext contextAplication = getServletContext();
        File archivo = new File(contextAplication.getRealPath("/tarifas.txt"));
        double valor = 0d;

        if (archivo.exists() && !archivo.isDirectory()) {
            try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {

                String linea;
                Pattern patron = Pattern.compile("(?<=" + valorBuscado + " )\\d+");

                while ((linea = br.readLine()) != null) {
                    Matcher m = patron.matcher(linea);
                    try {
                        if (m.find()) {
                            valor = Double.parseDouble(m.group(0));
                            return valor;
                        }
                    } catch (NumberFormatException ex) {
                        valor = 0d;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al leer la tarifa de " + valorBuscado + ".");
            }
        }
        return valor;
    }
}
