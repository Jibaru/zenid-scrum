<%@page import="com.untels.zenidscrum.modelo.bean.Precio"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proveedor"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Producto"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Privilegio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%                Producto prod = (Producto) request.getAttribute("producto");
                boolean edicion = prod != null;
                List<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores");
                String mensaje = (String) request.getAttribute("mensaje");
            %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>
                    <% if (edicion) { %>
                    Editar Producto
                    <% } else { %>
                    Nuevo Producto
                    <% } %>
                </h1>
                <div class="card">
                    <div class="card-body">
                        <form
                            <% if (edicion) {%>
                            action="modificar-producto?idProducto=<%=prod.getIdProducto()%>"
                            <% } else { %>
                            action="crear-producto"
                            <% } %>
                            method="POST">
                            <div class="row">
                                <div class="form-group mb-2 col-md-6">
                                    <label>Nombre</label>
                                    <input
                                        type="text"
                                        name="nombre"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getNombre()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2 col-md-6">
                                    <label>Cod. Barras</label>
                                    <input
                                        type="text"
                                        name="cod-barras"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getCodBarras()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Descripción</label>
                                    <textarea
                                        name="descripcion"
                                        class="form-control"
                                        rows="3"
                                        required><%if (edicion) {%><%=prod.getDescripcion()%><%}%></textarea>
                                </div>
                                <div class="form-group mb-2 col-md-4">
                                    <label>Marca</label>
                                    <input
                                        type="text"
                                        name="marca"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getMarca()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2 col-md-4">
                                    <label>Familia</label>
                                    <input
                                        type="text"
                                        name="familia"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getFamilia()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2 col-md-4">
                                    <label>Línea</label>
                                    <input
                                        type="text"
                                        name="linea"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getLinea()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2 col-md-4">
                                    <label>Stock</label>
                                    <input
                                        type="number"
                                        name="stock"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getStock()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2 col-md-4">
                                    <label>Stock Mínimo</label>
                                    <input
                                        type="number"
                                        name="stock-minimo"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getStockMinimo()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2 col-md-4">
                                    <label>IGV</label>
                                    <input
                                        type="number"
                                        name="igv"
                                        step="any"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getIgv()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2 col-md-4">
                                    <label>Precio Compra Unitario</label>
                                    <input
                                        type="number"
                                        name="precio-compra-unitario"
                                        step="any"
                                        class="form-control"
                                        value="<%if (edicion) {%><%=prod.getPrecioCompraUnitario()%><%}%>"
                                        required>
                                </div>
                                <div class="form-group mb-2 col-md-4">
                                    <label>Proveedor</label>
                                    <select name="proveedor" class="form-control">
                                        <% for (Proveedor p : proveedores) {%>
                                        <option value="<%=p.getIdProveedor()%>"
                                                <% if (edicion && prod.getProveedor().getIdProveedor() == p.getIdProveedor()) { %>
                                                selected
                                                <% }%>>
                                            <%=p.getNombre()%>
                                        </option>
                                        <% } %>
                                    </select>
                                </div>
                                <hr/>
                                <h2>Precios</h2>
                                <div class="row">
                                    <div class="form-group mb-2 col-md-3">
                                        <label>Precio Unitario</label>
                                        <input
                                            type="number"
                                            id="pprecio"
                                            step="any"
                                            class="form-control">
                                    </div>
                                    <div class="form-group mb-2 col-md-3">
                                        <label>Cantidad</label>
                                        <input
                                            type="number"
                                            id="pcantidad"
                                            class="form-control">
                                    </div>
                                    <div class="form-group mb-2 col-md-3">
                                        <label>Factor</label>
                                        <input
                                            type="number"
                                            id="pfactor"
                                            class="form-control">
                                    </div>
                                    <div class="form-group mb-2 col-md-3">
                                        <label>Unidad</label>
                                        <input
                                            type="text"
                                            id="puni"
                                            class="form-control">
                                    </div>
                                    <div class="form-group mb-2 col-md-3 pt-2">
                                        <button id="btn-agregar-precio"
                                                type="button"
                                                class="btn btn-warning w-100">
                                            Agregar
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="table-dark text-center">
                                        <tr>
                                            <th>Precio Unit.</th>
                                            <th>Cantidad</th>
                                            <th>Factor</th>
                                            <th>Unidad</th>
                                            <th>Remover</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbody-precios" class="text-center">
                                        <% int indicePrecios = 0; %>
                                        <% if (edicion) { %>
                                        <% for (; indicePrecios < prod.getPrecios().size(); indicePrecios++) {
                                                Precio prec = prod.getPrecios().get(indicePrecios);
                                        %>
                                        <tr>
                                            <td>
                                                <%=prec.getPrecioUnitario()%>
                                                <input type="hidden"
                                                       name="pprecio-<%=prec.getIdPrecio()%>"
                                                       value="<%=prec.getPrecioUnitario()%>" />
                                            </td>
                                            <td>
                                                <%=prec.getCantidad()%>
                                                <input type="hidden"
                                                       name="pcantidad-<%=prec.getIdPrecio()%>"
                                                       value="<%=prec.getCantidad()%>" />
                                            </td>
                                            <td>
                                                <%=prec.getFactor()%>
                                                <input type="hidden"
                                                       name="pfactor-<%=prec.getIdPrecio()%>"
                                                       value="<%=prec.getFactor()%>" />
                                            </td>
                                            <td>
                                                <%=prec.getUnidad()%>
                                                <input type="hidden"
                                                       name="punidad-<%=prec.getIdPrecio()%>"
                                                       value="<%=prec.getUnidad()%>" />
                                            </td>
                                            <td>
                                                <input type="hidden"
                                                       name="precio-<%=indicePrecios + 1%>"
                                                       value="<%=prec.getIdPrecio()%>" />
                                                <button type="button"
                                                        data-id="<%=prec.getIdPrecio()%>"
                                                        class="btn btn-danger">
                                                    Remover
                                                </button>
                                            </td>
                                        </tr>
                                        <% } %>
                                        <% }%>
                                    </tbody>
                                    <input id="total-precios"
                                           type="hidden"
                                           name="total-precios"
                                           value="<%=indicePrecios%>">
                                </table>
                            </div>
                            <script>
                                var btnAgregar = document.getElementById("btn-agregar-precio");
                                var tbodyPrecios = document.getElementById("tbody-precios");
                                var inputPrecio = document.getElementById("pprecio");
                                var inputUnidad = document.getElementById("puni");
                                var inputCantidad = document.getElementById("pcantidad");
                                var inputFactor = document.getElementById("pfactor");
                                var inputTotal = document.getElementById("total-precios");

                                btnAgregar.addEventListener("click", function (e) {
                                    var tr = document.createElement("tr");
                                    var valores = [{
                                            input: inputPrecio,
                                            prefijo: "pprecio-nuevo-"
                                        }, {
                                            input: inputCantidad,
                                            prefijo: "pcantidad-nuevo-"
                                        }, {
                                            input: inputFactor,
                                            prefijo: "pfactor-nuevo-"
                                        }, {
                                            input: inputUnidad,
                                            prefijo: "punidad-nuevo-"
                                        }];

                                    for (var obj of valores) {
                                        var td = document.createElement("td");
                                        var inputHidden = document.createElement("input");

                                        inputHidden.type = "hidden";
                                        inputHidden.name = obj.prefijo + siguienteIndice();
                                        inputHidden.value = obj.input.value;

                                        td.textContent = obj.input.value;
                                        td.appendChild(inputHidden);
                                        tr.appendChild(td);
                                    }

                                    var btn = document.createElement("button");
                                    var inputHidden = document.createElement("input");

                                    btn.textContent = "X";
                                    btn.className = "btn btn-danger";
                                    btn.type = "button";

                                    inputHidden.type = "hidden";
                                    inputHidden.name = "precio-" + siguienteIndice();
                                    inputHidden.value = "-1";

                                    var td = document.createElement("td");
                                    td.appendChild(inputHidden);
                                    td.appendChild(btn);

                                    tr.appendChild(td);

                                    tbodyPrecios.appendChild(tr);
                                    incrementarTotal();
                                });

                                function incrementarTotal() {
                                    inputTotal.value = parseInt(inputTotal.value) + 1;
                                }

                                function decrementarTotal() {
                                    inputTotal.value = parseInt(inputTotal.value) - 1;
                                }

                                function siguienteIndice() {
                                    return parseInt(inputTotal.value) + 1;
                                }
                            </script>
                            <% if (edicion) { %>
                            <button class="btn btn-success">
                                Modificar
                            </button>
                            <% } else { %>
                            <button class="btn btn-primary">
                                Guardar
                            </button>
                            <% } %>
                        </form>
                        <% if (mensaje != null) {%>
                        <div class="alert alert-danger">
                            <%=mensaje%>
                        </div>
                        <% }%>
                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
