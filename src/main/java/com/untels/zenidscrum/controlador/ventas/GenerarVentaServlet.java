package com.untels.zenidscrum.controlador.ventas;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Proforma;
import com.untels.zenidscrum.modelo.bean.Venta;
import com.untels.zenidscrum.modelo.dao.ProformaDAO;
import com.untels.zenidscrum.modelo.dao.SQLProformaDAO;
import com.untels.zenidscrum.modelo.dao.SQLVentaDAO;
import com.untels.zenidscrum.modelo.dao.VentaDAO;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "GenerarVentaServlet",
        urlPatterns = {
            "/seleccionar-proforma",
            "/buscar-proforma",
            "/generar-venta",
            "/ver-venta"
        }
)
public class GenerarVentaServlet extends HttpServlet {

    private final ProformaDAO proformaDAO;
    private final VentaDAO ventaDAO;

    public GenerarVentaServlet() {
        SQLConexion conexion = new SQLConexion();
        proformaDAO = new SQLProformaDAO(conexion);
        ventaDAO = new SQLVentaDAO(conexion);
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
            case "/buscar-proforma":
                buscarProforma(request, response);
                break;
            case "/seleccionar-proforma":
                seleccionarProformaVenta(request, response);
                break;
            case "/generar-venta":
                generarVenta(request, response);
                break;
            case "/ver-venta":
                verVenta(request, response);
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

    private void buscarProforma(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String nombreRef = request.getParameter("nombre-referencial");

        request.setAttribute("proformas", proformaDAO.listarPorNombreReferencial(nombreRef));

        request.getRequestDispatcher("WEB-INF/generar-venta/index.jsp")
                .forward(request, response);
    }

    private void seleccionarProformaVenta(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String idProforma = request.getParameter("idProforma");

        if (idProforma != null) {
            request.setAttribute("proforma", proformaDAO.obtenerPorId(Integer.parseInt(idProforma)));
        }

        request.getRequestDispatcher("WEB-INF/generar-venta/formulario.jsp")
                .forward(request, response);
    }

    private void generarVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
         String idProforma = request.getParameter("idProforma");
         String nombres = request.getParameter("nombre");
         String apePaterno = request.getParameter("ape-paterno");
         String apeMaterno = request.getParameter("ape-materno");
         String dni = request.getParameter("dni");
         String ruc = request.getParameter("ruc");
         String tipoDocumento = request.getParameter("tipo-documento");
         String comprobante = null;
         Proforma proforma = proformaDAO.obtenerPorId(Integer.parseInt(idProforma));
         
         if(tipoDocumento.equals("boleta")){
             comprobante = dni;
         }
         else{
             comprobante = ruc;
         }
         
         Venta v = new Venta();
         v.setNombres(nombres);
         v.setApePaterno(apePaterno);
         v.setApeMaterno(apeMaterno);
         v.setNumeroComprobante(comprobante);
         v.setTipoComprobante(tipoDocumento);
         v.setProforma(proforma);
         v.setFechaEmision(LocalDate.now());
         
         ventaDAO.Crear(v);
         
         response.sendRedirect("ver-venta?idVenta="
                + Integer.toString(v.getIdVenta()));
    }

    private void verVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int idVenta = Integer.parseInt(request.getParameter("idVenta"));
       int idProforma = ventaDAO.obtenerIdProformaPorIdVenta(idVenta);
       Venta venta = ventaDAO.obtenerPorId(idVenta);
       Proforma proforma = proformaDAO.obtenerPorId(idProforma);
       
       venta.setProforma(proforma);


        request.setAttribute("venta", venta);

        request.getRequestDispatcher("WEB-INF/generar-venta/venta.jsp")
                .forward(request, response);
    }

}
