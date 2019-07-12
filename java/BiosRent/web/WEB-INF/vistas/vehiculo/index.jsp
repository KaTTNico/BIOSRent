<%-- 
    Document   : index
    Created on : Jun 8, 2019, 10:39:00 AM
    Author     : Nicolas
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%!int vehiculoIndex;%>

<t:paginaMaestra title="Vehiculos">
    <jsp:body>
        <fmt:setLocale value="en-US" />

        <a href="vehiculo?accion=agregar"><img src="imagenes/iconos/addVehiculo.png" alt="Agregar" title="Agregar" height="70" width="90"></a><br/>
        
        <table class="contenido-tabla">
            <tr>
                <th>Matricula</th>
                <th>Tipo</th>
                <th>Descripcion</th>
                <th>PrecioAlquilerDiario</th>
                <th>Sucursal</th>
            </tr>

            <c:set var="vehiculoIndex" value="${vehiculoIndex + 1}" scope="page"/>
            
            <c:forEach items="${vehiculos}" var="vehiculo">
                <tr>                    
                    <td class="texto-centro">${vehiculo.matricula}</td>
                    <td>${vehiculo.tipo}</td>
                    <td>${vehiculo.descripcion}</td>
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${vehiculo.precioAlquilerDiario}" />
                    </td>
                    <td>${(vehiculo.sucursalPertenece == null)? "Alquilado" : vehiculo.sucursalPertenece.nombre}</td>
                    <td>
                        <a href="vehiculo?accion=modificar&vehiculoIndex=${vehiculoIndex}"><img src="imagenes/iconos/editVehiculo.png" alt="Modificar" title="Modificar..." height="50" width="50"></a>&nbsp;&nbsp;
                        <a href="vehiculo?accion=ver&vehiculoIndex=${vehiculoIndex}"><img src="imagenes/iconos/verUser.png" alt="Ver" title="Ver..." height="50" width="50"></a>&nbsp;&nbsp;
                        <a href="vehiculo?accion=eliminar&vehiculoIndex=${vehiculoIndex}"><img src="imagenes/iconos/removeVehiculo.png" alt="Eliminar" title="Eliminar..." height="50" width="50"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="vehiculo?accion=trasladar&vehiculoIndex=${vehiculoIndex}"><img src="imagenes/iconos/trasladarVehiculo.png" alt="Trasladar" title="Trasladar..." height="50" width="100"></a>
                    </td>
                    <c:set var="vehiculoIndex" value="${vehiculoIndex + 1}" scope="page"/>
                </tr>

            </c:forEach>
        </table>

        <p><a href="inicio">Volver a inicio...</a></p>
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
