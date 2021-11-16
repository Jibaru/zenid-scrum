<%@page import="com.untels.zenidscrum.modelo.bean.Proveedor"%>
<%@page import="com.untels.zenidscrum.modelo.bean.ProductoCompra"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%
    List<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores");
    List<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
    List<ProductoCompra> productosCompra = (List<ProductoCompra>) request
            .getSession()
            .getAttribute("productos-pedido");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Emisión Pedido</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Emisión pedido</h1>
                <div class="card">
                    <div class="card-body">
                        <form action="buscar-productos-stock-bajo" method="get">
                            <div class="text-center">
                                <button class="btn btn-primary w-100" type="submit">
                                    Buscar productos con bajo stock
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <%if (productos != null) { %>
                        <h2 class="mt-2 mb-2">Productos bajo en stock</h2>
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
                                                <th>Stock Mínimo</th>
                                                <th>Precio Compra/u</th>
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
                                                <td><%=p.getStockMinimo()%></td>
                                                <td>S/. <%=p.getPrecioCompraUnitario()%></td>
                                                <td>
                                                    <form action="agregar-producto-pedido?idProducto=<%=p.getIdProducto()%>"
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
                                    Productos a pedir
                                </h4>
                            </div>
                            <section class="card-body">
                                <% if (productosCompra != null && !productosCompra.isEmpty()) { %>
                                <ul class="p-0">
                                    <% for (ProductoCompra p : productosCompra) {%>
                                    <li class="card mb-2">
                                        <div class="card-body">
                                            <header class="d-flex justify-content-between">
                                                <h5>
                                                    <%=p.getNombre()%>
                                                </h5>
                                                <a href="remover-producto-pedido?idProducto=<%=p.getIdProducto()%>"
                                                   class="btn btn-danger">
                                                    X
                                                </a>
                                            </header>
                                            <p>
                                                <%=p.getDescripcion()%>
                                            </p>
                                            <span class="badge bg-success">
                                                Precio: S/. <%=p.getPrecioCompraUnitario()%>
                                            </span>
                                            <span class="badge bg-danger">
                                                IGV: S/. <%=p.getIgv()%>
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
                                    for (ProductoCompra p : productosCompra) {
                                        subTotal += (p.getPrecioCompraUnitario() * p.getCantidad());
                                        igvTotal += p.getIgv();
                                    }
                                    float total = subTotal + igvTotal;
                                %>
                                <div class="row">
                                    <div class="col-9">
                                        Subtotal
                                    </div>
                                    <div class="col-3 text-end">
                                        S/. <%=String.format("%.02f", subTotal)%>
                                    </div>
                                    <div class="col-9">
                                        IGV
                                    </div>
                                    <div class="col-3 text-end">
                                        S/. <%=String.format("%.02f", igvTotal)%>
                                    </div>
                                    <div class="col-9">
                                        <b>Precio Total</b>
                                    </div>
                                    <div class="col-3 text-end">
                                        S/. <%=String.format("%.02f", total)%>
                                    </div>
                                </div>
                                <hr />
                                <button
                                    type="button"
                                    data-bs-toggle="modal"
                                    data-bs-target="#modal-proforma"
                                    class="btn btn-success w-100">
                                    Realizar pedido
                                </button>
                                <div class="modal fade"
                                     id="modal-proforma"
                                     tabindex="-1"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-xl">
                                        <div class="modal-content">
                                            <form action="emitir-pedido" method="POST">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">
                                                        Pedido
                                                    </h5>
                                                    <button
                                                        type="button"
                                                        class="btn-close"
                                                        data-bs-dismiss="modal"
                                                        aria-label="Close">
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <% if (proveedores != null) { %>
                                                        <div class="form-group mb-2 col-md-3">
                                                            <label>Proveedor</label>
                                                            <select id="proveedor-seleccionado" class="form-control">
                                                                <% for (Proveedor prov : proveedores) {%>
                                                                <option value="<%=prov.getIdProveedor()%>">
                                                                    <%=prov.getNombre()%>
                                                                </option>
                                                                <% } %>
                                                            </select>
                                                            <button id="btn-agregar-proveedor"
                                                                    type="button"
                                                                    class="btn btn-warning w-100 mt-2 mb-2">
                                                                Agregar proveedor
                                                            </button>
                                                            <input id="total-proveedores"
                                                                   type="hidden"
                                                                   value="0"
                                                                   name="total-proveedores" />
                                                            <ul id="lista-proveedores" class="list-group">
                                                            </ul>
                                                            <script>
                                                                var btnAgregarProveedor = document.getElementById("btn-agregar-proveedor");
                                                                var selectProveedor = document.getElementById("proveedor-seleccionado");
                                                                var listaProveedores = document.getElementById("lista-proveedores");
                                                                var inputTotalProveedores = document.getElementById("total-proveedores");

                                                                btnAgregarProveedor.addEventListener("click", function (e) {
                                                                    var idProveedor = selectProveedor.options[selectProveedor.selectedIndex].value;
                                                                    var nombreProveedor = selectProveedor.options[selectProveedor.selectedIndex].text;
                                                                    var li = document.createElement("li");
                                                                    var input = document.createElement("input");
                                                                    var btn = document.createElement("button");
                                                                    var span = document.createElement("span");

                                                                    input.type = "hidden";
                                                                    input.value = idProveedor;
                                                                    input.name = "proveedor-" + siguienteIndiceProveedores();

                                                                    btn.type = "button";
                                                                    btn.className = "btn btn-danger";
                                                                    btn.textContent = "X";
                                                                    btn.dataset.id = idProveedor;
                                                                    btn.dataset.nombre = nombreProveedor;

                                                                    span.textContent = nombreProveedor;

                                                                    li.className = "list-group-item d-flex justify-content-between";
                                                                    li.appendChild(span);
                                                                    li.appendChild(btn);
                                                                    li.appendChild(input);

                                                                    listaProveedores.appendChild(li);
                                                                    removerElementoSelect(idProveedor);
                                                                    incrementarProveedores();
                                                                });

                                                                listaProveedores.addEventListener("click", function (e) {
                                                                    if (e.target.type == "button") {
                                                                        var id = e.target.dataset.id;
                                                                        var nombre = e.target.dataset.nombre;
                                                                        listaProveedores.removeChild(e.target.parentNode);
                                                                        agregarElementoSelect(id, nombre);
                                                                        decrementarProveedores();
                                                                    }
                                                                });

                                                                function removerElementoSelect(valor) {
                                                                    for (var i = 0; i < selectProveedor.length; i++) {
                                                                        if (selectProveedor.options[i].value == valor)
                                                                            selectProveedor.remove(i);
                                                                    }
                                                                }

                                                                function agregarElementoSelect(valor, texto) {
                                                                    var opcion = document.createElement("option");
                                                                    opcion.text = texto;
                                                                    opcion.value = valor;
                                                                    selectProveedor.appendChild(opcion);
                                                                }

                                                                function incrementarProveedores() {
                                                                    inputTotalProveedores.value = parseInt(inputTotalProveedores.value) + 1;
                                                                }

                                                                function decrementarProveedores() {
                                                                    inputTotalProveedores.value = parseInt(inputTotalProveedores.value) - 1;
                                                                }

                                                                function siguienteIndiceProveedores() {
                                                                    return parseInt(inputTotalProveedores.value) + 1;
                                                                }
                                                            </script>
                                                        </div>
                                                        <% } %>

                                                        <div class="col-md-9">
                                                            <div class="form-group mb-2 ">
                                                                <label>Observaciones</label>
                                                                <textarea
                                                                    rows="3"
                                                                    name="observaciones"
                                                                    class="form-control"></textarea>
                                                            </div>
                                                            <label>Productos a pedir</label>
                                                            <div class="table-responsive">
                                                                <table class="table">
                                                                    <thead class="table-dark">
                                                                        <tr>
                                                                            <th>Cod. Barras</th>
                                                                            <th>Nombre</th>
                                                                            <th>Descripción</th>
                                                                            <th>Precio Unitario</th>
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
                                                    </div>

                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                        Cancelar
                                                    </button>
                                                    <button class="btn btn-primary">
                                                        Realizar pedido(s)
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
