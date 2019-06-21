<%-- 
    Document   : index
    Created on : Jun 8, 2019, 10:43:59 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Menu alquiler">
    <jsp:body>
        <ul>
            <li><a href="agregar">Agregar</a></li>
            <li><a href="devolver">Devolver</a></li>
            <li><a href="ver">Ver</a></li>
        </ul>
    </jsp:body>
</t:paginaMaestra>