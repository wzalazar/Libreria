/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controladora.ventasControladora;
import Controladora.libroControladora;
import Datos.DatosLibros;
import Entidades.Libros;
import Entidades.Ventas;
import Entidades.LineaVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nazareno Oviedo
 */
@WebServlet(name = "tabla_micarrito", urlPatterns = {
    "/tabla_micarrito"
})
public class tabla_micarrito extends HttpServlet {
    
    
    

   

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
        libroControladora olibroControladora = new libroControladora();
        Ventas oVentas = new Ventas(Logueo.codCliente);
        ventasControladora oventasControladora = new ventasControladora(oVentas);
        HttpSession Session = request.getSession(true);
        RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        valida.include(request, response);
        
        try {
              if ((oventasControladora.getoVentas()).getoListaLineaVenta().elements() != null) {

                out.println("<div class=\"row-fluid\">"
                        + "<div class=\"span10\">"
                        + "<div class=\"box\">"
                        + "<div class=\"box-head\">"
                        + "<h3>Mi Carrito</h3>"
                        + "</div>"
                        + "<div class=\"box-content box-nomargin\">"
                        + "<table class=\"table table-striped table-bordered\">"
                        + "<thead>"
                        + "<tr>"
                        + "<th>Codigo</th>"
                        + "<th>Titulo</th>"
                        + "<th>Precio unitario</th>"
                        + "<th>Cantidad</th>"
                        + "<th>Subtotal</th>"
                        + "<th>Acciones</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>");
           
          
               
                
                Enumeration enumListaLineaVenta = oventasControladora.devolverLineVenta().elements();
                while (enumListaLineaVenta.hasMoreElements())
                {
                    
                    LineaVenta aux = (LineaVenta)enumListaLineaVenta.nextElement();
                    Libros oLibros=olibroControladora.BuscarLibro(aux.getCodLibro());
                    out.println("<tr>"
                            + "<td>" + aux.getCodLibro() + "</td>"
                            + "<td> " + oLibros.getNombre() + "</td>"
                            + "<td> " + aux.getValorLibro() + "</td>"
                            + "<td class=\"green\">" + aux.getCantidad() + "</td>"
                            + "<td class=\"green\">" + aux.getSubTotal() + "</td>"
                            + "<td class=\"actions\"><div class=\"btn-group\"> <form action=\"EliminaLibroCarrito\" method=\"post\"> <input type=\"hidden\" name=\"codigo\" value=\"" + aux.getCodLibro() + "\"/><input type=\"image\" value=\"Quitar\" class=\"btn btn-mini tip deleteRow\" title=\"Eliminar\"/></form></div></td>"
                            + "</tr>");
                }

                out.println("</tbody>"
                        + "</table>"
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "</div>");
            }
        } catch (Exception ex) {
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
