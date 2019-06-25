<%-- 
    Document   : index
    Created on : Jun 8, 2019, 10:40:05 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:paginaMaestra title="Clientes">
    <jsp:body> 
        <fmt:setLocale value="en-US"/>
        <form>
            <p<input type="text" name="buscar" value="${param.buscar}" id="buscar"/><input type="submit" value="Buscar"  /></p>
        </form>
        <p><a href="cliente?accion=agregar"><img src="imagenes/iconos/addUser.png" alt="Agregar" title ="Agregar.."></a></p>
    </jsp:body>
</t:paginaMaestra>