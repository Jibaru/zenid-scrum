package com.untels.zenidscrum.controlador.ventas;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Precio;
import com.untels.zenidscrum.modelo.bean.Producto;
import com.untels.zenidscrum.modelo.bean.ProductoProformado;
import com.untels.zenidscrum.modelo.bean.Proforma;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.dao.MarcaDAO;
import com.untels.zenidscrum.modelo.dao.PrecioDAO;
import com.untels.zenidscrum.modelo.dao.ProductoDAO;
import com.untels.zenidscrum.modelo.dao.ProductoProformadoDAO;
import com.untels.zenidscrum.modelo.dao.ProformaDAO;
import com.untels.zenidscrum.modelo.dao.ProveedorDAO;
import com.untels.zenidscrum.modelo.dao.SQLMarcaDAO;
import com.untels.zenidscrum.modelo.dao.SQLPrecioDAO;
import com.untels.zenidscrum.modelo.dao.SQLProductoDAO;
import com.untels.zenidscrum.modelo.dao.SQLProductoProformadoDAO;
import com.untels.zenidscrum.modelo.dao.SQLProformaDAO;
import com.untels.zenidscrum.modelo.dao.SQLProveedorDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "EmitirProformaServlet",
        urlPatterns = {
            "/emision-proforma",
            "/buscar-productos-proforma",
            "/agregar-producto-proforma",
            "/remover-producto-proforma",
            "/emitir-proforma",
            "/ver-proforma"
        }
)
public class EmitirProformaServlet extends HttpServlet {

    private final MarcaDAO marcaDAO;
    private final ProveedorDAO proveedorDAO;
    private final ProductoDAO productoDAO;
    private final PrecioDAO precioDAO;
    private final ProformaDAO proformaDAO;
    private final ProductoProformadoDAO productoProformadoDAO;

    public EmitirProformaServlet() {
        SQLConexion conexion = new SQLConexion();
        this.marcaDAO = new SQLMarcaDAO(conexion);
        this.proveedorDAO = new SQLProveedorDAO(conexion);
        this.productoDAO = new SQLProductoDAO(conexion);
        this.precioDAO = new SQLPrecioDAO(conexion);
        this.proformaDAO = new SQLProformaDAO(conexion);
        this.productoProformadoDAO = new SQLProductoProformadoDAO(conexion);
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
        String path = request.getServletPath();
        switch (path) {
            case "/emision-proforma":
                emisionProforma(request, response);
                break;
            case "/buscar-productos-proforma":
                buscarProductoProforma(request, response);
                break;
            case "/agregar-producto-proforma":
                agregarProductoProforma(request, response);
                break;
            case "/remover-producto-proforma":
                removerProductoProforma(request, response);
                break;
            case "/emitir-proforma":
                emitirProforma(request, response);
                break;
            case "/ver-proforma":
                verProforma(request, response);
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

    private void emisionProforma(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<String> marcas = marcaDAO.listarTodos();
        request.setAttribute("marcas", marcas);

        List<Proveedor> proveedores = proveedorDAO.listarTodos();
        request.setAttribute("proveedores", proveedores);

        request.getRequestDispatcher("WEB-INF/emitir-proforma/index.jsp")
                .forward(request, response);
    }

    private void buscarProductoProforma(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String marca = request.getParameter("marca");
        String idproveedor = request.getParameter("idproveedor");

        Integer idProv = null;

        if (idproveedor != null && !idproveedor.isEmpty()) {
            idProv = Integer.parseInt(idproveedor);
        }

        List<Producto> productos = productoDAO.buscar(nombre, marca, idProv);

        String familia = (!productos.isEmpty() ? productos.get(0).getFamilia() : null);
        String linea = (!productos.isEmpty() ? productos.get(0).getLinea() : null);

        List<Producto> productosEquivalentes = new ArrayList<>();

        if (familia != null || linea != null) {
            productosEquivalentes = productoDAO.buscarEquivalentes(familia, linea);
        }

        List<String> marcas = marcaDAO.listarTodos();
        List<Proveedor> proveedor = proveedorDAO.listarTodos();

        request.setAttribute("productos", productos);
        request.setAttribute("productosEquivalentes", productosEquivalentes);
        request.setAttribute("marcas", marcas);
        request.setAttribute("proveedores", proveedor);

        request.getRequestDispatcher("WEB-INF/emitir-proforma/index.jsp")
                .forward(request, response);
    }

    private void agregarProductoProforma(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int idPrecio = Integer.parseInt(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Producto producto = productoDAO.obtenerPorId(idProducto);
        Precio precio = precioDAO.obtenerPorId(idPrecio);

        ProductoProformado prodProforma = new ProductoProformado();

        prodProforma.setIdProducto(idProducto);
        prodProforma.setDescripcion(producto.getDescripcion());
        prodProforma.setNombre(producto.getNombre());
        prodProforma.setCodBarras(producto.getCodBarras());
        prodProforma.setIgv(producto.getIgv());
        prodProforma.setMarca(producto.getMarca());
        prodProforma.setUnidad(precio.getUnidad());
        prodProforma.setFactor(precio.getFactor());
        prodProforma.setPrecioUnitario(precio.getPrecioUnitario());
        prodProforma.setCantidad(cantidad);

        List<ProductoProformado> prods = (List<ProductoProformado>) request.getSession().getAttribute("productos-proforma");

        if (prods == null) {
            prods = new ArrayList<>();
        }

        boolean encontrado = false;
        for (ProductoProformado p : prods) {
            if (p.getIdProducto() == idProducto) {
                encontrado = true;
            }
        }

        if (!encontrado) {
            prods.add(prodProforma);
            request.getSession().setAttribute("productos-proforma", prods);
        }

        buscarProductoProforma(request, response);
    }

    private void removerProductoProforma(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        List<ProductoProformado> prods = (List<ProductoProformado>) request
                .getSession()
                .getAttribute("productos-proforma");

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

        buscarProductoProforma(request, response);

    }

    private void emitirProforma(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String nombreReferencial = request.getParameter("nombre-referencial");
        List<ProductoProformado> prods = (List<ProductoProformado>) request
                .getSession()
                .getAttribute("productos-proforma");

        Proforma prof = new Proforma();

        prof.setNombreReferencial(nombreReferencial);
        prof.setFechaCreacion(LocalDate.now());

        if (proformaDAO.crear(prof)) {
            for (ProductoProformado p : prods) {
                p.setIdProforma(prof.getIdProforma());
                productoProformadoDAO.crear(p);
            }
        }

        request.getSession().removeAttribute("productos-proforma");

        response.sendRedirect("ver-proforma?idProforma="
                + Integer.toString(prof.getIdProforma()));
    }

    private void verProforma(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        int idProforma = Integer.parseInt(request.getParameter("idProforma"));

        Proforma proforma = proformaDAO.obtenerPorId(idProforma);

        request.setAttribute("proforma", proforma);

        request.getRequestDispatcher("WEB-INF/emitir-proforma/proforma.jsp")
                .forward(request, response);
    }

}
