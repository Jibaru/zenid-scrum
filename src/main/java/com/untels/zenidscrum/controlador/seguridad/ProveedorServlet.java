/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.untels.zenidscrum.controlador.seguridad;

import com.untels.zenidscrum.acceso.datos.SQLConexion;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.dao.ProveedorDAO;
import com.untels.zenidscrum.modelo.dao.SQLProveedorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        name = "Proveedor", 
        urlPatterns = {"/Proveedor","/fproveedor"})

public class ProveedorServlet extends HttpServlet {
    private final  ProveedorDAO proveedordao;
    
    public ProveedorServlet(){
        SQLConexion conexion = new SQLConexion();
    this.proveedordao = new SQLProveedorDAO(conexion);
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
        try (PrintWriter out = response.getWriter()) {
           
            String path = request.getServletPath();
            
            if (path.equals("/fproveedor")) {
                
                listar(request, response);
                request.getRequestDispatcher("WEB-INF/proveedores/index.jsp").forward(request, response);

            }
            
            
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
        List<Proveedor> proveedores = proveedordao.listarTodos();

        request.setAttribute("proveedores", proveedores);

        request.getRequestDispatcher("WEB-INF/proveedores/index.jsp")
                .forward(request, response);
    }

    
}
