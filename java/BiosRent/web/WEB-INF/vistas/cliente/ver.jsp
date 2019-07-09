<%-- 
    Document   : ver
    Created on : Jun 8, 2019, 10:39:51 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="cliente" type="MVC.modelo.entidades.beans.datatypes.Cliente" scope="request"/>
        <h3><jsp:getProperty name="cliente" property="nombreCompleto"/></h3>
        
        <ul>
            <li>Cédula:<jsp:getProperty name="cliente" property="CI"/></li>
            <li>Nombre:<jsp:getProperty name="cliente" property="nombreCompleto"/></li>
            <li>Teléfono:<jsp:getProperty name="cliente" property="telefono"/></li>
            
        </ul>
    </body>
</html>
