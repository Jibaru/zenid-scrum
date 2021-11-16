package com.untels.zenidscrum.controlador.seguridad;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.bean.Representante;
import com.untels.zenidscrum.modelo.dao.ProveedorDAO;
import com.untels.zenidscrum.modelo.dao.RepresentanteDAO;
import com.untels.zenidscrum.modelo.dao.SQLProveedorDAO;
import com.untels.zenidscrum.modelo.dao.SQLRepresentanteDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "Proveedor",
        urlPatterns = {
            "/proveedores",
            "/formulario-proveedor",
            "/crear-proveedor",
            "/modificar-proveedor",
            "/inhabilitar-proveedor",
            "/habilitar-proveedor"
        }
)
public class ProveedorServlet extends HttpServlet {

    private final ProveedorDAO proveedorDAO;
    private final RepresentanteDAO representanteDAO;

    public ProveedorServlet() {
        SQLConexion conexion = new SQLConexion();
        this.proveedorDAO = new SQLProveedorDAO(conexion);
        this.representanteDAO = new SQLRepresentanteDAO(conexion);
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
            case "/proveedores":
                listar(request, response);
                break;
            case "/formulario-proveedor":
                formulario(request, response);
                break;
            case "/crear-proveedor":
                crearProveedor(request, response);
                break;
            case "/modificar-proveedor":
                modificarProveedor(request, response);
                break;
            case "/inhabilitar-proveedor":
                inhabilitarProveedor(request, response);
                break;
            case "/habilitar-proveedor":
                habilitarProveedor(request, response);
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
        List<Proveedor> proveedores = proveedorDAO.listarTodos();

        request.setAttribute("proveedores", proveedores);

        request.getRequestDispatcher("WEB-INF/proveedores/index.jsp")
                .forward(request, response);
    }

    private void formulario(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String idProveedor = request.getParameter("idProveedor");

        if (idProveedor != null) {
            Proveedor proveedor = proveedorDAO.obtenerPorId(Integer.parseInt(idProveedor));
            request.setAttribute("proveedor", proveedor);
        }

        request.getRequestDispatcher("WEB-INF/proveedores/formulario.jsp")
                .forward(request, response);
    }

    private void crearProveedor(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String nombre = request.getParameter("nombre");
        String ruc = request.getParameter("ruc");
        String correoElectronico = request.getParameter("correo-electronico");
        String telefono = request.getParameter("telefono");
        String nombreRep = request.getParameter("nombre-representante");
        String correoElectronicoRep = request.getParameter("correo-electronico-representante");
        String telefonoRep = request.getParameter("telefono-representante");

        Representante rep = new Representante();
        rep.setNombre(nombreRep);
        rep.setCorreoElectronico(correoElectronicoRep);
        rep.setTelefono(telefonoRep);

        if (representanteDAO.crear(rep)) {
            Proveedor pr = new Proveedor();
            pr.setRepresentante(rep);
            pr.setNombre(nombre);
            pr.setRuc(ruc);
            pr.setCorreoElectronico(correoElectronico);
            pr.setTelefono(telefono);
            pr.setHabilitado(true);

            proveedorDAO.crear(pr);

            response.sendRedirect("proveedores");
        } else {
            response.sendRedirect("formulario-proveedor");
        }
    }

    private void modificarProveedor(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String nombre = request.getParameter("nombre");
        String ruc = request.getParameter("ruc");
        String correoElectronico = request.getParameter("correo-electronico");
        String telefono = request.getParameter("telefono");
        String nombreRep = request.getParameter("nombre-representante");
        String correoElectronicoRep = request.getParameter("correo-electronico-representante");
        String telefonoRep = request.getParameter("telefono-representante");

        Proveedor pr = proveedorDAO.obtenerPorId(idProveedor);
        pr.setNombre(nombre);
        pr.setRuc(ruc);
        pr.setCorreoElectronico(correoElectronico);
        pr.setTelefono(telefono);

        Representante rep = pr.getRepresentante();
        rep.setNombre(nombreRep);
        rep.setCorreoElectronico(correoElectronicoRep);
        rep.setTelefono(telefonoRep);

        if (representanteDAO.modificar(rep)) {
            proveedorDAO.modificar(pr);
        }
        response.sendRedirect("formulario-proveedor?idProveedor=" + idProveedor);
    }

    private void inhabilitarProveedor(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        proveedorDAO.inhabilitar(idProveedor);
        response.sendRedirect("proveedores");
    }

    private void habilitarProveedor(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        proveedorDAO.habilitar(idProveedor);
        response.sendRedirect("proveedores");
    }

}
