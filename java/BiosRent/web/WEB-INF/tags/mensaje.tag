<%-- 
    Document   : mensaje
    Created on : Jun 8, 2019, 10:46:40 AM
    Author     : Nicolas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Mensaje" pageEncoding="UTF-8"%>

<c:if test="${!empty mensaje}">
    <p class="<c:if test="${fn:contains(mensaje,'ERROR')}">error</c:if>">${mensaje}</p>
</c:if>