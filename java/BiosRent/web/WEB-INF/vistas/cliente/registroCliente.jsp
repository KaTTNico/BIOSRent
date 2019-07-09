<%-- 
    Document   : registroCliente
    Created on : Jun 8, 2019, 10:33:13 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="t"  tagdir="/WEB-INF/tags" %>

<t:paginaMaestra title="Agregar un cliente">
    <jsp:body>
        <t:editorCliente btntext="agregar" foco="CI" />
        <t:mensaje/>
    </jsp:body>
</t:paginaMaestra>
