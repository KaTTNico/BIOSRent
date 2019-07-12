<%-- 
    Document   : confirmacionDesicion
    Created on : Jul 11, 2019, 10:34:12 PM
    Author     : fmora
--%>

<%@tag description="Para realizar confirmaciones de decisiones" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="question" %>
<%@attribute name="servlet" %>

<table class="contenido-tabla">
    <tr align="center">
        <td colspan="3">${question}</td>
    </tr>
    <tr></tr>
    <tr>
        <td align="right">
            <form action="${servlet}?&decition=yes" method="post">
                <input type="submit" value="Aceptar" class="aceptarDecision"/>
            </form>
        </td>
        <td>&nbsp;&nbsp;</td>
        <td>
            <form action="${servlet}?&decition=no" method="post">
                <input type="submit" value="Cancelar" class="cancelarDecision"/>
            </form>
        </td>
    </tr>
</table>
