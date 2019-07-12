<%-- 
    Document   : ver
    Created on : Jun 8, 2019, 10:41:25 AM
    Author     : Nicolas
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Ver Vehiculo">
    <jsp:body>
       
        <t:formularioVehiculo/>

        <p><a href="vehiculo?action=index">Volver a vehiculos...</a></p>
        
        <p><a href="inicio">Volver a inicio...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
