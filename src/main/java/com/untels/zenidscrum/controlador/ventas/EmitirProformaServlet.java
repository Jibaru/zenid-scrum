package com.untels.zenidscrum.controlador.ventas;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Producto;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.dao.MarcaDAO;
import com.untels.zenidscrum.modelo.dao.ProductoDAO;
import com.untels.zenidscrum.modelo.dao.ProveedorDAO;
import com.untels.zenidscrum.modelo.dao.SQLMarcaDAO;
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
        name = "EmitirProformaServlet",
        urlPatterns = {
            "/emision-proforma",
            "/buscar-productos-proforma"
        }
)
public class EmitirProformaServlet extends HttpServlet {

    private final MarcaDAO marcaDAO;
    private final ProveedorDAO proveedorDAO;
    private final ProductoDAO productoDAO;

    public EmitirProformaServlet() {
        this.marcaDAO = new SQLMarcaDAO(new SQLConexion());
        this.proveedorDAO = new SQLProveedorDAO(new SQLConexion());
        this.productoDAO = new SQLProductoDAO(new SQLConexion());
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

        List<String> marca = marcaDAO.listarTodos();
        request.setAttribute("marcas", marca);

        List<Proveedor> proveedor = proveedorDAO.listarTodos();
        request.setAttribute("proveedores", proveedor);
        

        request.getRequestDispatcher("WEB-INF/emitir-proforma/index.jsp")
                .forward(request, response);
    }

    private void buscarProductoProforma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String marca = request.getParameter("marca");
        String idproveedor = request.getParameter("idproveedor");
        
        System.out.println(nombre);
        System.out.println(marca);
        System.out.println(idproveedor);
        
        Integer idProv = idproveedor != null && idproveedor.isEmpty() ? null:Integer.parseInt(idproveedor);
        
        List<Producto> producto = productoDAO.buscar(nombre, marca, idProv);
        
        request.setAttribute("productos", producto);
        
        
        List<String> marcas = marcaDAO.listarTodos();
        request.setAttribute("marcas", marcas);

        List<Proveedor> proveedor = proveedorDAO.listarTodos();
        request.setAttribute("proveedores", proveedor);
        

        request.getRequestDispatcher("WEB-INF/emitir-proforma/index.jsp")
                .forward(request, response);
    }

}
