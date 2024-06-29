<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab10_20191802.beans.Jugador" %>
<%
    ArrayList<Jugador> listaJugador = (ArrayList<Jugador>) request.getAttribute("listaJugador");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Jugadores</title>
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
<h1>Lista de Jugadores</h1>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>Nombre</th>
        <th>Edad</th>
        <th>Posición</th>
        <th>Club</th>
        <th>Selección</th>
        <th>Acción Borrar</th>
    </tr>
    </thead>
    <tbody>
    <% for (Jugador jugador : listaJugador) { %>
    <tr>
        <td><%= jugador.getIdJugador() %></td>
        <td><%= jugador.getNombre() %></td>
        <td><%= jugador.getEdad() %></td>
        <td><%= jugador.getPosicion() %></td>
        <td><%= jugador.getClub() %></td>
        <td><%= jugador.getSn_idSeleccion().getNombre() %></td>
        <td><a href="<%= request.getContextPath() %>/JugadorServlet?action=eliminarJugador&id=<%= jugador.getIdJugador() %>">Borrar</a></td>
    </tr>
    <% } %>
    </tbody>
</table>
<br>
<a href="<%= request.getContextPath() %>/JugadorServlet?action=nuevoJugador">Crear Nuevo Jugador</a>
</body>
</html>