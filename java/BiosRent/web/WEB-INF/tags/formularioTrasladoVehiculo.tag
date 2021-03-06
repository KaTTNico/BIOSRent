<%-- 
    Document   : formularioTrasladoVehiculo
    Created on : Jul 13, 2019, 2:45:18 PM
    Author     : fmora
--%>

<%@tag description="Formulaio para las vistas agregar y modificar vehiculo" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="contenido-tabla">
    <c:if test="${!empty vehiculo.matricula}"> 
        <tr>
            <td>Vehiculo:</td>
            <td>${vehiculo.matricula}</td>
        </tr>
    </c:if>
    <tr>
        <td>Sucursal actual: </td>
        <td>${empty vehiculo.sucursalPertenece ? 'No asignado' : vehiculo.sucursalPertenece.nombre}</td>
    </tr>
    <tr>
        <td>
            <label for="sucursalSelect">Sucursal a trasladar:</label>
        </td>
        <td>
            <select id="sucursalSelect" name="sucursalTraslado" class="txt-box-clientes">
                <c:forEach var="sucursal" items="${sucursales}">
                    <option value="${sucursal.codigo}" <c:if test="${empty param.sucursalTraslado ? vehiculo.sucursalPertenece.codigo.equals(sucursal.codigo) : param.sucursalTraslado.equals(sucursal.codigo.toString())}">selected</c:if>>${sucursal.nombre}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
</table>