package com.untels.zenidscrum.controlador.seguridad;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Precio;
import com.untels.zenidscrum.modelo.bean.Producto;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.dao.PrecioDAO;
import com.untels.zenidscrum.modelo.dao.ProductoDAO;
import com.untels.zenidscrum.modelo.dao.ProveedorDAO;
import com.untels.zenidscrum.modelo.dao.SQLPrecioDAO;
import com.untels.zenidscrum.modelo.dao.SQLProductoDAO;
import com.untels.zenidscrum.modelo.dao.SQLProveedorDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "ProductoServlet",
        urlPatterns = {
            "/productos",
            "/formulario-producto",
            "/crear-producto",
            "/modificar-producto",
            "/inhabilitar-producto",
            "/habilitar-producto"
        }
)
public class ProductoServlet extends HttpServlet {

    private final ProductoDAO productoDAO;
    private final ProveedorDAO proveedorDAO;
    private final PrecioDAO precioDAO;

    public ProductoServlet() {
        SQLConexion conexion = new SQLConexion();
        productoDAO = new SQLProductoDAO(conexion);
        proveedorDAO = new SQLProveedorDAO(conexion);
        precioDAO = new SQLPrecioDAO(conexion);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getServletPath();

        switch (path) {
            case "/productos":
                listar(request, response);
                break;
            case "/formulario-producto":
                formulario(request, response);
                break;
            case "/crear-producto":
                crearProducto(request, response);
                break;
            case "/modificar-producto":
                modificarProducto(request, response);
                break;
            case "/inhabilitar-producto":
                inhabilitarProducto(request, response);
                break;
            case "/habilitar-producto":
                habilitarProducto(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listar(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<Producto> productos = productoDAO.listarTodos();

        request.setAttribute("productos", productos);

        request.getRequestDispatcher("WEB-INF/productos/index.jsp")
                .forward(request, response);
    }

    private void formulario(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String idProducto = request.getParameter("idProducto");

        if (idProducto != null) {
            Producto producto = productoDAO.obtenerPorId(Integer.parseInt(idProducto));
            request.setAttribute("producto", producto);
        }

        List<Proveedor> proveedores = proveedorDAO.listarTodos();

        request.setAttribute("proveedores", proveedores);

        request.getRequestDispatcher("WEB-INF/productos/formulario.jsp")
                .forward(request, response);
    }

    private void crearProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String codBarras = request.getParameter("cod-barras");
        String descripcion = request.getParameter("descripcion");
        String marca = request.getParameter("marca");
        String familia = request.getParameter("familia");
        String linea = request.getParameter("linea");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int stockMinimo = Integer.parseInt(request.getParameter("stock-minimo"));
        float igv = Float.parseFloat(request.getParameter("igv"));
        float precioCompraUnitario = Float.parseFloat(request.getParameter("precio-compra-unitario"));
        int idProveedor = Integer.parseInt(request.getParameter("proveedor"));

        int totalPrecios = Integer.parseInt(request.getParameter("total-precios"));

        Proveedor prov = proveedorDAO.obtenerPorId(idProveedor);

        Producto prod = new Producto();
        prod.setNombre(nombre);
        prod.setCodBarras(codBarras);
        prod.setDescripcion(descripcion);
        prod.setMarca(marca);
        prod.setFamilia(familia);
        prod.setLinea(linea);
        prod.setStock(stock);
        prod.setStockMinimo(stockMinimo);
        prod.setIgv(igv);
        prod.setPrecioCompraUnitario(precioCompraUnitario);
        prod.setProveedor(prov);
        prod.setHabilitado(true);

        if (productoDAO.crear(prod)) {
            for (int i = 1; i <= totalPrecios; i++) {
                int idPrecio = Integer.parseInt(request.getParameter("precio-" + i));

                float precioUnit = Float.parseFloat(request.getParameter("pprecio-nuevo-" + i));
                int cantidad = Integer.parseInt(request.getParameter("pcantidad-nuevo-" + i));
                int factor = Integer.parseInt(request.getParameter("pfactor-nuevo-" + i));
                String unidad = request.getParameter("punidad-nuevo-" + i);

                Precio precio = new Precio();
                precio.setPrecioUnitario(precioUnit);
                precio.setCantidad(cantidad);
                precio.setFactor(factor);
                precio.setUnidad(unidad);
                precio.setIdProducto(prod.getIdProducto());

                precioDAO.crear(precio);
            }

            response.sendRedirect("productos");
        } else {
            response.sendRedirect("formulario-producto");
        }

    }

    private void modificarProducto(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String idProductoStr = request.getParameter("idProducto");
        String nombre = request.getParameter("nombre");
        String codBarras = request.getParameter("cod-barras");
        String descripcion = request.getParameter("descripcion");
        String marca = request.getParameter("marca");
        String familia = request.getParameter("familia");
        String linea = request.getParameter("linea");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int stockMinimo = Integer.parseInt(request.getParameter("stock-minimo"));
        float igv = Float.parseFloat(request.getParameter("igv"));
        float precioCompraUnitario = Float.parseFloat(request.getParameter("precio-compra-unitario"));
        int idProveedor = Integer.parseInt(request.getParameter("proveedor"));

        Proveedor prov = proveedorDAO.obtenerPorId(idProveedor);

        Producto prod = new Producto();
        prod.setIdProducto(Integer.parseInt(idProductoStr));
        prod.setNombre(nombre);
        prod.setCodBarras(codBarras);
        prod.setDescripcion(descripcion);
        prod.setMarca(marca);
        prod.setFamilia(familia);
        prod.setLinea(linea);
        prod.setStock(stock);
        prod.setStockMinimo(stockMinimo);
        prod.setIgv(igv);
        prod.setPrecioCompraUnitario(precioCompraUnitario);
        prod.setProveedor(prov);

        if (productoDAO.modificar(prod)) {
            // TODO: Actualizar y/o crear precios

            response.sendRedirect("formulario-producto?idProducto=" + idProductoStr);
        } else {
            response.sendRedirect("formulario-producto");
        }

    }

    private void inhabilitarProducto(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        productoDAO.inhabilitar(idProducto);

        response.sendRedirect("productos");
    }

    private void habilitarProducto(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        productoDAO.habilitar(idProducto);

        response.sendRedirect("productos");
    }

}
