<%--
  Created by IntelliJ IDEA.
  User: CDS
  Date: 09/07/2021
  Time: 09:24 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
  <title>Modificar Usuario | Tiendita</title>
  <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
<h1>Modificar Pelicula</h1>
<form action="${context}/updatePelicula" method="POST">
  <input type="hidden" value="update" name="action">
  <input type="hidden" value="${ pelicula.id }" name="id">
  <label>Nombre Pelicula: </label>
  <input class="form-control" type="text" name="nombre" value="${ pelicula.id.nombre }" />
  <br>
  <label>Descripcion: </label>
  <input class="form-control" type="text" name="descripcion" value="${ pelicula.descripcion }" />
  <br>
  <label>Fecha Estreno: </label>
  <input class="form-control" type="date" name="fechaEstreno" value="${ pelicula.id.fechaEstreno }" />
  <br>
  <label>Recaudación: </label>
  <input class="form-control" type="number" name="recaudacion" value="${ pelicula.recaudacion }" />
  <br>

  <button type="button" class="btn btn-secondary"><i class="fas fa-times"></i> Cancelar</button>
  <button type="submit" class="btn btn-primary"><i class="fas fa-edit"></i> Modificar</button>
</form>

<script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</body>
</html>

