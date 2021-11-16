<%@page import="com.untels.zenidscrum.modelo.bean.ProductoProformado"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Precio"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Producto"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proveedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<String> marcas = (ArrayList<String>) request.getAttribute("marcas");
    List<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores");
    List<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
    List<Producto> productosEquivalentes = (ArrayList<Producto>) request.getAttribute("productosEquivalentes");
    List<ProductoProformado> productosProforma = (List<ProductoProformado>) request
            .getSession()
            .getAttribute("productos-proforma");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Emitir Proforma</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Emisión proforma</h1>
                <div class="card">
                    <div class="card-body">
                        <form class="row" action="buscar-productos-proforma" method="get">
                            <div class="col-sm-12 col-md-3">
                                <label  class="form-label">
                                    Nombre
                                </label>
                                <input
                                    type="text"
                                    class="form-control"
                                    name="nombre"
                                    <%if (request.getParameter("nombre") != null) {%>
                                    value="<%=request.getParameter("nombre")%>"
                                    <% } %>
                                    >
                            </div>
                            <div class="col-sm-12 col-md-3">
                                <label class="form-label">Marca</label>
                                <select class="form-select" name="marca">
                                    <option selected disabled value="">Elegir marca</option>
                                    <% for (String m : marcas) {%>
                                    <option
                                        value="<%=m%>"
                                        <%if (request.getParameter("marca") != null && request.getParameter("marca").equals(m)) {%>
                                        selected
                                        <% }%>
                                        ><%=m%></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col-sm-12 col-md-3">
                                <label  class="form-label">Proveedor</label>
                                <select class="form-select" name="idproveedor">
                                    <option selected disabled value="">Elegir proveedor</option>
                                    <% for (Proveedor p : proveedores) {%>
                                    <option
                                        value="<%=p.getIdProveedor()%>"
                                        <%if (request.getParameter("idproveedor") != null
                                                    && request.getParameter("idproveedor").equals(Integer.toString(p.getIdProveedor()))) {%>
                                        selected
                                        <% }%>
                                        ><%=p.getNombre()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col-sm-12 col-md-3 text-center pt-4">
                                <button class="btn btn-primary w-100" type="submit">Buscar</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <%if (productos != null) { %>
                        <h2 class="mt-2 mb-2">Productos encontrados</h2>
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class="table-dark">
                                            <tr>
                                                <th>Id</th>
                                                <th>Nombre</th>
                                                <th>Marca</th>
                                                <th>Descripción</th>
                                                <th>Stock</th>
                                                <th>Familia</th>
                                                <th>Linea</th>
                                                <th>Opciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (Producto p : productos) {%>
                                            <tr>
                                                <td><%=p.getIdProducto()%></td>
                                                <td><%=p.getNombre()%></td>
                                                <td><%=p.getMarca()%></td>
                                                <td><%=p.getDescripcion()%></td>
                                                <td><%=p.getStock()%></td>
                                                <td><%=p.getFamilia()%></td>
                                                <td><%=p.getLinea()%></td>
                                                <td>
                                                    <form action="agregar-producto-proforma?idProducto=<%=p.getIdProducto()%>"
                                                          method="POST">
                                                        <button
                                                            type="button"
                                                            data-bs-toggle="modal" data-bs-target="#modal-<%=p.getIdProducto()%>"
                                                            class="btn btn-primary">
                                                            Agregar a proforma
                                                        </button>
                                                        <div class="modal fade"
                                                             id="modal-<%=p.getIdProducto()%>"
                                                             tabindex="-1"
                                                             aria-labelledby="exampleModalLabel"
                                                             aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title">
                                                                            <%=p.getNombre()%>
                                                                        </h5>
                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div class="form-group">
                                                                            <label>Cantidad</label>
                                                                            <input
                                                                                type="number"
                                                                                name="cantidad"
                                                                                class="form-control"
                                                                                required/>
                                                                        </div>
                                                                        <div class="form-group mb-2">
                                                                            <label>Precio</label>
                                                                            <select name="precio" class="form-control">
                                                                                <% for (Precio prec : p.getPrecios()) {%>
                                                                                <option value="<%=prec.getIdPrecio()%>">
                                                                                    S/. <%=prec.getPrecioUnitario()%> | Factor: <%=prec.getFactor()%> | Unidad: <%=prec.getUnidad()%> | Cantidad: <%=prec.getCantidad()%>
                                                                                </option>
                                                                                <% } %>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                                            Cancelar
                                                                        </button>
                                                                        <button class="btn btn-primary">
                                                                            Agregar
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </td>
                                            </tr>
                                            <% }%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <%}%>
                        <%if (productosEquivalentes != null) { %>
                        <h2 class="mt-2 mb-2">Productos similares</h2>
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class="table-dark">
                                            <tr>
                                                <th>Id</th>
                                                <th>Nombre</th>
                                                <th>Marca</th>
                                                <th>Descripción</th>
                                                <th>Stock</th>
                                                <th>Familia</th>
                                                <th>Linea</th>
                                                <th>Opciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (Producto p : productosEquivalentes) {%>
                                            <tr>
                                                <td><%=p.getIdProducto()%></td>
                                                <td><%=p.getNombre()%></td>
                                                <td><%=p.getMarca()%></td>
                                                <td><%=p.getDescripcion()%></td>
                                                <td><%=p.getStock()%></td>
                                                <td><%=p.getFamilia()%></td>
                                                <td><%=p.getLinea()%></td>
                                                <td>
                                                    <form action="agregar-producto-proforma?idProducto=<%=p.getIdProducto()%>"
                                                          method="POST">
                                                        <button
                                                            type="button"
                                                            data-bs-toggle="modal" data-bs-target="#modal-<%=p.getIdProducto()%>"
                                                            class="btn btn-primary">
                                                            Agregar a proforma
                                                        </button>
                                                        <div class="modal fade"
                                                             id="modal-<%=p.getIdProducto()%>"
                                                             tabindex="-1"
                                                             aria-labelledby="exampleModalLabel"
                                                             aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title">
                                                                            <%=p.getNombre()%>
                                                                        </h5>
                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div class="form-group">
                                                                            <label>Cantidad</label>
                                                                            <input
                                                                                type="number"
                                                                                name="cantidad"
                                                                                class="form-control"
                                                                                required/>
                                                                        </div>
                                                                        <div class="form-group mb-2">
                                                                            <label>Precio</label>
                                                                            <select name="precio" class="form-control">
                                                                                <% for (Precio prec : p.getPrecios()) {%>
                                                                                <option value="<%=prec.getIdPrecio()%>">
                                                                                    S/. <%=prec.getPrecioUnitario()%> | Factor: <%=prec.getFactor()%> | Unidad: <%=prec.getUnidad()%> | Cantidad: <%=prec.getCantidad()%>
                                                                                </option>
                                                                                <% } %>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                                            Cancelar
                                                                        </button>
                                                                        <button class="btn btn-primary">
                                                                            Agregar
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </td>
                                            </tr>
                                            <% }%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <%}%>
                    </div>
                    <div class="col-md-4">
                        <div class="card mt-2">
                            <div class="card-header">
                                <h4>
                                    Productos a proformar
                                </h4>
                            </div>
                            <section class="card-body">
                                <% if (productosProforma != null && !productosProforma.isEmpty()) { %>
                                <ul class="p-0">
                                    <% for (ProductoProformado p : productosProforma) {%>
                                    <li class="card mb-2">
                                        <div class="card-body">
                                            <header class="d-flex justify-content-between">
                                                <h5>
                                                    <%=p.getNombre()%>
                                                </h5>
                                                <a href="remover-producto-proforma?idProducto=<%=p.getIdProducto()%>"
                                                   class="btn btn-danger">
                                                    X
                                                </a>
                                            </header>
                                            <p>
                                                <%=p.getDescripcion()%>
                                            </p>
                                            <span class="badge bg-success">
                                                Precio: S/. <%=p.getPrecioUnitario()%>
                                            </span>
                                            <span class="badge bg-danger">
                                                IGV: (%) <%=p.getIgv()%>
                                            </span>
                                            <span class="badge bg-warning">
                                                Cantidad: <%=p.getCantidad()%>
                                            </span>
                                        </div>
                                    </li>
                                    <% } %>
                                </ul>
                                <hr />
                                <%
                                    float subTotal = 0;
                                    float igvTotal = 0;
                                    for (ProductoProformado p : productosProforma) {
                                        subTotal += (p.getPrecioUnitario() * p.getCantidad());
                                        igvTotal += (p.getIgv() * p.getPrecioUnitario() * p.getCantidad());
                                    }
                                    float total = subTotal + igvTotal;
                                %>
                                <div class="row">
                                    <div class="col-6">
                                        Subtotal
                                    </div>
                                    <div class="col-6 text-end">
                                        S/. <%=String.format("%.02f", subTotal)%>
                                    </div>
                                    <div class="col-6">
                                        IGV Total
                                    </div>
                                    <div class="col-6 text-end">
                                        S/. <%=String.format("%.02f", igvTotal)%>
                                    </div>
                                    <div class="col-6">
                                        <b>Precio Total</b>
                                    </div>
                                    <div class="col-6 text-end">
                                        S/. <%=String.format("%.02f", total)%>
                                    </div>
                                </div>
                                <hr />
                                <button
                                    type="button"
                                    data-bs-toggle="modal"
                                    data-bs-target="#modal-proforma"
                                    class="btn btn-success w-100">
                                    Realizar proforma
                                </button>
                                <div class="modal fade"
                                     id="modal-proforma"
                                     tabindex="-1"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-xl">
                                        <div class="modal-content">
                                            <form action="emitir-proforma" method="POST">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">
                                                        Proforma
                                                    </h5>
                                                    <button
                                                        type="button"
                                                        class="btn-close"
                                                        data-bs-dismiss="modal"
                                                        aria-label="Close">
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group mb-2">
                                                        <label>Nombre Referencial</label>
                                                        <input
                                                            type="text"
                                                            name="nombre-referencial"
                                                            class="form-control"
                                                            required/>
                                                    </div>
                                                    <label>Productos proformados</label>
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
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                        Cancelar
                                                    </button>
                                                    <button class="btn btn-primary">
                                                        Realizar proforma
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <% } else { %>
                                <div class="alert alert-warning">
                                    Sin productos
                                </div>
                                <% }%>
                            </section>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
