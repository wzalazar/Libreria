/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Facundo
 */
@WebServlet(name = "mensajeModificarLibroCorrecto", urlPatterns = {"/mensajeModificarLibroCorrecto"})
public class mensajeModificarLibroCorrecto extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            RequestDispatcher incluir = request.getRequestDispatcher("incluir");
            incluir.include(request, response);
            RequestDispatcher top = request.getRequestDispatcher("top");
            top.include(request, response);
            RequestDispatcher mensajeCorrecto = request.getRequestDispatcher("mensajeCorrecto");
            mensajeCorrecto.include(request, response);
              out.println("<div style=\"position: absolute; left: 50%; top: 320px; margin-left: -208px; width: 258px;\"><a href=\"VerLibro\" title=\"Volver\" style=\"float:right;margin-top:10px;\"><button class=\"btn btn-red5\">Volver a modificar libro</button></a></div>"
                    );
            RequestDispatcher bottom = request.getRequestDispatcher("bottom");
            bottom.include(request, response);
        } 
        finally
        {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
}
