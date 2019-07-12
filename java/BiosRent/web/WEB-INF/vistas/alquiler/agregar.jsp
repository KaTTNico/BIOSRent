<%-- 
    Document   : agregar
    Created on : Jun 8, 2019, 10:42:13 AM
    Author     : Nicolas
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Alquiler">
    <jsp:body>
        <fmt:setLocale value="en-US"/>
        <table class="listado" >
            <tr>
                <th>Matricula</th><th>Tipo</th><th>Descripcion</th><th>PrecioAlquilerDiario</th><th>Sucursal</th>
            </tr>

            <c:forEach items="${vehiculos}" var="vehiculo">
                <tr>
                    <td class="texto-centro">${vehiculo.matricula}</td>
                    <td>${vehiculo.tipo}</td>
                    <td>${vehiculo.descripcion}</td>
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${vehiculo.precioAlquilerDiario}" />
                    </td>
                    <td>${(vehiculo.sucursalPertenece.codigo == null)? "N/A" : vehiculo.sucursalPertenece.codigo}</td>
                    <td>
                        <a href="alquiler?accion=alquilar&matricula=${vehiculo.matricula}"><img src="imagenes/iconos/glyphicons-191-plus-sign.png" alt="Alquilar" title="Alquilar" ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="inicio">Volver...</a></p>
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>