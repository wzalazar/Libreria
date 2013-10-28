package Servlets;

import Datos.DatosClientes;
import Entidades.Clientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "formulario_cliente_modificar", urlPatterns =
{
    "/formulario_cliente_modificar"
})
public class formulario_cliente_modificar extends HttpServlet
{
        DatosClientes oDatosClientes;

    @Override
    public void init() throws ServletException
    {
        try
        {
            oDatosClientes = new DatosClientes();
            oDatosClientes.Conectar();
        } catch (Exception ex)
        {
            Logger.getLogger(Logueo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy()
    {
        oDatosClientes.Desconectar();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try
        {
            int codigo = Integer.valueOf(request.getParameter("codigo"));
            Clientes aux = oDatosClientes.BuscarCliente(codigo);
            
            out.println("<div class=\"row-fluid\">"
                + "<div class=\"span12\">"
                + "<div class=\"box\">"
                + "<div class=\"box-head\">"
                + "<h3>Agregar un Cliente</h3>"
                + "</div>"
                + "<div class=\"box-content\">"
                + "<form action=\"modificarCliente\" method=\"post\" class=\"form-horizontal\">"
                + "<legend>Información del Cliente</legend>"
                + "<div class=\"control-group\">"
                + "<label for=\"basicround\" class=\"control-label\">Usuario</label>"
                + "<div class=\"controls\">"
                + "<input name=\"codigo\"  value=\"" + aux.getID() + "\" id=\"basicround\" type=\"hidden\">"
                + "<input name=\"usuario\" class=\"required\" value=\"" + aux.getUsuario() + "\" id=\"basicround\" type=\"text\">"
                + "</div>"
                + "</div>"
                + "<div class=\"control-group\">"
                + "<label for=\"basic\" class=\"control-label\">Password</label>"
                + "<div class=\"controls\">"
                + "<input name=\"password\"  value=\"" + aux.getContraseña() + "\" id=\"genero\" class=\"required\" type=\"text\">"
                + "</div>"
                + "</div>"
                + "<div class=\"control-group\">"
                + "<label for=\"basic\" class=\"control-label\">Tipo</label>"
                + "<div class=\"controls\">"
                + "<input name=\"tipo\"  value=\"" + aux.getTipo() + "\" id=\"tipo\" class=\"required\" type=\"text\">"
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
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
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
