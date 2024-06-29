<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab10_20191802.beans.Estadio" %>
<%
    ArrayList<Estadio> listaEstadios = (ArrayList<Estadio>) request.getAttribute("listaEstadios");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Estadios</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: mintcream;
        }
    </style>
</head>
<body>
<h1>Lista de Estadios</h1>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>Nombre</th>
        <th>Provincia</th>
        <th>Club</th>
        <th>Acción Borrar</th>
    </tr>
    </thead>
    <tbody>
    <% for (Estadio estadio : listaEstadios) { %>
    <tr>
        <td><%= estadio.getIdEstadio() %></td>
        <td><%= estadio.getNombre() %></td>
        <td><%= estadio.getProvincia() %></td>
        <td><%= estadio.getClub() %></td>
        <td><a href="<%= request.getContextPath() %>/EstadioServlet?action=eliminarEstadio&id=<%= estadio.getIdEstadio() %>">Borrar</a></td>
    </tr>
    <% } %>
    </tbody>
</table>
<br>
<a href="<%= request.getContextPath() %>/EstadioServlet?action=nuevoEstadio">Crear Nuevo Estadio</a>
</body>
</html>