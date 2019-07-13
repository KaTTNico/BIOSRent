<%-- 
    Document   : index
    Created on : Jun 8, 2019, 10:31:00 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Menú Principal">
    <jsp:body>
        <ul class="menuInicio">
            <li><a href="alquiler?accion=index">Alquiler</a></li>
            <li><a href="cliente">Cliente</a></li>
            <li><a href="vehiculo">Vehiculo</a></li>
        </ul>
    </jsp:body>
</t:paginaMaestra>
