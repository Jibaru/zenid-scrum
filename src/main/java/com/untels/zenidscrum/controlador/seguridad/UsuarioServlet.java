package com.untels.zenidscrum.controlador.seguridad;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Usuario;
import com.untels.zenidscrum.modelo.dao.RolDAO;
import com.untels.zenidscrum.modelo.dao.SQLRolDAO;
import com.untels.zenidscrum.modelo.dao.SQLUsuarioDAO;
import com.untels.zenidscrum.modelo.dao.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "UsuarioServlet",
        urlPatterns = {
            "/usuarios",
            "/formulario-usuario",
            "/crear-usuario",
            "/modificar-usuario",
            "/inhabilitar-usuario",
            "/habilitar-usuario"
        }
)
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO;
    private final RolDAO rolDAO;

    public UsuarioServlet() {
        SQLConexion conexion = new SQLConexion();
        usuarioDAO = new SQLUsuarioDAO(conexion);
        rolDAO = new SQLRolDAO(conexion);
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
            case "/usuarios":
                listar(request, response);
                break;
            case "/formulario-usuario":
                formularioUsuario(request, response);
                break;
            case "/crear-usuario":
                crearUsuario(request, response);
                break;
            case "/modificar-usuario":
                modificarUsuario(request, response);
                break;
            case "/inhabilitar-usuario":
                inhabilitarUsuario(request, response);
                break;
            case "/habilitar-usuario":
                habilitarUsuario(request, response);
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
        List<Usuario> usuarios = usuarioDAO.listarTodos();

        request.setAttribute("usuarios", usuarios);

        request.getRequestDispatcher("WEB-INF/usuarios/index.jsp")
                .forward(request, response);
    }

    private void formularioUsuario(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String idUsuario = request.getParameter("idUsuario");

        if (idUsuario != null) {
            Usuario usuario = usuarioDAO.obtenerPorId(Integer.parseInt(idUsuario));
            request.setAttribute("usuario", usuario);
        }
        request.setAttribute("roles", rolDAO.listarTodos());

        request.getRequestDispatcher("WEB-INF/usuarios/formulario.jsp")
                .forward(request, response);
    }

    private void crearUsuario(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String correoElectronico = request.getParameter("correo-electronico");
        String contrasenia = request.getParameter("contrasenia");
        String idRol = request.getParameter("rol");

        if (usuarioDAO.obtenerUsuarioPorCorreoElectronico(correoElectronico) != null) {
            request.setAttribute("mensaje", "Correo electrónico existente");
            response.sendRedirect("formulario-usuario");
            return;
        }

        Usuario us = new Usuario();
        us.setNombre(nombre);
        us.setContrasenia(BCrypt.withDefaults().hashToString(12, contrasenia.toCharArray()));
        us.setCorreoElectronico(correoElectronico);
        us.setRol(rolDAO.obtenerPorId(Integer.parseInt(idRol)));
        us.setHabilitado(true);

        usuarioDAO.crear(us);

        response.sendRedirect("usuarios");

    }

    private void modificarUsuario(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        String idUsuario = request.getParameter("idUsuario");
        String nombre = request.getParameter("nombre");
        String correoElectronico = request.getParameter("correo-electronico");
        String correoElectronicoActual = request.getParameter("correo-electronico-actual");
        String idRol = request.getParameter("rol");

        if (!correoElectronico.equalsIgnoreCase(correoElectronicoActual)
                && usuarioDAO.obtenerUsuarioPorCorreoElectronico(correoElectronico) != null) {
            request.setAttribute("mensaje", "Correo electrónico existente");
            response.sendRedirect("formulario-usuario?idUsuario=" + idUsuario);
            return;
        }

        Usuario us = new Usuario();
        us.setIdUsuario(Integer.parseInt(idUsuario));
        us.setNombre(nombre);
        us.setCorreoElectronico(correoElectronico);
        us.setRol(rolDAO.obtenerPorId(Integer.parseInt(idRol)));
        us.setHabilitado(true);

        usuarioDAO.modificar(us);

        response.sendRedirect("formulario-usuario?idUsuario=" + idUsuario);
    }

    private void inhabilitarUsuario(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String idUsuarioStr = request.getParameter("idUsuario");
        int idUsuario = Integer.parseInt(idUsuarioStr);

        usuarioDAO.inhabilitar(idUsuario);

        response.sendRedirect("usuarios");
    }

    private void habilitarUsuario(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String idUsuarioStr = request.getParameter("idUsuario");
        int idUsuario = Integer.parseInt(idUsuarioStr);

        usuarioDAO.habilitar(idUsuario);

        response.sendRedirect("usuarios");
    }

}
