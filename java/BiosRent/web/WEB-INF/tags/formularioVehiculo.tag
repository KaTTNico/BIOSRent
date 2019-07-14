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

<jsp:useBean id="vehiculo" class="MVC.modelo.entidades.beans.datatypes.Vehiculo" scope="session"></jsp:useBean>
<jsp:setProperty name="vehiculo" property="matricula" value="${empty param.matricula ? vehiculo.matricula : param.matricula}"/>
<jsp:setProperty name="vehiculo" property="precioAlquilerDiario" value="${empty param.precioAlquilerDiario ? vehiculo.precioAlquilerDiario : param.precioAlquilerDiario}"/>
<jsp:setProperty name="vehiculo" property="descripcion" value="${empty param.descripcion ? vehiculo.descripcion : param.descripcion}"/>
<jsp:setProperty name="vehiculo" property="tipo" value="${empty param.tipo ? vehiculo.tipo : param.tipo}"/>

<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<fmt:setLocale value="en-US" />

<table class="contenido-tabla">
    <tr>
        <td><label for="txtMatricula" class="labelForm" width="170">Matricula</label></td>
        <td><input id="txtMatricula" name="matricula" value="${vehiculo.matricula}" type="text" class="controlForm"/></td>
    </tr>

    <tr>
        <td><label for="txtDescripcion" class="labelForm">Descripción</label></td>
        <td><textarea id="txtDescripcion" name="descripcion" class="controlForm" style="width: 170px;">${vehiculo.descripcion}</textarea></td>
    </tr>

    <tr>
        <td><label for="cbTipo" class="labelForm">Tipo</label></td>
        <td><select id="cbTipo" name="tipo" class="controlForm" style="width: 170px;">
                <option <c:if test="${vehiculo.tipo.equals('AUTO')}">selected</c:if>>AUTO</option>
                <option <c:if test="${vehiculo.tipo.equals('CAMIONETA')}">selected</c:if>>CAMIONETA</option>
                <option <c:if test="${vehiculo.tipo.equals('OTROS')}">selected</c:if>>OTROS</option>
                </select>
            </td>
        </tr>

        <tr>
            <td><label for="nudPrecio" class="labelForm">Precio Alquiler Diario</label></td>
            <td><input id="nudPrecio" name="precioAlquilerDiario" type="numeric" step="0.01" value="${vehiculo.precioAlquilerDiario}" class="controlForm" width="170"/></td>
    </tr>

</table>