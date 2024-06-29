<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab10_20191802.beans.Seleccion" %>
<%
    ArrayList<Seleccion> listaSeleccion = (ArrayList<Seleccion>) request.getAttribute("listaSeleccion");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Jugador</title>
</head>
<body>
<h1>Crear Nuevo Jugador</h1>
<form action="<%= request.getContextPath() %>/JugadorServlet" method="post">
    <input type="hidden" name="action" value="crearJugador">

    <label for="Nombre">Nombre:</label><br>
    <input type="text" id="Nombre" name="Nombre" required><br><br>

    <label for="Edad">Edad:</label><br>
    <input type="number" id="Edad" name="Edad" required><br><br>

    <label for="Posicion">Posición:</label><br>
    <input type="text" id="Posicion" name="Posicion" required><br><br>

    <label for="Club">Club:</label><br>
    <input type="text" id="Club" name="Club" required><br><br>

    <label for="Seleccion">Selección:</label><br>
    <select id="Seleccion" name="Seleccion" required>
        <% for (Seleccion seleccion : listaSeleccion) { %>
        <option value="<%= seleccion.getIdSeleccion() %>"><%= seleccion.getNombre() %></option>
        <% } %>
    </select><br><br>

    <input type="submit" value="Crear">
</form>
<br>
<a href="<%= request.getContextPath() %>/JugadorServlet?action=listaJugador">Volver a la Lista de Jugadores</a>
</body>
</html>