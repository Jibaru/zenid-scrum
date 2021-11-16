<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.ProductoProformado"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proforma"%>
<%
    Proforma proforma = (Proforma) request.getAttribute("proforma");
    List<ProductoProformado> productosProforma = null;

    if (proforma != null) {
        productosProforma = proforma.getProductosProformados();
    }

    float subTotal = 0;
    float igvTotal = 0;
    if (productosProforma != null) {
        for (ProductoProformado p : productosProforma) {
            subTotal += (p.getPrecioUnitario() * p.getCantidad());
            igvTotal += (p.getIgv() * p.getPrecioUnitario() * p.getCantidad());
        }
    }
    float total = subTotal + igvTotal;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proforma</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <% if (proforma != null) {%>
                <h1>Proforma Emitida #<%=proforma.getIdProforma()%></h1>
                <div class="card">
                    <div class="card-body">
                        <h5>
                            Nombre Referencial
                        </h5>
                        <p><%=proforma.getNombreReferencial()%></p>
                        <h5>
                            Fecha de Emisión
                        </h5>
                        <p><%=proforma.getFechaCreacion()%></p>
                        <h5>
                            Productos proformados
                        </h5>
                        <table class="table">
                            <thead class="table-dark">
                                <tr>
                                    <th>Cod. Barras</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th>Precio Unitario (S/.)</th>
                                    <th>Cantidad</th>
                                    <th>IGV</th>
                                    <th>Subtotal (S/.)</th>
                                    <th>Total IGV (S/.)</th>
                                    <th>Total + IGV (S/.)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (ProductoProformado p : productosProforma) {%>
                                <%
                                    float subtotalProducto = p.getCantidad() * p.getPrecioUnitario();
                                    float totalIGVProducto = subtotalProducto * p.getIgv();
                                    float totalProducto = subtotalProducto + totalIGVProducto;
                                %>
                                <tr>
                                    <td><%=p.getCodBarras()%></td>
                                    <td><%=p.getNombre()%></td>
                                    <td><%=p.getDescripcion()%></td>
                                    <td class="text-end"><%=p.getPrecioUnitario()%></td>
                                    <td class="text-end"><%=p.getCantidad()%></td>
                                    <td class="text-end">
                                        <%=p.getIgv()%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", subtotalProducto)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", totalIGVProducto)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", totalProducto)%>
                                    </td>
                                </tr>
                                <% }%>
                                <tr>
                                    <td colspan="7" class="text-end">
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
                                    <td colspan="8" class="text-end">
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
                <h1>Proforma no encontrada</h1>
                <% }%>
            </section>
        </main>
    </body>
</html>
