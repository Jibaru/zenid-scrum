<%@page import="com.untels.zenidscrum.modelo.bean.Pedido"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Privilegio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Pedido> pedidos = (ArrayList<Pedido>) request.getAttribute("pedidos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Pedidos</h1>
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Codigo Compra</th>
                                        <th>Observaciones</th>
                                        <th>Fecha Creación</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Pedido u : pedidos) {%>
                                    <tr>
                                        <td><%=u.getIdPedido()%></td>
                                        <td><%=u.getCodigoCompra()%></td>
                                        <td><%=u.getObservaciones()%></td>
                                        <td><%=u.getFechaCreacion()%></td>
                                        <td>
                                            <a href="ver-pedido?idPedido=<%=u.getIdPedido()%>"
                                               class="btn btn-warning">
                                                Ver
                                            </a>
                                            <a href="eliminar-pedido?idPedido=<%=u.getIdPedido()%>"
                                               class="btn btn-danger">
                                                Eliminar
                                            </a>
                                        </td>
                                    </tr>
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

