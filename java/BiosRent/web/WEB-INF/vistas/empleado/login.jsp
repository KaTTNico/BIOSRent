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
    <body>
        <h1 class="login">Logueo</h1>
        <h2 class="login">Empleados</h2>

        <form  action="empleado" method="POST">
            <div class="loguearse">

                <label class="labellogueo" for="NombreUser">Usuario:</label><br>
                <input class="txt-login" type ="text" name="NombreUser" id="NombreUser" value="${param.NomUser}"/><br>



                <br><label class="labellogueo" for="Pass">Contraseña:</label><br>
                <input class="txt-login" type ="password" name="Pass" id="Pass"/><br>


                <br><input type ="hidden" name ="accion" value="logIn"/>
                <div class="btnlogin"><input class = "boton-buscar" type="submit" name ="botonLogin" value="Login"</div>

            </div>
        </form>
        <script>
            document.getElementById('NombreUser').select();
        </script>
    </body>
    <t:mensaje />
</html>
