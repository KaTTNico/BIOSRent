<%-- 
    Document   : modificar
    Created on : Jun 8, 2019, 10:39:09 AM
    Author     : Nicolas
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Modificar Vehiculo">
    <jsp:body>

        <form action="vehiculo?accion=modificar" method="post">
            <t:formularioVehiculo matriculaReadOnly="true" readOnly="false"/>
        
        <input type="submit" value="Modificar" class="submitFormulario"/>
        </form>
        
        <p><a href="vehiculo?action=index">Volver a vehiculos...</a></p>
        
        <p><a href="inicio">Volver a inicio...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
