package com.untels.zenidscrum.controlador.ventas;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Venta;
import com.untels.zenidscrum.modelo.dao.ReporteDAO;
import com.untels.zenidscrum.modelo.dao.SQLReporteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "GenerarReporte",
        urlPatterns = {
            "/GenerarReporte",
            "/buscar-venta-reporte",
            "/reporte"
        })
public class GenerarReporteServlet extends HttpServlet {

    private final ReporteDAO reporteDAO;

    public GenerarReporteServlet() {
        SQLConexion conexion = new SQLConexion();
        reporteDAO = new SQLReporteDAO(conexion);

//        proformaDAO = new SQLProformaDAO(conexion);
//        ventaDAO = new SQLVentaDAO(conexion);
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
            case "/buscar-venta-reporte":
                buscarventareporte(request, response);
                break;
            case "/reporte":
                request.getRequestDispatcher("WEB-INF/generar-venta-reporte/reporte.jsp")
                        .forward(request, response);
                // buscarventareporte(request, response);
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

    private void buscarventareporte(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String inicio = request.getParameter("fechainicio");
        String fin = request.getParameter("fechafinal");
        List<Venta> vr = reporteDAO.listarPorFecha(inicio, fin);

        request.setAttribute("ventas", reporteDAO.listarPorFecha(inicio, fin));
        if (vr.isEmpty()) {
            vr = null;
            request.setAttribute("ventas", vr);
        }
        request.getRequestDispatcher("WEB-INF/generar-venta-reporte/index.jsp")
                .forward(request, response);
    }
}
