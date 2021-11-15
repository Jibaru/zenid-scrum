<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Privilegio"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Rol rol = (Rol) request.getAttribute("rol");
    boolean edicion = rol != null;
    List<Privilegio> privilegios = (ArrayList<Privilegio>) request.getAttribute("privilegios");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rol</title>
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
                <h1>
                    <% if (edicion) { %>
                    Editar Rol
                    <% } else { %>
                    Nuevo Rol
                    <% } %>
                </h1>
                <div class="card">
                    <div class="card-body">
                        <% if (prvModRol.isCrear()
                                    || (prvModRol.isActualizar() && edicion)) { %>
                        <form
                            <% if (edicion) {%>
                            action="modificar-rol?idRol=<%=rol.getIdRol()%>"
                            <% } else { %>
                            action="crear-rol"
                            <% } %>
                            method="POST">
                            <div class="form-group">
                                <label>Nombre</label>
                                <input
                                    type="text"
                                    name="nombre"
                                    class="form-control"
                                    value="<%if (rol != null) {%><%=rol.getNombre()%><%}%>"
                                    required>
                            </div>
                            <div class="form-group">
                                <label>Descripción</label>
                                <textarea
                                    name="descripcion"
                                    rows="3"
                                    class="form-control"
                                    required><%if (rol != null) {
                                    %><%=rol.getDescripcion()%><%}%></textarea>
                            </div>
                            <hr>
                            <h2>Privilegios</h2>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="table-dark text-center">
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Crear</th>
                                            <th>Actualizar</th>
                                            <th>Listar</th>
                                            <th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody class="text-center">
                                        <% for (Privilegio p : privilegios) { %>
                                        <% if (edicion) {%>
                                    <input type="hidden"
                                           name="id-privilegio-<%=p.getNombre()%>"
                                           value="<%=p.getIdPrivilegio()%>">
                                    <% }%>
                                    <tr>
                                        <td><%=p.getNombre()%></td>
                                        <td>
                                            <input type="checkbox"
                                                   name="crear-<%=p.getNombre()%>"
                                                   <%
                                                   if (p.isCrear()) { %>
                                                   checked
                                                   <% }%>>
                                        </td>
                                        <td>
                                            <input type="checkbox"
                                                   name="actualizar-<%=p.getNombre()%>"
                                                   <%
                                                   if (p.isActualizar()) { %>
                                                   checked
                                                   <% }%>>
                                        </td>
                                        <td>
                                            <input type="checkbox"
                                                   name="listar-<%=p.getNombre()%>"
                                                   <%
                                                   if (p.isListar()) { %>
                                                   checked
                                                   <% }%>>
                                        </td>
                                        <td>
                                            <input type="checkbox"
                                                   name="eliminar-<%=p.getNombre()%>"
                                                   <%
                                                   if (p.isEliminar()) { %>
                                                   checked
                                                   <% }%>>
                                        </td>
                                    </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                            <% if (edicion) { %>
                            <button class="btn btn-success">
                                Modificar
                            </button>
                            <% } else { %>
                            <button class="btn btn-primary">
                                Guardar
                            </button>
                            <% } %>
                        </form>
                        <% } else { %>
                        <div class="alert alert-danger">
                            No tiene acceso
                        </div>
                        <% }%>
                    </div>
                </div>
            </section>
    </body>
</html>
