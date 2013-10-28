/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controladora.ventasControladora;
import Entidades.Ventas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Facundo
 */
@WebServlet(name = "ComprarCarrito", urlPatterns = {"/ComprarCarrito"})
public class ComprarCarrito extends HttpServlet {
    
     

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
        HttpSession Session = request.getSession(true);
        RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        valida.include(request, response);
        try {
            /* TODO output your page here. You may use following sample code. */
            response.setContentType("text/html;charset=UTF-8");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComprarCarrito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComprarCarrito at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
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
            Ventas oVentas = new Ventas(Logueo.codCliente);
            ventasControladora oventasControladora = new ventasControladora(oVentas);
            
            try
            {
                if(oVentas.getoListaLineaVenta().size()==0)
                {
                 RequestDispatcher mensajeCompraIncorrecta = request.getRequestDispatcher("mensajeCompraIncorrecta");
                 mensajeCompraIncorrecta.include(request, response);
                }
                else
                {
                    oventasControladora.guardarVenta(oVentas);
                    Hashtable HT= new Hashtable();
                    oVentas.setoListaLineaVenta(HT);
                }
            }
            catch(Exception ex)
            {
                 RequestDispatcher mensajeCompraIncorrecta = request.getRequestDispatcher("mensajeCompraIncorrecta");
                 mensajeCompraIncorrecta.include(request, response);
            }
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher mensajeCompraCorrecta = request.getRequestDispatcher("mensajeCompraCorrecta");
            mensajeCompraCorrecta.include(request, response);
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
