<%-- 
    Document   : devolver
    Created on : Jun 8, 2019, 10:42:49 AM
    Author     : Nicolas
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat" %>  

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Devolver">
    <jsp:body>
        <fmt:setLocale value="en-US"/>

        <form method='post' accion="devolver" autocomplete="off">
            <div class="autocomplete" style="width:300px;">
                <input type="hidden" name="sucursal" value="${empleadoLogueado.sucursalEmp.codigo}"/> 
                <input type="hidden" name="multa" value="${param.multa}"/>


                <input type="text" value="${alquiler.id}" name="id" readonly disabled />
                <p></p>
                <input type="text" value="${alquiler.fechaAlquiler}" name="fecha" readonly disabled />
                <p></p>
                <input type="text" value="${alquiler.cantidadDias}" name="cantidadDias" readonly disabled />
                <p></p>
                <input type="text" value="${alquiler.costoSeguro}" name="costoSeguro" readonly disabled />
                <p></p>
                <input type="text" value="${alquiler.costoTotal}" name="total" readonly disabled />
                <p></p>
                <input type="text" value="${alquiler.depositoEnGarantia}" name="depositoGarantia" readonly disabled />
                <p></p>
                <input type="text" value="${alquiler.clientel.CI}" name="cedula" readonly disabled />
                <p></p>
                <input type="text" value="${alquiler.sucursal.codigo}" name="sucursal" readonly disabled />
            </div>
            <br /><br />
            <input type="submit" value="Devolver"/>
        </form>
        <p><a href="index">Volver...</a></p>
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>