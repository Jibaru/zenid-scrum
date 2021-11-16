<%@page import="com.untels.zenidscrum.modelo.bean.Privilegio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%            Privilegio prvModUsuario = usuario
                        .getRol()
                        .getPrivilegioPorNombre("USUARIOS");
            %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Usuarios</h1>
                <div class="card">
                    <div class="card-body">
                        <% if (prvModUsuario.isListar()) { %>
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Correo electrónico</th>
                                        <th>Rol</th>
                                        <th>
                                            <% if (prvModUsuario.isCrear()) { %>
                                            <a href="formulario-usuario" class="btn btn-primary">
                                                Nuevo
                                            </a>
                                            <% } %>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Usuario u : usuarios) {%>
                                    <tr>
                                        <td><%=u.getIdUsuario()%></td>
                                        <td><%=u.getNombre()%></td>
                                        <td><%=u.getCorreoElectronico()%></td>
                                        <td><%=u.getRol().getNombre()%></td>
                                        <td>
                                            <% if (prvModUsuario.isActualizar()) {%>
                                            <a href="formulario-usuario?idUsuario=<%=u.getIdUsuario()%>"
                                               class="btn btn-warning">
                                                Editar
                                            </a>
                                            <a href="formulario-cambio-contrasenia?idUsuario=<%=u.getIdUsuario()%>"
                                               class="btn btn-info">
                                                Cambiar Contra.
                                            </a>
                                            <% } %>
                                            <% if (prvModUsuario.isEliminar()) { %>
                                            <% if (u.isHabilitado()) {%>
                                            <a href="inhabilitar-usuario?idUsuario=<%=u.getIdUsuario()%>"
                                               class="btn btn-danger">
                                                Inhabilitar
                                            </a>
                                            <% } else {%>
                                            <a href="habilitar-usuario?idUsuario=<%=u.getIdUsuario()%>"
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
