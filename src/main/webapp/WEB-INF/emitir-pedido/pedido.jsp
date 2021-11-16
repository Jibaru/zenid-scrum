<%@page import="java.util.ArrayList"%>
<%@page import="com.untels.zenidscrum.modelo.bean.ProductoCompra"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.ProductoProformado"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proforma"%>
<%
    Pedido pedido = (Pedido) request.getAttribute("pedido");
    List<ProductoCompra> productosCompra = (ArrayList<ProductoCompra>) request.getAttribute("productos-compra");

    float subTotal = 0;
    float igvTotal = 0;
    if (productosCompra != null) {
        for (ProductoCompra p : productosCompra) {
            subTotal += (p.getPrecioCompraUnitario() * p.getCantidad());
            igvTotal += p.getIgv();
        }
    }
    float total = subTotal + igvTotal;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <% if (pedido != null) {%>
                <h1>Pedido Emitido #<%=pedido.getIdPedido()%> | Código Compra: #<%=pedido.getCodigoCompra()%></h1>
                <div class="card">
                    <div class="card-body">
                        <h5>
                            Nombre Proveedor
                        </h5>
                        <p><%=pedido.getProveedor().getNombre()%></p>
                        <h5>
                            Fecha de Emisión
                        </h5>
                        <p><%=pedido.getFechaCreacion()%></p>
                        <h5>
                            Correo Electrónico Proveedor
                        </h5>
                        <p><%=pedido.getProveedor().getCorreoElectronico()%></p>
                        <h5>
                            RUC Proveedor
                        </h5>
                        <p><%=pedido.getProveedor().getRuc()%></p>
                        <h5>
                            Teléfono Proveedor
                        </h5>
                        <p><%=pedido.getProveedor().getTelefono()%></p>
                        <h5>
                            Observaciones
                        </h5>
                        <p><%=pedido.getObservaciones()%></p>
                        <h5>
                            Productos a pedir
                        </h5>
                        <table class="table">
                            <thead class="table-dark">
                                <tr>
                                    <th>Cod. Barras</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th>Precio Comp. Unitario</th>
                                    <th>Cantidad</th>
                                    <th>IGV</th>
                                    <th>Subtotal</th>
                                    <th>Total + IGV</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (ProductoCompra p : productosCompra) {%>
                                <%
                                    float subtotalProducto = p.getCantidad() * p.getPrecioCompraUnitario();
                                    float totalProducto = subtotalProducto * p.getIgv();
                                %>
                                <tr>
                                    <td><%=p.getCodBarras()%></td>
                                    <td><%=p.getNombre()%></td>
                                    <td><%=p.getDescripcion()%></td>
                                    <td class="text-end"><%=p.getPrecioCompraUnitario()%></td>
                                    <td class="text-end"><%=p.getCantidad()%></td>
                                    <td class="text-end">
                                        <%=p.getIgv()%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", subtotalProducto)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", totalProducto)%>
                                    </td>
                                </tr>
                                <% }%>
                                <tr>
                                    <td colspan="6" class="text-end">
                                        <%=String.format("%.02f", subTotal)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", igvTotal)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", total)%>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="7" class="text-end">
                                        <b>Precio Total</b>
                                    </td>
                                    <td class="text-end">
                                        <b>
                                            <%=String.format("%.02f", total)%>
                                        </b>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <% } else { %>
                <h1>Pedido no encontrado</h1>
                <% }%>
            </section>
        </main>
    </body>
</html>
