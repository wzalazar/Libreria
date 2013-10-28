/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controladora.libroControladora;
import Controladora.ventasControladora;
import Datos.DatosLibros;
import Entidades.Libros;
import Entidades.Ventas;
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
 * @author Facundo
 */
@WebServlet(name = "filtroGenero", urlPatterns = {"/filtroGenero"})
public class filtroGenero extends HttpServlet {

   DatosLibros oDatosLibros;
    

    @Override
    public void init() throws ServletException
    {
        try
        {
            oDatosLibros = new DatosLibros();
            oDatosLibros.Conectar();
        } catch (Exception ex)
        {
            Logger.getLogger(Logueo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy()
    {
        oDatosLibros.Desconectar();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        libroControladora olibroControladora = new libroControladora();
        Ventas oVentas = new Ventas(Logueo.codCliente);
        ventasControladora oventasControladora = new ventasControladora(oVentas);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession(true);
        RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        valida.include(request, response);
        try
        {
            String genero = request.getParameter("genero");
            Hashtable ListaLibros = oDatosLibros.ListarLibrosGenero(genero);            
            Enumeration eLibros = ListaLibros.elements();
            out.println("<div class=\"main\">"
                    + "<div class=\"container-fluid\">"
                    + "<div class=\"content\">"
                    + "<div class=\"row-fluid no-margin\">"
                    + "<div class=\"span12\">"
                    + "</div>"
                    + "</div>");
            out.println("<div class=\"row-fluid\">"
                    + "<div class=\"span10\">"
                    + "<div class=\"box\">"
                    + "<div class=\"box-head\">"
                    + "<h3>Lista de libros</h3>"
                    + "</div>"
                    + "<div class=\"box-content box-nomargin\">"
                    + "<table class=\"table table-striped table-bordered\">"
                    + "<thead>"
                    + "<tr>"
                    + "<th>Codigo</th>"
                    + "<th>Titulo</th>"
                    + "<th class=\"mobile-hide\">Genero</th>"
                    + "<th>Autor</th>"
                    + "<th>Año</th>"
                    + "<th>Precio</th>"
                    + "<th>Acciones</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>");

            while (eLibros.hasMoreElements())
            {
                Libros aux = (Libros) eLibros.nextElement();

                out.println("<tr>"
                        + "<td><a href=\"#\" data-title=\"Lorem ipsum\" data-content=\"Content of Accusam\">" + aux.getCodigo() + "</a></td>"
                        + "<td> " + aux.getNombre() + "</td>"
                        + "<td class=\"mobile-hide\"><a href=\"CargaLibroCarritoFiltroGenero?genero="+aux.getGenero()+"\" >" + aux.getGenero() + "</a></td>"
                        + "<td class=\"green\"><a href=\"CargaLibroCarritoFiltroAutor?autor="+aux.getAutor()+"\" title=\"Ordenar por autor " +aux.getAutor()+"\">" + aux.getAutor() + "</a></td>"
                        + "<td class=\"green\"><a href=\"CargaLibroCarritoFiltroAnio?anio="+aux.getAño()+"\" title=\"Ordenar por año " +aux.getAño()+"\">" + aux.getAño() + "</a></td>"
                        + "<td class=\"green\"><a href=\"CargaLibroCarritoFiltroPrecio?precio="+aux.getPrecio()+"\" title=\"Ordenar por precio " +aux.getPrecio()+"\">" + aux.getPrecio() + "</a></td>"
                        + "<td class=\"actions\"><div class=\"btn-group\"> <form action=\"CargaLibroCarrito\" method=\"post\"> <input type=\"hidden\" name=\"codigo\" value=\"" + aux.getCodigo() +"\"/><input type=\"hidden\" name=\"nombre\" value=\"" + aux.getNombre() +"\"/><input type=\"image\" value=\"Agregar\" class=\"btn btn-mini tip deleteRow\" title=\"Agregar\"/></form></div></td>"
                        + "</tr>");
            }

            out.println("</tbody>"
                    + "</table>"
                    + "</div>"
                    + "</div><a href=\"dashboard_carrito\" title=\"Listar todos\" style=\"float:right;margin-top:10px;\"><button class=\"btn btn-red5\">Listar todos</button></a>"
                    + "</div>"
                    + "</div>");

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher tabla_micarrito = request.getRequestDispatcher("tabla_micarrito");
            tabla_micarrito.include(request, response);

            out.println("<div style=\"position:relative\" class=\"form-actions\">"
                    + "<p style=\"float:left;font-weight: bold; margin-top: 15px; font-size: 32px;\">Total=$ "+ oventasControladora.devolverTotal() +"</p><form action=\"ComprarCarrito\" method=\"post\"><input type=\"submit\" style=\"position:absolute;right:31px;top:18px;padding:16px;\" class=\"btn btn-red5\" value=\"Comprar\">"
                    + "</div>"
                    + "</div></div>");

            out.println();
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
