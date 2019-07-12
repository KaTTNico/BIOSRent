<%-- 
    Document   : agregar
    Created on : Jun 8, 2019, 10:35:00 AM
    Author     : Nicolas
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Agregar Vehiculo">
    <jsp:body>
        
        <form action="vehiculo?action=agregar" method="post">
        <t:formularioVehiculo/>
        
        <input type="submit" value="Agregar" class="submitFormulario">
        
        </form>
        
        <p><a href="vehiculo?action=index">Volver a vehiculos...</a></p>
        
        <p><a href="inicio">Volver a inicio...</a></p>

        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
