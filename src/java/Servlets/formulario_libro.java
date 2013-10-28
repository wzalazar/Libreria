package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "formulario_libro", urlPatterns =
{
    "/formulario_libro"
})
public class formulario_libro extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<div class=\"row-fluid\">"
                + "<div class=\"span12\">"
                + "<div class=\"box\">"
                + "<div class=\"box-head\">"
                + "<h3>Agregar un libro</h3>"
                + "</div>"
                + "<div class=\"box-content\">"
                + "<form action=\"CargaLibro\" method=\"post\" class=\"form-horizontal\">"
                + "<legend>Información del libro</legend>"
                + "<div class=\"control-group\">"
                + "<label for=\"basicround\" class=\"control-label\">Titulo</label>"
                + "<div class=\"controls\">"
                + "<input name=\"titulo\"  placeholder=\"Escriba el titulo...\" class=\"required\" id=\"basicround\" type=\"text\">"
                + "</div>"
                + "</div>"
                + "<div class=\"control-group\">"
                + "<label for=\"basic\" class=\"control-label\">Genero</label>"
                + "<div class=\"controls\">"
                + "<input name=\"genero\"  placeholder=\"Escriba el genero...\" id=\"genero\" class=\"required\" type=\"text\">"
                + "</div>"
                + "</div>"
                + "<div class=\"control-group\">"
                + "<label for=\"placeholder\" class=\"control-label\">Autor</label>"
                + "<div class=\"controls\">"
                + "<input name=\"autor\" placeholder=\"Escriba el autor...\" id=\"placeholder\" class=\"required\" type=\"text\">"
                + "</div>"
                + "</div>"
                + "<div class=\"control-group\">"
                + "<label for=\"basic\" class=\"control-label\">Año</label>"
                + "<div class=\"controls\">"
                + "<input name=\"year\"  placeholder=\"Escriba el año..\" id=\"basic\" class=\"required number\" type=\"text\">"
                + "</div>"
                + "</div>"
                + "<div class=\"control-group\">"
                + "<label for=\"twoicons\" class=\"control-label\">Precio</label>"
                + "<div class=\"controls\">"
                + "<div class=\"input-prepend input-append\"> <span class=\"add-on\">$</span>"
                + "<input class=\"required number\"  placeholder=\"Escriba el precio...\" name=\"precio\" id=\"twoicons\" type=\"text\">"
                + "<span class=\"add-on\">.00</span> </div>"
                + "</div>"
                + "</div>"
                + "<div class=\"form-actions\">"
                + "<button class=\"btn btn-primary\" type=\"submit\">Guardar</button>"
                + "<input class=\"btn btn-danger\" value=\"reset\" type=\"reset\">"
                + "</div>"
                + "</form>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</div>");
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
