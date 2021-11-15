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
                                    >
                            </div>
                            <div class="col-sm-12 col-md-3">
                                <label class="form-label">Marca</label>
                                <select class="form-select" name="marca">
                                    <option selected disabled value="">Elegir marca</option>
                                    <% for (String m : marcas) {%>
                                    <option
                                        value="<%=m%>"><%=m%></option>
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
                                        <td>
                                            <button data-id="<%=p.getIdProducto()%>"
                                                    type="button"
                                                    class="btn btn-primary">
                                                Agregar a proforma
                                            </button>
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
                                        <td>
                                            <button data-id="<%=p.getIdProducto()%>"
                                                    type="button"
                                                    class="btn btn-primary">
                                                Agregar a proforma
                                            </button>
                                        </td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <%}%>
            </section>
        </main>
    </body>
</html>
