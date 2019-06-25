<%-- 
    Document   : login
    Created on : Jun 8, 2019, 10:31:53 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Logueo</h1>
        <h2>Empleados</h2>

        <form action="empleado" method="POST">
            <div>
                <label for="NombreUser">Usuario:</label>
                <input type ="text" name="NombreUser" id="NombreUser" value="${param.NomUser}"/>
            </div>
            <div>
                <label for="Pass">Contraseña:</label>
                <input type ="password" name="Pass" id="Pass"/>
            </div>
            <div>
                <input type ="hidden" name ="accion" value="logIn"/>
                <input type="submit" name ="botonLogin" value="Login"

            </div>
        </form>
        <script>
            document.getElementById('NombreUser').select();
        </script>
    </body>
    <t:mensaje />
</html>
