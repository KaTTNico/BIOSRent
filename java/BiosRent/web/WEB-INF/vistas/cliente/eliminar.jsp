<%-- 
    Document   : eliminar
    Created on : Jun 8, 2019, 10:39:44 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:paginaMaestra>
    <jsp:body>
        <c:if test ="${!empty cliente}">
            <p>¿Eliminar al cliente? <strong>${cliente.CI}</strong></p>
            <form method="post" accept-charset="ISO-8859-1">
                <p>
                    <input type="hidden" name="ci" value ="${cliente.CI}"/>
                    <input type="submit" name="accion" value="Eliminar"/>
                </p>
            </form>
        </c:if>
        <p><a href="cliente">Volver</a></p>
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>