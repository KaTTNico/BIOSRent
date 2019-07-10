<%-- 
    Document   : mensaje
    Created on : Jun 8, 2019, 10:46:40 AM
    Author     : Nicolas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Mensaje" pageEncoding="UTF-8"%>

<c:if test="${!empty mensaje}">
    <c:if test="${fn:contains(mensaje,'Error')}">
      <p class="error">${mensaje}</p>  
    </c:if>
    <c:if test="${!fn:contains(mensaje,'Error')}">
        <p class="letras">${mensaje}</p>
    </c:if>
</c:if>

    
