<%@page import="com.untels.zenidscrum.modelo.bean.Privilegio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
        <%@ include file="../layout/estilos.jsp" %> 
    </head>
    <body>
        <%@ include file="../layout/navegacion.jsp" %> 
        <%
            Usuario us = (Usuario) request.getAttribute("usuario");
            boolean edicion = us != null;
            List<Rol> roles = (ArrayList<Rol>) request.getAttribute("roles");
            String mensaje = (String) request.getAttribute("mensaje");
            
            Privilegio prvModUsuario = usuario
                    .getRol()
                    .getPrivilegioPorNombre("USUARIOS");
        %>
        <main class="container">
            <div class="card">
                <div class="card-header">
                    <h2>Usuario</h2>
                </div>
                <div class="card-body">
                    <% if (prvModUsuario.isCrear() || 
                            (prvModUsuario.isActualizar() && edicion)) { %>
                    <form 
                        <% if (edicion) { %>
                            action="modificar-usuario?idUsuario=<%=us.getIdUsuario()%>"
                        <% } else { %>
                            action="crear-usuario" 
                        <% } %>
                        method="POST">
                        <div class="form-group mb-2">
                            <label>Nombre</label>
                            <input 
                                type="text" 
                                name="nombre" 
                                class="form-control" 
                                value="<%if (edicion) {%><%=us.getNombre()%><%}%>"
                                required>
                        </div>
                        <div class="form-group mb-2">
                            <label>Correo Electrónico</label>
                            <input 
                                type="email" 
                                name="correo-electronico" 
                                class="form-control" 
                                value="<%if (edicion) {%><%=us.getCorreoElectronico()%><%}%>"
                                required>
                        </div>
                        <% if (edicion)  { %>
                        <input 
                                type="hidden" 
                                name="correo-electronico-actual" 
                                value="<%=us.getCorreoElectronico()%>">
                        <% } %>
                        <%if (!edicion) {%>
                        <div class="form-group mb-2">
                            <label>Contraseña</label>
                            <input 
                                type="password" 
                                name="contrasenia" 
                                class="form-control"
                                required>
                        </div>
                        <%}%>
                        <div class="form-group mb-2">
                            <label>Rol</label>
                            <select name="rol" class="form-control">
                                <% for (Rol r: roles) { %>
                                <option value="<%=r.getIdRol()%>"
                                    <% if (edicion && us.getRol().getIdRol() == r.getIdRol()) { %>
                                    selected
                                    <% } %>>
                                    <%=r.getNombre()%>
                                </option>
                                <% } %>
                            </select>
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
                    <% } %>
                    <% if (mensaje != null) { %>
                    <div class="alert alert-danger">
                        <%=mensaje%>
                    </div>
                    <% } %>
                </div>
            </div>
        </main>
    </body>
</html>
