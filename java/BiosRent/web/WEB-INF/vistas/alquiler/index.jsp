<%-- 
    Document   : index
    Created on : Jun 8, 2019, 10:43:59 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:paginaMaestra title="Menu alquiler">
    <jsp:body>
        <ul>
            <li><a href="alquiler?accion=agregar">Agregar</a></li>
            <li><a href="alquiler?accion=devolver">Devolver</a></li>
            <li><a href="alquiler?accion=ver">Ver</a></li>
        </ul>
        <p>Vehiculos disponibles:</p>
        <table>
            <tr>
                <th>Matricula</th><th>Tipo</th><th>Descripcion</th><th>PrecioAlquilerDiario</th><th>Sucursal</th>
            </tr>

            <c:forEach items="${vehiculos}" var="vehiculo">
                <tr>
                    <td>${vehiculo.matricula}</td>
                    <td>${vehiculo.tipo}</td>
                    <td>${vehiculo.descripcion}</td>
                    <td>
                        <fmt:formatNumber type="number" pattern="0.00" value="${vehiculo.precioAlquilerDiario}" />
                    </td>
                    <td>${vehiculo.sucursalPertenece.codigo}</td>
                    <td>
                        <a href="alquiler?accion=agregar&matricula=${vehiculo.matricula}"><img src="imagenes/iconos/glyphicons-191-plus-sign.png" alt="Alquilar" title="Alquilar" ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:paginaMaestra>