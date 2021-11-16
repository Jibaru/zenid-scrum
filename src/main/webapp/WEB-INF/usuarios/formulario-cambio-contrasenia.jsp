<%@page import="com.untels.zenidscrum.modelo.bean.Usuario"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Privilegio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar Contraseña</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%                Usuario us = (Usuario) request.getAttribute("usuario");
                String mensaje = (String) request.getAttribute("mensaje");
                Privilegio prvModUsuario = usuario
                        .getRol()
                        .getPrivilegioPorNombre("USUARIOS");
            %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>
                    Cambiar contraseña: <%=us.getNombre()%>
                </h1>
                <div class="card">
                    <div class="card-body">
                        <% if (prvModUsuario.isActualizar()) {%>
                        <form
                            action="cambiar-contrasenia-usuario?idUsuario=<%=us.getIdUsuario()%>"
                            method="POST"
                            autocomplete="nope">
                            <div class="form-group mb-2">
                                <label>Contraseña</label>
                                <input
                                    id="contrasenia"
                                    type="password"
                                    name="contrasenia"
                                    class="form-control"
                                    autocomplete="nope"
                                    required>
                            </div>
                            <div class="form-group mb-2">
                                <label>Repetir Contraseña</label>
                                <input
                                    id="contrasenia-repetida"
                                    type="password"
                                    name="contrasenia-repetida"
                                    class="form-control"
                                    autocomplete="nope"
                                    required>
                            </div>
                            <button id="btn" class="btn btn-success">
                                Modificar
                            </button>
                            <script>
                                var inputContra = document.getElementById("contrasenia");
                                var inputContraseniaRep = document.getElementById("contrasenia-repetida");
                                var btn = document.getElementById("btn");

                                inputContraseniaRep.addEventListener("keyup", function (e) {
                                    if (e.target.value != inputContra.value) {
                                        btn.disabled = true;
                                    } else {
                                        btn.disabled = false;
                                    }
                                });

                                setTimeout(function () {
                                    inputContra.value = "";
                                    inputContraseniaRep.value = "";
                                }, 500);
                            </script>
                        </form>
                        <% } else { %>
                        <div class="alert alert-danger">
                            No tiene acceso
                        </div>
                        <% } %>
                        <% if (mensaje != null) {%>
                        <div class="alert alert-danger">
                            <%=mensaje%>
                        </div>
                        <% }%>
                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
