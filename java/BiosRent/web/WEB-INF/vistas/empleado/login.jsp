<%-- 
    Document   : login
    Created on : Jun 8, 2019, 10:31:53 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html class="fondo">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body class="bodyLogin">

        <div class="login-page">
            <form  class="form" action="empleado" method="POST">
                <div>


                    <input class="txtbox-logueo" type ="text" name="NombreUser" id="NombreUser" value="${param.NomUser}" placeholder="usuario"/>

                    <input class="txtbox-logueo" type ="password" name="Pass" id="Pass" placeholder="contraseña"/><br>


                    <input type ="hidden" name ="accion" value="logIn"/>
                    <input class="btn-logueo" type="submit" name ="botonLogin" value="LOGIN">
                    <t:mensaje />
                </div>
            </form>
        </div>
        <script>
            document.getElementById('NombreUser').select();
        </script>
    </body>

</html>
