<%-- 
    Document   : paginaMaestra
    Created on : Jun 8, 2019, 10:47:34 AM
    Author     : Nicolas
--%>

<%@tag description="Master page" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>
<%@attribute  name="empLogueado"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BIOS RENT - ${title}</title>
        <link rel="shortcut icon" href="imagenes/favicon (2).ico">
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <div class="header">
            <h1>BIOS RENT</h1>
            <h2>${title}</h2>
            <c:if test="${empleadoLogueado != null}">
                <form action="empleado" method="post">
                    <p>
                        <label class="Userlog">${empleadoLogueado.nombreUser}</label>
                        <input type="hidden" name="accion" value="logOut" />
                        <input class ="boton-logOut" type="submit" name="btnLogOut" value="Log Out" />
                        
                           
                    </p>
                </form>
            </c:if>

        </div>

        <jsp:doBody />
       
    </body>
</html>