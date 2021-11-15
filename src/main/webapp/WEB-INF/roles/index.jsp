<%@page import="com.untels.zenidscrum.modelo.bean.Privilegio"%>
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
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%            Privilegio prvModRol = usuario
                        .getRol()
                        .getPrivilegioPorNombre("ROLES");
            %>

            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Roles</h1>
                <div class="card">
                    <div class="card-body">
                        <% if (prvModRol.isListar()) { %>
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                        <th>
                                            <% if (prvModRol.isCrear()) { %>
                                            <a href="formulario-rol" class="btn btn-primary">
                                                Nuevo
                                            </a>
                                            <% } %>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Rol r : roles) {%>
                                    <tr>
                                        <td><%=r.getIdRol()%></td>
                                        <td><%=r.getNombre()%></td>
                                        <td><%=r.getDescripcion()%></td>
                                        <td>
                                            <% if (prvModRol.isActualizar()) {%>
                                            <a href="formulario-rol?idRol=<%=r.getIdRol()%>"
                                               class="btn btn-warning">
                                                Editar
                                            </a>
                                            <% } %>
                                            <% if (prvModRol.isEliminar()) { %>
                                            <% if (r.isHabilitado()) {%>
                                            <a href="inhabilitar-rol?idRol=<%=r.getIdRol()%>"
                                               class="btn btn-danger">
                                                Inhabilitar
                                            </a>
                                            <% } else {%>
                                            <a href="habilitar-rol?idRol=<%=r.getIdRol()%>"
                                               class="btn btn-dark">
                                                Habilitar
                                            </a>
                                            <% } %>
                                            <% } %>
                                        </td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                        <% } else { %>
                        <div class="alert alert-danger">
                            No tiene acceso
                        </div>
                        <% }%>
                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
