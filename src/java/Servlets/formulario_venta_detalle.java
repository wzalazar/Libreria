/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controladora.libroControladora;
import Controladora.ventasControladora;
import Datos.DatosLibros;
import Datos.DatosClientes;
import Datos.DatosVenta;
import Entidades.Libros;
import Entidades.LineaVenta;
import Entidades.Ventas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
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
@WebServlet(name = "formulario_venta_detalle", urlPatterns =
{
    "/formulario_venta_detalle"
})
public class formulario_venta_detalle extends HttpServlet
{

    DatosVenta oDatosVenta;
    DatosLibros oDatosLibros;
    Hashtable ListaLineaVenta= new Hashtable();
    
    

    @Override
    public void init() throws ServletException
    {
        try
        {
            oDatosVenta = new DatosVenta();
            oDatosLibros = new DatosLibros();
            oDatosVenta.Conectar();
        } catch (Exception ex)
        {
            Logger.getLogger(Logueo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy()
    {
        oDatosVenta.Desconectar();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession(true);
        RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        valida.include(request, response);
        try
        {
            int codigo = Integer.valueOf(request.getParameter("codigo"));
            Ventas auxVenta=oDatosVenta.ListarVentasCodigo(codigo);
            ListaLineaVenta = oDatosVenta.ListarLineaVenta(codigo);
            Enumeration enumListaLineaVenta = ListaLineaVenta.elements();     
            DatosClientes oDatosClientes=new DatosClientes();
            String nombreCliente= oDatosClientes.BuscarCliente(auxVenta.getIdCliente()).getUsuario();
            out.println("<div class=\"row-fluid\">"
                    + "<div class=\"span10\">"
                    + "<div class=\"box\">"
                    + "<div class=\"box-head\">"
                    + "<h3>Detalle de Venta</h3>"
                    + "</div>"
                    + "<div class=\"box-content box-nomargin\">"
                    + "<table class=\"table table-striped table-bordered\">"
                    + "<thead>"
                    + "<tr>"
                    + "<th>Codigo</th>"
                    + "<th>Cliente</th>"
                    + "<th class=\"mobile-hide\">Fecha</th>"
                    + "<th>Total</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
                    + "<tr>"
                    + "<td><a href=\"#\" data-title=\"Lorem ipsum\" data-content=\"Content of Accusam\">" + auxVenta.getCodigo() + "</a></td>"
                    + "<td>" + nombreCliente + "</td>"
                    + "<td class=\"mobile-hide\">" + auxVenta.getFecha() + "</td>"
                    + "<td class=\"green\">" + auxVenta.getTotal() + "</td>"                    
                    + "</tr>"
                    + "</tbody>"
                    + "</table>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "</div>");
         
            
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
                       
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>");
           
          
               
                 
                 while (enumListaLineaVenta.hasMoreElements())
                 {
                    LineaVenta auxLineaVenta=(LineaVenta)enumListaLineaVenta.nextElement();
                    
                    int auxcodigo=auxLineaVenta.getCodLibro();
                    String nombre=(oDatosLibros.BuscarLibro(auxcodigo)).getNombre();
                    out.println("<tr>"
                            + "<td>" + auxLineaVenta.getCodLibro() + "</td>"
                            + "<td> " + nombre + "</td>"
                            + "<td> " + auxLineaVenta.getValorLibro() + "</td>"
                            + "<td class=\"green\">" + auxLineaVenta.getCantidad() + "</td>"
                            + "<td class=\"green\">" + auxLineaVenta.getSubTotal() + "</td>"
                           
                            + "</tr>");
                }

            out.println("</tbody>"
                    + "</table>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "</div>");
            
            
        } catch (Exception ex)
        {
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}
