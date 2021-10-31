<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Rol> roles = (ArrayList<Rol>) request.getAttribute("roles");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roles</title>
        <%@ include file="../layout/estilos.jsp" %> 
    </head>
    <body>
        <%@ include file="../layout/navegacion.jsp" %> 
        <main class="container">
            <div class="card">
                <div class="card-body">
                    <table class="table">
                        <thead class="table-dark">
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>
                                    <a href="formulario-rol" class="btn btn-primary">Nuevo</a>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Rol r:roles) { %>
                            <tr>
                                <td><%=r.getIdRol()%></td>
                                <td><%=r.getNombre()%></td>
                                <td><%=r.getDescripcion()%></td>
                                <td>
                                    <a href="formulario-rol?idRol=<%=r.getIdRol()%>" 
                                       class="btn btn-warning">
                                        Editar
                                    </a>
                                    <% if (r.isHabilitado()) { %>
                                    <a href="inhabilitar-rol?idRol=<%=r.getIdRol()%>" 
                                       class="btn btn-danger">
                                        Inhabilitar
                                    </a>
                                    <% } else { %>
                                    <a href="habilitar-rol?idRol=<%=r.getIdRol()%>" 
                                       class="btn btn-dark">
                                        Habilitar
                                    </a>
                                    <% } %>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </body>
</html>
