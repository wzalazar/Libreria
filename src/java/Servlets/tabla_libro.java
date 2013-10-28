package Servlets;

import Datos.DatosClientes;
import Datos.DatosLibros;
import Entidades.Libros;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
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

@WebServlet(name = "tabla_libro", urlPatterns =
{
    "/tabla_libro"
})
public class tabla_libro extends HttpServlet
{

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession(true);
        RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        valida.include(request, response);

        try
        {
            Hashtable ListaLibros = oDatosLibros.ListarLibros();
            Enumeration eLibros = ListaLibros.elements();

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
                        + "<td>" + aux.getCodigo() + "</td>"
                        + "<td> " + aux.getNombre() + "</td>"
                        + "<td class=\"mobile-hide\">" + aux.getGenero() + "</td>"
                        + "<td class=\"green\">" + aux.getAutor() + "</td>"
                        + "<td class=\"green\">" + aux.getAño() + "</td>"
                        + "<td class=\"green\">" + aux.getPrecio() + "</td>"
                        + "<td class=\"actions\"><div class=\"btn-group\"> <form action=\"CargaLibroModificar\" method=\"post\"> <input type=\"hidden\" name=\"codigo\" value=\"" + aux.getCodigo() +"\"/><input type=\"image\" value=\"Modificar\" class=\"btn btn-mini tip deleteRow\" title=\"Modificar\"/></form><form action=\"deleteLibro\" method=\"post\"><input type=\"hidden\" name=\"codigo\" value=\"" + aux.getCodigo() +"\"/><input type=\"image\" value=\"Eliminar\" class=\"btn btn-mini tip deleteRow\" title=\"Eliminar\"/> </form> </div></td>"
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
