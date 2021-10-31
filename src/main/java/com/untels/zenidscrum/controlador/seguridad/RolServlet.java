package com.untels.zenidscrum.controlador.seguridad;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Privilegio;
import com.untels.zenidscrum.modelo.bean.Rol;
import com.untels.zenidscrum.modelo.dao.PrivilegioDAO;
import com.untels.zenidscrum.modelo.dao.RolDAO;
import com.untels.zenidscrum.modelo.dao.SQLPrivilegioDAO;
import com.untels.zenidscrum.modelo.dao.SQLRolDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "RolServlet",
        urlPatterns = {
            "/roles",
            "/formulario-rol",
            "/crear-rol",
            "/modificar-rol",
            "/inhabilitar-rol",
            "/habilitar-rol"
        }
)
public class RolServlet extends HttpServlet {

    private final RolDAO rolDAO;
    private final PrivilegioDAO privilegioDAO;

    public RolServlet() {
        SQLConexion conexion = new SQLConexion();
        rolDAO = new SQLRolDAO(conexion);
        privilegioDAO = new SQLPrivilegioDAO(conexion);
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
            case "/roles":
                listar(request, response);
                break;
            case "/formulario-rol":
                formulario(request, response);
                break;
            case "/crear-rol":
                crearRol(request, response);
                break;
            case "/modificar-rol":
                modificarRol(request, response);
                break;
            case "/inhabilitar-rol":
                inhabilitarRol(request, response);
                break;
            case "/habilitar-rol":
                habilitarRol(request, response);
                break;
            default:
                request.getRequestDispatcher("WEB-INF/404/index.jsp")
                        .forward(request, response);
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
        List<Rol> roles = rolDAO.listarTodos();

        request.setAttribute("roles", roles);

        request.getRequestDispatcher("WEB-INF/roles/index.jsp")
                .forward(request, response);
    }

    private void formulario(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String idRol = request.getParameter("idRol");

        if (idRol != null) {
            Rol rol = rolDAO.obtenerPorId(Integer.parseInt(idRol));
            request.setAttribute("rol", rol);
            request.setAttribute("privilegios", rol.getPrivilegios());
        } else {
            List<Privilegio> privilegios = new ArrayList<>();
            for (String nombre : Privilegio.TIPOS) {
                Privilegio privilegio = new Privilegio();
                privilegio.setNombre(nombre);
                privilegios.add(privilegio);
            }
            request.setAttribute("privilegios", privilegios);
        }

        request.getRequestDispatcher("WEB-INF/roles/formulario.jsp")
                .forward(request, response);
    }

    private void crearRol(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        List<Privilegio> privilegios = new ArrayList<>();
        for (String nombrePrivilegio : Privilegio.TIPOS) {
            Privilegio privilegio = new Privilegio();
            privilegio.setNombre(nombrePrivilegio);
            privilegio.setCrear(
                    parsearCheckbox(
                            request.getParameter("crear-" + nombrePrivilegio)));
            privilegio.setActualizar(
                    parsearCheckbox(
                            request.getParameter("actualizar-" + nombrePrivilegio)));
            privilegio.setListar(
                    parsearCheckbox(
                            request.getParameter("listar-" + nombrePrivilegio)));
            privilegio.setEliminar(
                    parsearCheckbox(request.getParameter("eliminar-" + nombrePrivilegio)));
            privilegios.add(privilegio);
        }

        Rol rol = new Rol();
        rol.setNombre(nombre);
        rol.setDescripcion(descripcion);
        rol.setHabilitado(true);

        if (rolDAO.crear(rol)) {
            for (Privilegio p : privilegios) {
                p.setIdRol(rol.getIdRol());
                privilegioDAO.crear(p);
            }

            response.sendRedirect("roles");
        } else {
            response.sendRedirect("formulario-rol");
        }

    }

    private void modificarRol(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String idRolStr = (String) request.getParameter("idRol");
        int idRol = Integer.parseInt(idRolStr);
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        List<Privilegio> privilegios = new ArrayList<>();
        for (String nombrePrivilegio : Privilegio.TIPOS) {
            Privilegio privilegio = new Privilegio();
            privilegio.setIdPrivilegio(
                    Integer.parseInt(
                            request.getParameter("id-privilegio-" + nombrePrivilegio)));
            privilegio.setNombre(nombrePrivilegio);
            privilegio.setCrear(
                    parsearCheckbox(
                            request.getParameter("crear-" + nombrePrivilegio)));
            privilegio.setActualizar(
                    parsearCheckbox(
                            request.getParameter("actualizar-" + nombrePrivilegio)));
            privilegio.setListar(
                    parsearCheckbox(
                            request.getParameter("listar-" + nombrePrivilegio)));
            privilegio.setEliminar(
                    parsearCheckbox(request.getParameter("eliminar-" + nombrePrivilegio)));
            privilegio.setIdRol(idRol);
            privilegios.add(privilegio);
        }

        Rol rol = new Rol();
        rol.setIdRol(idRol);
        rol.setNombre(nombre);
        rol.setDescripcion(descripcion);
        rol.setHabilitado(true);

        if (rolDAO.modificar(rol)) {
            for (Privilegio p : privilegios) {
                privilegioDAO.modificar(p);
            }

            response.sendRedirect("formulario-rol?idRol=" + idRolStr);
        } else {
            response.sendRedirect("formulario-rol");
        }
    }

    private void inhabilitarRol(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String idRolStr = (String) request.getParameter("idRol");
        int idRol = Integer.parseInt(idRolStr);

        rolDAO.inhabilitar(idRol);

        response.sendRedirect("roles");
    }

    private void habilitarRol(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String idRolStr = (String) request.getParameter("idRol");
        int idRol = Integer.parseInt(idRolStr);

        rolDAO.habilitar(idRol);

        response.sendRedirect("roles");
    }

    private boolean parsearCheckbox(String valor) {
        return valor != null && valor.equalsIgnoreCase("on");
    }

}
