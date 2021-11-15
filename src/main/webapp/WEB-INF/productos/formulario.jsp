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
                                        name="descripción"
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
                                            name="punit"
                                            step="any"
                                            class="form-control">
                                    </div>
                                    <div class="form-group mb-2 col-md-3">
                                        <label>Cantidad</label>
                                        <input
                                            type="number"
                                            name="pcantidad"
                                            class="form-control">
                                    </div>
                                    <div class="form-group mb-2 col-md-3">
                                        <label>Factor</label>
                                        <input
                                            type="number"
                                            name="pfactor"
                                            class="form-control">
                                    </div>
                                    <div class="form-group mb-2 col-md-3 pt-2">
                                        <button type="button" class="btn btn-warning w-100">
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
                                            <th>Remover</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% if (edicion) { %>
                                        <% for (Precio prec : prod.getPrecios()) {%>
                                        <tr>
                                            <td>
                                                <%=prec.getPrecioUnitario()%>
                                                <input type="hidden"
                                                       name="punit-<%=prec.getIdPrecio()%>"
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
                                                <button type="button"
                                                        data-id="<%=prec.getIdPrecio()%>"
                                                        class="btn btn-danger">
                                                    Remover
                                                </button>
                                            </td>
                                        </tr>
                                        <% } %>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
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
