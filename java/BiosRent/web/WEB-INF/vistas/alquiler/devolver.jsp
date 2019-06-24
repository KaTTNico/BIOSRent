<%-- 
    Document   : devolver
    Created on : Jun 8, 2019, 10:42:49 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Menú Principal">
    <jsp:body>
        <ul>
            <form method="get">
                <div>
                    <label for="cedula">Cedula cliente:</label>
                    <input type="text" name="cedula" id="cedula"/>
                </div>

                <input type="submit" name="accion" value="" />
            </form>
        </ul>
    </jsp:body>
</t:paginaMaestra>
