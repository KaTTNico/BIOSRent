<%-- 
    Document   : editorCliente
    Created on : 25-jun-2019, 16:21:01
    Author     : Juan
--%>

<%@tag description="Editor de clientes" pageEncoding="UTF-8"%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="foco" required="true"%>
<%@attribute name="btntext" required="true" %>
<%@attribute name="deshabilitarClave"%>
<fmt:setLocale value="en-US"/>
<%-- any content can be specified here e.g.: --%>
<form method="post" accept-charset="ISO-8859-1">
    <div>
        <label for="cedula">Cédula:</label> 
        <c:choose>
            <c:when test="${deshabilitarClave}">
                <input type="text" name="cedula" id="cedula"value="${!empty cliente ? cliente.CI :param.CI}" readonly="readonly"/>

            </c:when>
            <c:otherwise>
                <input type="text" name="CI" id="cedula"value="${!empty cliente ? cliente.CI :param.CI}" />
                
            </c:otherwise>

        </c:choose>
    </div>
    <div>
        <label for="NombreCompleto">Nombre completo:</label>
        <input type="text" name ="NombreCompleto" id="NombreCompleto" value="${!empty cliente ? cliente.NombreCompleto : param.NombreCompleto}" />
    </div>
    <div>
        <label for="Telefono">Telefono:</label>
        <input type="text" name="Telefono" id="Telefono" value="${!empty cliente ? cliente.Telefono : param.Telefono}" />
    </div>
    <div>
        <input type="submit" name="accion" value="${btntext}"/>
    </div>
    
    <script>
        document.getElementById('${foco}').focus();
        document.getElementById('${foco}').select();
    </script>