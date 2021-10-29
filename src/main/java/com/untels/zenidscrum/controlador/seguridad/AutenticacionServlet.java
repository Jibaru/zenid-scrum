package com.untels.zenidscrum.controlador.seguridad;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Usuario;
import com.untels.zenidscrum.modelo.dao.SQLUsuarioDAO;
import com.untels.zenidscrum.modelo.dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        name = "AutenticacionServlet",
        urlPatterns = {
            "/principal",
            "/iniciar-sesion",
            "/cerrar-sesion"
        }
)
public class AutenticacionServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO;

    public AutenticacionServlet() {
        this.usuarioDAO = new SQLUsuarioDAO(new SQLConexion());
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
            case "/principal":
                principal(request, response);
                break;
            case "/iniciar-sesion":
                iniciarSesion(request, response);
                break;
            case "/cerrar-sesion":
                cerrarSesion(request, response);
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

    private void iniciarSesion(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String correoElectronico = request.getParameter("correo-electronico");
        String contrasenia = request.getParameter("contrasenia");

        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreoElectronico(correoElectronico);

        if (usuario != null && contraseniaValida(contrasenia, usuario.getContrasenia())) {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario-autenticado", usuario);
        } else {
            request.setAttribute("mensaje", "Correo electrónico o contraseña incorrectas");
        }

        response.sendRedirect("principal");
    }

    private void cerrarSesion(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("usuario-autenticado");
        response.sendRedirect("principal");
    }

    private void principal(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/principal.jsp")
                .forward(request, response);
    }

    private boolean contraseniaValida(String contrasenia, String encriptado) {
        BCrypt.Result resultado = BCrypt.verifyer().verify(
                contrasenia.toCharArray(), encriptado);
        return resultado.verified;
    }

}
