<%@page import="java.util.ArrayList"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedores</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Proveedores</h1>
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table" >
                                <thead class="table-dark" >
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Ruc</th>
                                        <th>Teléfono</th>
                                        <th>Correo Electrónico</th>
                                        <th>Representante</th>
                                        <th>
                                            <a class="btn btn-primary" href="formulario-proveedor" >
                                                Nuevo
                                            </a>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Proveedor p : proveedores) {%>
                                    <tr>
                                        <td><%=p.getIdProveedor()%></td>
                                        <td><%=p.getNombre()%></td>
                                        <td><%=p.getRuc()%></td>
                                        <td><%=p.getTelefono()%></td>
                                        <td><%=p.getCorreoElectronico()%></td>
                                        <td><%=p.getRepresentante().getNombre()%></td>
                                        <td>
                                            <a href="formulario-proveedor?idProveedor=<%=p.getIdProveedor()%>"
                                               class="btn btn-warning">
                                                Editar
                                            </a>
                                            <% if (p.isHabilitado()) {%>
                                            <a href="inhabilitar-proveedor?idProveedor=<%=p.getIdProveedor()%>"
                                               class="btn btn-danger">
                                                Inhabilitar
                                            </a>
                                            <% } else {%>
                                            <a href="habilitar-proveedor?idProveedor=<%=p.getIdProveedor()%>"
                                               class="btn btn-dark">
                                                Habilitar
                                            </a>
                                            <% } %>
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
