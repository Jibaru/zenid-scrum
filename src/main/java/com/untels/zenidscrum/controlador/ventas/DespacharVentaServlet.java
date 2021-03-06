package com.untels.zenidscrum.controlador.ventas;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Venta;
import com.untels.zenidscrum.modelo.dao.SQLVentaDAO;
import com.untels.zenidscrum.modelo.dao.VentaDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "DespacharVentaServlet",
        urlPatterns = {
            "/despacho-venta",
            "/despachar-venta"
        }
)
public class DespacharVentaServlet extends HttpServlet {

    private VentaDAO ventaDAO;

    public DespacharVentaServlet() {
        SQLConexion conexion = new SQLConexion();
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
            case "/despacho-venta":
                despachoVenta(request, response);
                break;
            case "/despachar-venta":
                despacharVenta(request, response);
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

    private void despachoVenta(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String termino = request.getParameter("termino");
        String boleta = request.getParameter("boleta");
        String factura = request.getParameter("factura");

        if (termino != null || boleta != null || factura != null) {
            List<Venta> ventas = ventaDAO.listarPorTerminoBoletaFactura(
                    (termino != null ? termino : ""),
                    boleta != null,
                    factura != null);

            request.setAttribute("ventas", ventas);

            request.getRequestDispatcher("WEB-INF/despachar-venta/index.jsp")
                    .forward(request, response);
        } else {
            List<Venta> ventas = ventaDAO.listarTodos();

            request.setAttribute("ventas", ventas);

            request.getRequestDispatcher("WEB-INF/despachar-venta/index.jsp")
                    .forward(request, response);
        }
    }

    private void despacharVenta(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        int idVenta = Integer.parseInt(request.getParameter("idVenta"));

        ventaDAO.despachar(idVenta);

        response.sendRedirect("despacho-venta");
    }

}
