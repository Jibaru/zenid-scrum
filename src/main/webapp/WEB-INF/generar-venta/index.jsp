<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proforma"%>
<%
    List<Proforma> proformas = (ArrayList<Proforma>) request.getAttribute("proformas");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generar Venta</title>
        <%@ include file="../layout/estilos.jsp" %>

    </head>

    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Buscar proforma</h1>
                <div class="card">
                    <div class="card-body">
                        <form class="row" action="buscar-proforma" method="get">
                            <div class="row justify-content-center">
                                <div class="col-6">
                                    <input class="form-control"
                                           type="text"
                                           placeholder="Nombre referencial"
                                           name="nombre-referencial">
                                </div>
                                <div class="col-4">
                                    <button class="btn btn-primary me-md-2"
                                            type="submit">Buscar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <h2 class="mt-2 mb-2">Proformas coincidentes</h2>
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre referencial</th>
                                        <th>Fecha emisión</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (proformas != null) { %>
                                    <% for (Proforma p : proformas) {%>
                                    <tr>
                                        <td><%=p.getIdProforma()%></td>
                                        <td><%=p.getNombreReferencial()%></td>
                                        <td><%=p.getFechaCreacion()%></td>
                                        <td>
                                            <a class="btn btn-warning" href="ver-proforma?idProforma=<%=p.getIdProforma()%>">
                                                Ver
                                            </a>
                                            <a class="btn btn-warning" href="seleccionar-proforma?idProforma=<%=p.getIdProforma()%>">
                                                Seleccionar
                                            </a>
                                        </td>
                                    </tr>
                                    <% } %>
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
