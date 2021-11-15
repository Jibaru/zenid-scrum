package com.untels.zenidscrum.controlador.seguridad;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Producto;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.dao.ProductoDAO;
import com.untels.zenidscrum.modelo.dao.ProveedorDAO;
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

    public ProductoServlet() {
        SQLConexion conexion = new SQLConexion();
        productoDAO = new SQLProductoDAO(conexion);
        proveedorDAO = new SQLProveedorDAO(conexion);
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
        List<Proveedor> proveedores = proveedorDAO.listarTodos();

        request.setAttribute("proveedores", proveedores);

        request.getRequestDispatcher("WEB-INF/productos/formulario.jsp")
                .forward(request, response);
    }

    private void crearProducto(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void modificarProducto(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void inhabilitarProducto(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void habilitarProducto(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
