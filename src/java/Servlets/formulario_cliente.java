package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "formulario_cliente", urlPatterns =
{
    "/formulario_cliente"
})
public class formulario_cliente extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<div class=\"row-fluid\">"
                + "<div class=\"span12\">"
                + "<div class=\"box\">"
                + "<div class=\"box-head\">"
                + "<h3>Agregar un Cliente</h3>"
                + "</div>"
                + "<div class=\"box-content\">"
                + "<form action=\"CargaCliente\" method=\"post\" class=\"form-horizontal\">"
                + "<legend>Información del cliente</legend>"
                + "<div class=\"control-group\">"
                + "<label for=\"basicround\" class=\"control-label\">Usuario</label>"
                + "<div class=\"controls\">"
                + "<input name=\"usuario\"  class=\"required\" placeholder=\"Escriba el nombre de usuario...\" id=\"basicround\" type=\"text\">"
                + "</div>"
                + "</div>"
                + "<div class=\"control-group\">"
                + "<label for=\"basic\" class=\"control-label\">Password</label>"
                + "<div class=\"controls\">"
                + "<input name=\"password\" class=\"required\"  placeholder=\"Escriba la contraseña...\" id=\"genero\" class=\"input-square\" type=\"text\">"
                + "</div>"
                + "</div>"
                + "<div class=\"control-group\">"
                + "<label for=\"basic\" class=\"control-label\">Tipo Usuario</label>"
                + "<div class=\"controls\">"
                + "<input name=\"tipo\" class=\"required\"  placeholder=\"Escriba el tipo de usuario...\" id=\"tipo\"  type=\"text\">"
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
