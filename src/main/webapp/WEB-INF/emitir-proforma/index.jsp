<%@page import="com.untels.zenidscrum.modelo.bean.Producto"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proveedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<String> marca = (ArrayList<String>) request.getAttribute("marcas");
    List<Proveedor> proveedor = (ArrayList<Proveedor>) request.getAttribute("proveedores");
    List<Producto> producto = (ArrayList<Producto>) request.getAttribute("productos");
    List<Producto> productoEquivalente = (ArrayList<Producto>) request.getAttribute("productosEquivalentes");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Emitir Proforma</title>
        <%@ include file="../layout/estilos.jsp" %> 
    </head>
    <body>
        <%@ include file="../layout/navegacion.jsp" %> 


        <form class="row g-3" action="buscar-productos-proforma" method="get">
            <div class="col-md-3">
                <label  class="form-label">Nombre</label>
                <input type="text" class="form-control" name="nombre">
            </div>

            <div class="col-md-3">
                <label class="form-label">Marca</label>


                <select class="form-select" name="marca">
                    <option selected disabled value="">Elegir marca</option>
                    <% for (String m : marca) {%>
                    <option value="<%=m%>"><%=m%></option>
                    <% } %>
                </select>
            </div>
            <div class="col-md-3">
                <label  class="form-label">Proveedor</label>
                <select class="form-select" name="idproveedor">
                    <option selected disabled value="">Elegir proveedor</option>
                    <% for (Proveedor p : proveedor) {%>
                    <option value="<%=p.getIdProveedor()%>"><%=p.getNombre()%></option>
                    <% } %>
                </select>

            </div>
            <div class="col-md-3">
                <button class="btn btn-primary" type="submit">Buscar</button>
            </div>
        </form>

        <%if (producto != null) { %>
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
                <% for (Producto p : producto) {%>
                <tr>
                    <td><%=p.getIdProducto()%></td>
                    <td><%=p.getNombre()%></td>
                    <td><%=p.getMarca()%></td>
                    <td><%=p.getDescripcion()%></td>
                    <td><%=p.getStock()%></td>
                    <td> Agregar proforma </td>
                </tr>
                <% }%>
            </tbody>
        </table>
        <%}%>

        <%if (productoEquivalente != null) { %>
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
                <% for (Producto p : productoEquivalente) {%>
                <tr>
                    <td><%=p.getIdProducto()%></td>
                    <td><%=p.getNombre()%></td>
                    <td><%=p.getMarca()%></td>
                    <td><%=p.getDescripcion()%></td>
                    <td><%=p.getStock()%></td>
                    <td> Agregar proforma </td>
                </tr>
                <% }%>
            </tbody>
        </table>
        <%}%>
    </body>
</html>
