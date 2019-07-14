<%-- 
    Document   : agregar
    Created on : Jun 8, 2019, 10:35:00 AM
    Author     : Nicolas
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:paginaMaestra title="Agregar Vehiculo">
    <jsp:body>

        <form action="vehiculo?accion=agregar" method="Post" >
            
            <t:formularioVehiculo/>
            <t:formularioTrasladoVehiculo/>

            <input type="submit" value="Agregar" class="submitFormulario">
        </form>

        <p><a href="vehiculo?action=index">Volver a vehiculos...</a></p>

        <p><a href="inicio">Volver a inicio...</a></p>

        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
