<%-- 
    Document   : ver
    Created on : Jun 8, 2019, 10:43:33 AM
    Author     : Nicolas
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Agregar Vehiculo">
    <jsp:body>
       
        <t:formularioVehiculo/>        

        <a href="vehiculo?action=index">Volver a vehiculos...</a>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
