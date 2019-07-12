<%-- 
    Document   : trasladar
    Created on : Jun 8, 2019, 10:43:15 AM
    Author     : Nicolas
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:paginaMaestra title="Trasladar Vehiculo">
    <jsp:body>

        <form action="vehiculo?action=trasladar" method="post">
            <table class="contenido-tabla">
                <tr>
                    <td>Vehiculo:</td>
                    <td>${vehiculo.matricula}</td>
                </tr>
                <tr>
                    <td>Sucursal actual: </td>
                    <td>${vehiculo.sucursalPertenece.nombre}</td>
                </tr>
                <tr>
                    <td>
                        <label for="sucursalSelect">Sucursal a trasladar:</label>
                    </td>
                    <td>
                        <select id="sucursalSelect" name="sucursalTraslado">
                            <c:forEach var="sucursal" items="${sucursales}">
                                <option value="${sucursal.codigo}">${sucursal.nombre}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Trasladar" class="submitFormulario"/>
        </form>

        <p><a href="vehiculo?action=index">Volver a vehiculos...</a></p>

        <p><a href="inicio">Volver a inicio...</a></p>

        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
