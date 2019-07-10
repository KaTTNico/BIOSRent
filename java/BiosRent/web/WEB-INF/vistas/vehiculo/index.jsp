<%-- 
    Document   : index
    Created on : Jun 8, 2019, 10:39:00 AM
    Author     : Nicolas
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%!int vehiculoIndex = 1;%>

<t:paginaMaestra title="Agregar Vehiculo">
    <jsp:body>
        <fmt:setLocale value="en-US" />

        <a href="vehiculo?accion=agregar"><img src="imagenes/iconos/AddVehicle.png" alt="Agregar" title="Agregar" height="100" width="100"></a><br/><br/>

        <table>
            <tr>
                <th>Matricula</th>
                <th>Tipo</th>
                <th>Descripcion</th>
                <th>PrecioAlquilerDiario</th>
                <th>Sucursal</th>
            </tr>

            <c:forEach items="${vehiculos}" var="vehiculo">
                <tr>                    
                    <td class="texto-centro">${vehiculo.matricula}</td>
                    <td>${vehiculo.tipo}</td>
                    <td>${vehiculo.descripcion}</td>
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${vehiculo.precioAlquilerDiario}" />
                    </td>
                    <td>${(vehiculo.sucursalPertenece.codigo == null)? "Alquilado" : vehiculo.sucursalPertenece.codigo}</td>
                    <td>
                        <a href="vehiculo?accion=modificar&indexVehiculo=${vehiculoIndex}"><img src="imagenes/iconos/glyphicons-31-pencil.png" alt="Modificar" title="Modificar"></a>&nbsp;&nbsp;
                        <a href="vehiculo?accion=ver&indexVehiculo=${vehiculoIndex}"><img src="imagenes/iconos/glyphicons-52-eye-open.png" alt="Ver" title="Ver" ></a>&nbsp;&nbsp;
                        <a href="vehiculo?accion=eliminar&indexVehiculo=${vehiculoIndex}"><img src="imagenes/iconos/glyphicons-192-minus-sign.png" alt="Eliminar" title="Eliminar" ></a>&nbsp;&nbsp;
                        <a href="vehiculo?accion=trasladar&indexVehiculo=${vehiculoIndex}"><img src="imagenes/iconos/MoverVehiculo.png" alt="Trasladar" title="Trasladar" height="30" width="30"></a>
                    </td>
                    <c:set var="vehiculoIndex" value="${vehiculoIndex + 1}" scope="page"/>
                </tr>

            </c:forEach>
        </table>

        <p><a href="inicio">Volver...</a></p>
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
