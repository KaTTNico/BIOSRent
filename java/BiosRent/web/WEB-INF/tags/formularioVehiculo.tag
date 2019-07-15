<%-- 
    Document   : formularioVehiculo
    Created on : Jul 10, 2019, 10:07:21 PM
    Author     : fmora
--%>

<%@tag description="Formulaio para las vistas agregar y modificar vehiculo" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@attribute name="readOnly" %>
<%@attribute name="matriculaReadOnly" %>

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<jsp:useBean id="vehiculo" class="MVC.modelo.entidades.beans.datatypes.Vehiculo" scope="session"></jsp:useBean>
<jsp:setProperty name="vehiculo" property="matricula" value="${empty param.matricula ? vehiculo.matricula : param.matricula}"/>
<jsp:setProperty name="vehiculo" property="descripcion" value="${empty param.descripcion ? vehiculo.descripcion : param.descripcion}"/>
<jsp:setProperty name="vehiculo" property="tipo" value="${empty param.tipo ? vehiculo.tipo : param.tipo}"/>

<fmt:setLocale value="en-US" />

<table class="contenido-tabla">
    <tr>
        <td><label for="txtMatricula" class="labelForm" width="170">Matricula</label></td>
        <td><input id="txtMatricula" name="matricula" value="${empty param.matricula ? vehiculo.matricula : param.matricula}" <c:if test="${readOnly || matriculaReadOnly}">disabled="disabled"</c:if> type="text" class="txt-box-clientes"/></td>
        </tr>

        <tr>
            <td><label for="txtDescripcion" class="labelForm">Descripción</label></td>
            <td><textarea id="txtDescripcion" name="descripcion" class="txt-bsox-clientes" <c:if test="${readOnly}">disabled="disabled"</c:if> style="width: 170px;">${empty param.descripcion ? vehiculo.descripcion : param.descripcion}</textarea></td>
        </tr>

        <tr>
            <td><label for="cbTipo" class="labelForm">Tipo</label></td>
            <td><select id="cbTipo" name="tipo" class="txt-box-clientes" <c:if test="${readOnly}">disabled="disabled"</c:if> style="width: 170px;">
                <option <c:if test="${(empty param.tipo ? vehiculo.tipo : param.tipo).equals('AUTO')}">selected</c:if>>AUTO</option>
                <option <c:if test="${(empty param.tipo ? vehiculo.tipo : param.tipo).equals('CAMIONETA')}">selected</c:if>>CAMIONETA</option>
                <option <c:if test="${(empty param.tipo ? vehiculo.tipo : param.tipo).equals('OTRO')}">selected</c:if>>OTROS</option>
                </select>
            </td>
        </tr>

        <tr>
            <td><label for="nudPrecio" class="labelForm">Precio Alquiler Diario</label></td>
          <td><input id="nudPrecio" name="precioAlquilerDiario" type="text" <c:if test="${readOnly}">disabled="disabled"</c:if> value="${empty param.precioAlquilerDiario ? vehiculo.precioAlquilerDiario : param.precioAlquilerDiario}"    class="txt-box-clientes" width="170"/></td>
        </tr>
        <tr>
            <td><label for="imgVehiculo" class="labelForm">Foto:</label></td>
            <td><input id="imgVehiculo" name="imgVehiculo" type="file" class="txt-box-clientes" <c:if test="${readOnly}">disabled="disabled"</c:if>/></td>
        </tr>
        <tr>
            <td colspan="2">
                <img src="imagenes/vehiculos/${empty param.matricula ? vehiculo.matricula : param.matricula}.png" alt="${empty param.matricula ? vehiculo.matricula : param.matricula}" onerror="this.src='imagenes/no-disponible.png'" style="vertical-align: middle;" />
        </td>
    </tr>

</table>