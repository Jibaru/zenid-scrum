<%@page import="com.untels.zenidscrum.modelo.bean.Privilegio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Productos</h1>
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                        <th>
                                            <a href="formulario-producto" class="btn btn-primary">
                                                Nuevo
                                            </a>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Producto r : productos) {%>
                                    <tr>
                                        <td><%=r.getIdProducto()%></td>
                                        <td><%=r.getNombre()%></td>
                                        <td><%=r.getDescripcion()%></td>
                                        <td>
                                            <a href="formulario-producto?idProducto=<%=r.getIdProducto()%>"
                                               class="btn btn-warning">
                                                Editar
                                            </a>
                                            <% if (r.isHabilitado()) {%>
                                            <a href="inhabilitar-producto?idProducto=<%=r.getIdProducto()%>"
                                               class="btn btn-danger">
                                                Inhabilitar
                                            </a>
                                            <% } else {%>
                                            <a href="habilitar-producto?idProducto=<%=r.getIdProducto()%>"
                                               class="btn btn-dark">
                                                Habilitar
                                            </a>
                                            <% } %>
                                        </td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
