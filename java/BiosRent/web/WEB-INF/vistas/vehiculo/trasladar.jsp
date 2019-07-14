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

        <form action="vehiculo?accion=trasladar" method="post">
            <t:formularioTrasladoVehiculo/>
            
            <input type="submit" value="Trasladar" class="submitFormulario"/>
        </form>

        <p><a href="vehiculo?action=index">Volver a vehiculos...</a></p>

        <p><a href="inicio">Volver a inicio...</a></p>

        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
