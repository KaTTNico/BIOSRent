<%-- 
    Document   : modificar
    Created on : Jun 8, 2019, 10:39:35 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:paginaMaestra title="Modificar un cliente">
    <jsp:body>
        
        <c:if test="${!ocultarFormulario}">
            <t:editorCliente deshabilitarClave="true" foco="Nombre" btntext="Modificar"/>
        </c:if>
        
        <t:mensaje />
        
        <p><a href="cliente">Volver..</a></p>

        
    </jsp:body>
</t:paginaMaestra>