<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab10_20191802.beans.Estadio" %>
<%@ page import="com.example.lab10_20191802.daos.EstadioDAO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Estadio</title>
</head>
<body>
<h1>Crear Nuevo Estadio</h1>
<form action="${pageContext.request.contextPath}/EstadioServlet" method="post">
    <input type="hidden" name="action" value="crearEstadio">

    <label for="nombre">Nombre:</label><br>
    <input type="text" id="nombre" name="nombre" required><br><br>

    <label for="provincia">Provincia:</label><br>
    <input type="text" id="provincia" name="provincia" required><br><br>

    <label for="club">Club:</label><br>
    <input type="text" id="club" name="club" required><br><br>

    <input type="submit" value="Crear">
</form>
<br>
<a href="${pageContext.request.contextPath}/EstadioServlet?action=listaEstadios">Volver a la Lista de Estadios</a>
</body>
</html>