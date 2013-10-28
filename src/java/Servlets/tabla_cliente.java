package Servlets;

import Datos.DatosClientes;
import Datos.DatosLibros;
import Entidades.Clientes;
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


@WebServlet(name = "tabla_cliente", urlPatterns =
{
    "/tabla_cliente"
})

public class tabla_cliente extends HttpServlet
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
        HttpSession Session = request.getSession(true);
        RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        valida.include(request, response);

        try
        {
            Hashtable ListaClientes = oDatosClientes.ListarClientes();
            Enumeration eClientes = ListaClientes.elements();

            out.println("<div class=\"row-fluid\">"
                    + "<div class=\"span10\">"
                    + "<div class=\"box\">"
                    + "<div class=\"box-head\">"
                    + "<h3>Lista de clientes</h3>"
                    + "</div>"
                    + "<div class=\"box-content box-nomargin\">"
                    + "<table class=\"table table-striped table-bordered\">"
                    + "<thead>"
                    + "<tr>"
                    + "<th>ID</th>"
                    + "<th>Usuario</th>"
                    + "<th>Password</th>"
                    + "<th>Tipo</th>"
                    + "<th>Acciones</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>");

            while (eClientes.hasMoreElements())
            {
                Clientes aux = (Clientes) eClientes.nextElement();
                out.println("<tr>"
                        + "<td>" + aux.getID() + "</td>"
                        + "<td>" + aux.getUsuario() + "</a></td>"
                        + "<td>" + aux.getContraseña() + "</td>"                        
                        + "<td>" + aux.getTipo() + "</td>"  
                        + "<td class=\"actions\"><div class=\"btn-group\"> <form action=\"CargaClienteModificar\" method=\"post\"> <input type=\"hidden\" name=\"codigo\" value=\"" + aux.getID() +"\"/><input type=\"image\" value=\"Modificar\" class=\"btn btn-mini tip deleteRow\" title=\"Modificar\"/></form><form action=\"deleteCliente\" method=\"post\"><input type=\"hidden\" name=\"codigo\" value=\"" + aux.getID() +"\"/><input type=\"image\" value=\"Eliminar\" class=\"btn btn-mini tip deleteRow\" title=\"Eliminar\"/> </form> </div></td>"
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
