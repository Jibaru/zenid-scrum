<%@page import="com.untels.zenidscrum.modelo.bean.Venta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
    List<Venta> ventas = (ArrayList<Venta>) request.getAttribute("ventas");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generar Reporte</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Buscar Venta</h1>
                <div class="card">
                    <div class="card-body">
                        <form class="row" action="buscar-venta-reporte" method="get">
                            <div class="row justify-content-center">
                                <div class="col-6">
                                    <div class="row align-items-start">
                                        <div class="col">
                                            <label for="fechafinal">Fecha Inicio</label>
                                            <input class="form-control"
                                                   type="text"
                                                   placeholder="yyyy-mm-dd"
                                                   name="fechainicio">
                                        </div>
                                        <div class="col">
                                            <label for="fechafinal">Fecha Final</label>
                                            <input class="form-control"
                                                   type="text"
                                                   placeholder="yyyy-mm-dd"
                                                   name="fechafinal">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-4" style="display: flex;
                                     align-items:flex-end;">
                                    <button class="btn btn-primary me-md-6 w-50"
                                            type="submit">Buscar</button>
                                </div>
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
                                        <th>Nombre referencial</th>
                                        <th>Tipo Comprobante</th>
                                        <th>Fecha emisión</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (ventas != null) {  %>
                                    <%for (Venta v : ventas) {%>
                                    <tr>
                                        <td><%=v.getIdVenta()%></td>
                                        <td><%=v.getNombres()%> <%=v.getApePaterno()%> <%=v.getApeMaterno()%></td>
                                        <td><%=v.getTipoComprobante()%></td>
                                        <td><%=v.getFechaEmision()%></td>

                                    </tr>
                                    <% }%>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                        <button type="button" class="btn btn-dark btn-lg" style="float: right">Generar Reporte</button>
                    </div>
                </div>
            </section>
        </main>

    </body>
</html>
