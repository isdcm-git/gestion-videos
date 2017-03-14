<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Nombre:</td>
                <td><%=usuario.getNombre()%></td>
            </tr>
            <tr>
                <td>Apellidos:</td>
                <td><%=usuario.getApellidos()%></td>
            </tr>
            <tr>
                <td>Correo:</td>
                <td><%=usuario.getCorreo()%></td>
            </tr>
        </table>
    </body>
</html>
