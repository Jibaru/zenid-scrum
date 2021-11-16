package com.untels.zenidscrum.controlador.almacen;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Pedido;
import com.untels.zenidscrum.modelo.bean.Producto;
import com.untels.zenidscrum.modelo.bean.ProductoCompra;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.dao.PedidoDAO;
import com.untels.zenidscrum.modelo.dao.ProductoCompraDAO;
import com.untels.zenidscrum.modelo.dao.ProductoDAO;
import com.untels.zenidscrum.modelo.dao.ProveedorDAO;
import com.untels.zenidscrum.modelo.dao.SQLPedidoDAO;
import com.untels.zenidscrum.modelo.dao.SQLProductoCompraDAO;
import com.untels.zenidscrum.modelo.dao.SQLProductoDAO;
import com.untels.zenidscrum.modelo.dao.SQLProveedorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "SolicitarProductos",
        urlPatterns = {
            "/pedidos",
            "/emision-pedido",
            "/buscar-productos-stock-bajo",
            "/agregar-producto-pedido",
            "/remover-producto-pedido",
            "/emitir-pedido",
            "/ver-pedido",
            "/eliminar-pedido"
        }
)
public class EmitirPedidoServlet extends HttpServlet {

    private final ProductoDAO productoDAO;
    private final ProveedorDAO proveedorDAO;
    private final PedidoDAO pedidoDAO;
    private final ProductoCompraDAO productoCompraDAO;

    public EmitirPedidoServlet() {
        SQLConexion conexion = new SQLConexion();
        productoDAO = new SQLProductoDAO(conexion);
        proveedorDAO = new SQLProveedorDAO(conexion);
        pedidoDAO = new SQLPedidoDAO(conexion);
        productoCompraDAO = new SQLProductoCompraDAO(conexion);
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
        try ( PrintWriter out = response.getWriter()) {
            String path = request.getServletPath();
            switch (path) {
                case "/pedidos":
                    listarPedidos(request, response);
                    break;
                case "/emision-pedido":
                    emisionPedido(request, response);
                    break;
                case "/buscar-productos-stock-bajo":
                    buscarProductoStockBajo(request, response);
                    break;
                case "/agregar-producto-pedido":
                    agregarProductoPedido(request, response);
                    break;
                case "/remover-producto-pedido":
                    removerProductoPedido(request, response);
                    break;
                case "/emitir-pedido":
                    emitirPedido(request, response);
                    break;
                case "/ver-pedido":
                    verPedido(request, response);
                    break;
                case "/eliminar-pedido":
                    eliminarPedido(request, response);
                    break;
            }
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

    private void emisionPedido(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/emitir-pedido/index.jsp")
                .forward(request, response);
    }

    private void buscarProductoStockBajo(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<Producto> productos = productoDAO.listarStockBajo();
        request.setAttribute("productos", productos);

        List<Proveedor> proveedores = proveedorDAO.listarTodos();
        request.setAttribute("proveedores", proveedores);

        request.getRequestDispatcher("WEB-INF/emitir-pedido/index.jsp")
                .forward(request, response);
    }

    private void agregarProductoPedido(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Producto producto = productoDAO.obtenerPorId(idProducto);

        ProductoCompra prodPedido = new ProductoCompra();

        prodPedido.setIdProducto(idProducto);
        prodPedido.setDescripcion(producto.getDescripcion());
        prodPedido.setNombre(producto.getNombre());
        prodPedido.setCodBarras(producto.getCodBarras());
        prodPedido.setIgv(producto.getIgv());
        prodPedido.setMarca(producto.getMarca());
        prodPedido.setPrecioCompraUnitario(producto.getPrecioCompraUnitario());
        prodPedido.setCantidad(cantidad);
        prodPedido.setStockActual(producto.getStock());
        prodPedido.setStockMinimo(producto.getStockMinimo());

        List<ProductoCompra> prods = (List<ProductoCompra>) request
                .getSession().getAttribute("productos-pedido");

        if (prods == null) {
            prods = new ArrayList<>();
        }

        boolean encontrado = false;
        for (ProductoCompra p : prods) {
            if (p.getIdProducto() == idProducto) {
                encontrado = true;
            }
        }

        if (!encontrado) {
            prods.add(prodPedido);
            request.getSession().setAttribute("productos-pedido", prods);
        }

        buscarProductoStockBajo(request, response);

    }

    private void removerProductoPedido(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        List<ProductoCompra> prods = (List<ProductoCompra>) request
                .getSession()
                .getAttribute("productos-pedido");

        if (prods != null) {
            int i = 0;
            for (; i < prods.size(); i++) {
                if (prods.get(i).getIdProducto() == idProducto) {
                    break;
                }
            }

            if (i != prods.size()) {
                prods.remove(i);
            }
        }

        buscarProductoStockBajo(request, response);
    }

    private void emitirPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int totalProveedores = Integer.parseInt(request.getParameter("total-proveedores"));
        String observaciones = request.getParameter("observaciones");
        List<ProductoCompra> prods = (List<ProductoCompra>) request
                .getSession()
                .getAttribute("productos-pedido");

        int codigoCompra = pedidoDAO.obtenerSiguienteCodigoCompra();

        for (int i = 1; i <= totalProveedores; i++) {
            Proveedor proveedor = new Proveedor();
            proveedor.setIdProveedor(Integer.parseInt(
                    request.getParameter("proveedor-" + Integer.toString(i))));

            Pedido pedido = new Pedido();
            pedido.setObservaciones(observaciones);
            pedido.setFechaCreacion(LocalDate.now());
            pedido.setCodigoCompra(codigoCompra);
            pedido.setProveedor(proveedor);

            if (pedidoDAO.crear(pedido)) {
                for (ProductoCompra pc : prods) {
                    pc.setIdPedido(pedido.getIdPedido());
                    productoCompraDAO.crear(pc);
                }
            }
        }

        request.getSession().removeAttribute("productos-pedido");

        response.sendRedirect("pedidos?codigoCompra="
                + Integer.toString(codigoCompra));
    }

    private void verPedido(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));

        Pedido pedido = pedidoDAO.obtenerPorId(idPedido);
        List<ProductoCompra> productosCompra = productoCompraDAO.listarPorIdPedido(idPedido);

        request.setAttribute("pedido", pedido);
        request.setAttribute("productos-compra", productosCompra);

        request.getRequestDispatcher("WEB-INF/emitir-pedido/pedido.jsp")
                .forward(request, response);
    }

    private void listarPedidos(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String codigoCompra = request.getParameter("codigoCompra");
        List<Pedido> pedidos = null;

        if (codigoCompra != null) {
            pedidos = pedidoDAO.listarPorCodigoCompra(Integer.parseInt(codigoCompra));
        } else {
            pedidos = pedidoDAO.listarTodos();
        }

        request.setAttribute("pedidos", pedidos);

        request.getRequestDispatcher("WEB-INF/emitir-pedido/pedidos.jsp")
                .forward(request, response);

    }

    private void eliminarPedido(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));

        pedidoDAO.eliminar(idPedido);

        response.sendRedirect("pedidos");
    }

}
