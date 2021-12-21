<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Venta"%>
<%
    List<Venta> ventas = (ArrayList<Venta>) request.getAttribute("ventas");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Despachar Venta</title>
        <%@ include file="../layout/estilos.jsp" %>

    </head>

    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Buscar ventas</h1>
                <div class="card">
                    <div class="card-body">
                        <form class="row d-flex justify-content align-items-center"
                              action="despacho-venta" method="get">
                            <div class="form-group col-md-10">
                                <label>Término</label>
                                <input class="form-control"
                                       type="text"
                                       placeholder="Término"
                                       name="termino"
                                       <% if (request.getParameter("termino") != null) {%>
                                       value="<%=request.getParameter("termino")%>">
                                <% } %>
                            </div>
                            <div class="form-group col-md-2">
                                <input class="form-check-input"
                                       type="checkbox"
                                       value="boleta"
                                       name="boleta"
                                       <% if (request.getParameter("boleta") != null) {%>
                                       checked
                                       <% } %>>
                                <label class="form-check-label">
                                    Boleta
                                </label><br>
                                <input class="form-check-input"
                                       type="checkbox"
                                       value="factura"
                                       name="factura"
                                       <% if (request.getParameter("factura") != null) {%>
                                       checked
                                       <% } %>>
                                <label class="form-check-label">
                                    Factura
                                </label>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary mt-2"
                                        type="submit">Buscar</button>
                            </div>
                        </form>
                    </div>
                </div>
                <h2 class="mt-2 mb-2">Ventas coincidentes</h2>
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Apellido Paterno</th>
                                        <th>Apellido Materno</th>
                                        <th>Tipo Comprobante</th>
                                        <th>Nº Comprobante</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (ventas != null) { %>
                                    <% for (Venta v : ventas) {%>
                                    <tr>
                                        <td><%=v.getIdVenta()%></td>
                                        <td><%=v.getNombres()%></td>
                                        <td><%=v.getApePaterno()%></td>
                                        <td><%=v.getApeMaterno()%></td>
                                        <td><%=v.getTipoComprobante()%></td>
                                        <td><%=v.getNumeroComprobante()%></td>
                                        <td>
                                            <a class="btn btn-primary" href="ver-venta?idVenta=<%=v.getIdVenta()%>">
                                                Ver Venta
                                            </a>
                                            <a class="btn btn-success" href="despachar-venta?idVenta=<%=v.getIdVenta()%>">
                                                Despachar
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
