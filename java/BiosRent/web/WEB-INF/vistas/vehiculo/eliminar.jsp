<%-- 
    Document   : eliminar
    Created on : Jun 8, 2019, 10:35:09 AM
    Author     : Nicolas
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Eliminar Vehiculo">
    <jsp:body>
        
        <t:confirmacionDecision question="¿Esta seguro que desea eliminar el vehiculo ${vehiculo.matricula}?" servlet="vehiculo"/>

        <p><a href="vehiculo?action=index">Volver a vehiculos...</a></p>
        
        <p><a href="inicio">Volver a inicio...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
