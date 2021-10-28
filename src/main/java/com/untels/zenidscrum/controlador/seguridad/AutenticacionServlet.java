package com.untels.zenidscrum.controlador.seguridad;

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

@WebServlet(
        name = "AutenticacionServlet",
        urlPatterns = {
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

        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreoElectronico(correoElectronico);

        if (usuario != null) {
            // TODO: Validar contrasenia

            request.getRequestDispatcher("WEB-INF/principal.jsp")
                    .forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);
        }

    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

    }

}
