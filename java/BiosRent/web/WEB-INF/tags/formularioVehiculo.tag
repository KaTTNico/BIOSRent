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

<jsp:useBean id="vehiculo" class="MVC.modelo.entidades.beans.datatypes.Vehiculo" scope="request">
    <jsp:setProperty name="vehiculo" property="matricula" value=""/>
    <jsp:setProperty name="vehiculo" property="precioAlquilerDiario" value="0"/>
    <jsp:setProperty name="vehiculo" property="descripcion" value=""/>
    <jsp:setProperty name="vehiculo" property="tipo" value="Auto"/>
</jsp:useBean>

<fmt:setLocale value="en-US" />

<form action="vehiculo" method="post" >
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
                    <option <c:if test="${vehiculo.tipo.equals('Auto')}">selected</c:if>>Auto</option>
                    <option <c:if test="${vehiculo.tipo.equals('Camioneta')}">selected</c:if>>Camioneta</option>
                    <option <c:if test="${vehiculo.tipo.equals('Otros')}">selected</c:if>>Otros</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td><label for="nudPrecio" class="labelForm">Precio Alquiler Diario</label></td>
                <td><input id="nudPrecio" name="precioalquilerdiario" type="numeric" step="0.01" value="${vehiculo.precioAlquilerDiario}" class="controlForm" width="170"/></td>
        </tr>

    </table>
</form>