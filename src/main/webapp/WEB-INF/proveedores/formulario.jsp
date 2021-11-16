<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Representante"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proveedor"%>
<%
    Proveedor prov = (Proveedor) request.getAttribute("proveedor");
    Representante rep = null;

    if (prov != null) {
        rep = prov.getRepresentante();
    }
    boolean edicion = prov != null;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../layout/estilos.jsp" %>
        <title>Proveedor</title>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Nuevo Proveedor</h1>
                <div class="card">
                    <div class="card-body">
                        <form
                            class="row"
                            <% if (edicion) {%>
                            action="modificar-proveedor?idProveedor=<%=prov.getIdProveedor()%>"
                            <% } else { %>
                            action="crear-proveedor"
                            <% }%>
                            method="POST">
                            <div class="form-group mb-2 col-md-6">
                                <label>Nombre</label>
                                <input
                                    type="text"
                                    name="nombre"
                                    class="form-control"
                                    value="<%if (edicion) {%><%=prov.getNombre()%><%}%>"
                                    required>
                            </div>
                            <div class="form-group mb-2 col-md-6">
                                <label>RUC</label>
                                <input
                                    type="text"
                                    name="ruc"
                                    class="form-control"
                                    value="<%if (edicion) {%><%=prov.getRuc()%><%}%>"
                                    required>
                            </div>
                            <div class="form-group mb-2 col-md-6">
                                <label>Correo Electrónico</label>
                                <input
                                    type="email"
                                    name="correo-electronico"
                                    class="form-control"
                                    value="<%if (edicion) {%><%=prov.getCorreoElectronico()%><%}%>"
                                    required>
                            </div>

                            <div class="form-group mb-2 col-md-6">
                                <label>Telefono</label>
                                <input
                                    type="text"
                                    name="telefono"
                                    class="form-control"
                                    value="<%if (edicion) {%><%=prov.getTelefono()%><%}%>"
                                    required>
                            </div>

                            <div class="form-group mb-2 col-md-6">
                                <label>Nombre Representante</label>
                                <input
                                    type="text"
                                    name="nombre-representante"
                                    class="form-control"
                                    value="<%if (edicion) {%><%=rep.getNombre()%><%}%>"
                                    required>
                            </div>


                            <div class="form-group mb-2 col-md-6">
                                <label>Correo Electrónico Representante</label>
                                <input
                                    type="email"
                                    name="correo-electronico-representante"
                                    class="form-control"
                                    value="<%if (edicion) {%><%=rep.getCorreoElectronico()%><%}%>"
                                    required>
                            </div>

                            <div class="form-group mb-2 col-md-6">
                                <label>Teléfono Representante</label>
                                <input
                                    type="text"
                                    name="telefono-representante"
                                    class="form-control"
                                    value="<%if (edicion) {%><%=rep.getTelefono()%><%}%>"
                                    required>
                            </div>

                            <div class="form-group mb-2">
                                <% if (edicion) { %>
                                <button class="btn btn-success">
                                    Modificar
                                </button>
                                <% } else { %>
                                <button class="btn btn-primary">
                                    Guardar
                                </button>
                                <% }%>
                            </div>
                        </form>

                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
