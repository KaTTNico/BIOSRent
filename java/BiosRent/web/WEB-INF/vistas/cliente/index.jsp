<%-- 
    Document   : index
    Created on : Jun 8, 2019, 10:40:05 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:paginaMaestra title="Clientes">
    <jsp:body> 
        <fmt:setLocale value="en-US"/>
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar"/><input type="submit" value="Buscar"  /></p>
        </form>
        <p><a href="cliente?accion=agregar"><img src="imagenes/iconos/addUser.png" alt="Agregar" title ="Agregar.."></a></p>

        <table>
            <tr>
                <th>Cédula</th><th>Nombre</th><th>Teléfono</th><th></th>
            </tr>

            <c:forEach items="${clientes}" var ="cliente">
                <tr>
                    
                    <td>${cliente.CI}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.nombreCompleto}</td>
                   
                    <td>
                        <a href="cliente?accion=ver&cedula=${cliente.CI}"><img src="imagenes/iconos/glyphicons-52-eye-open.png" alt="Ver" title="Ver.."></a>&nbsp;&nbsp;
                        <a href="cliente?accion=modificar&cedula=${cliente.CI}"><img src="imagenes/iconos/glyphicons-31-pencil.png" alt="Modificar" title="Modificar.."></a>&nbsp;&nbsp;
                        <a href="cliente?accion=eliminar&cedula=${cliente.CI}"><img src="imagenes/iconos/glyphicons-192-minus-sing.png" alt="Eliminar" title="Eliminar.."></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <t:mensaje />
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:paginaMaestra>