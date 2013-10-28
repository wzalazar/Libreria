package Servlets;

import Datos.DatosLibros;
import Entidades.Libros;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "formulario_libro_modificar", urlPatterns =
{
    "/formulario_libro_modificar"
})
public class formulario_libro_modificar extends HttpServlet
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
        
        try
        {
            int codigo = Integer.valueOf(request.getParameter("codigo"));
            Libros aux = oDatosLibros.BuscarLibro(codigo);
            out.println("<div class=\"row-fluid\">"
                    + "<div class=\"span12\">"
                    + "<div class=\"box\">"
                    + "<div class=\"box-head\">"
                    + "<h3>Agregar un libro</h3>"
                    + "</div>"
                    + "<div class=\"box-content\">"
                    + "<form action=\"modificarLibro\" method=\"post\" class=\"form-horizontal\">"
                    + "<legend>Información del libro</legend>"
                    + "<div class=\"control-group\">"
                    + "<label for=\"basicround\" class=\"control-label\">Titulo</label>"
                    + "<div class=\"controls\">"
                    + "<input name=\"codigo\"  value=\"" + aux.getCodigo() + "\" id=\"basicround\" type=\"hidden\">"
                    + "<input name=\"titulo\"  value=\"" + aux.getNombre() + "\" id=\"basicround\" class=\"required\"  type=\"text\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"control-group\">"
                    + "<label for=\"basic\" class=\"control-label\">Genero</label>"
                    + "<div class=\"controls\">"
                    + "<input name=\"genero\"  value=\"" + aux.getGenero() + "\" id=\"genero\" class=\"required\"  type=\"text\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"control-group\">"
                    + "<label for=\"placeholder\" class=\"control-label\">Autor</label>"
                    + "<div class=\"controls\">"
                    + "<input name=\"autor\" value=\"" + aux.getAutor() + "\" id=\"placeholder\" class=\"required\"  type=\"text\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"control-group\">"
                    + "<label for=\"basic\" class=\"control-label\">Año</label>"
                    + "<div class=\"controls\">"
                    + "<input name=\"year\"  value=\"" + aux.getAño() + "\" id=\"basic\" class=\"required number\"  type=\"text\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"control-group\">"
                    + "<label for=\"twoicons\" class=\"control-label\">Precio</label>"
                    + "<div class=\"controls\">"
                    + "<div class=\"input-prepend input-append\"> <span class=\"add-on\">$</span>"
                    + "<input class=\"required number\" value=\"" + aux.getPrecio() + "\" name=\"precio\" id=\"twoicons\" type=\"text\">"
                    + "<span class=\"add-on\">.00</span> </div>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"form-actions\">"
                    + "<button class=\"btn btn-primary\" type=\"submit\">Modificar</button>"
                    + "<input class=\"btn btn-danger\" value=\"reset\" type=\"reset\">"
                    + "</div>"
                    + "</form>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "</div>");
        } 
        catch (Exception ex)
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
