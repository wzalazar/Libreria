package Servlets;

import Datos.BibliotecaDB;
import Datos.DatosClientes;
import Datos.DatosLibros;
import Datos.DatosVenta;
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

@WebServlet(name = "tabla_venta", urlPatterns =
{
    "/tabla_venta"
})
public class tabla_venta extends HttpServlet
{

    DatosVenta oDatosVenta;

    @Override
    public void init() throws ServletException
    {
        try
        {
            oDatosVenta = new DatosVenta();
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession(true);
        RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        valida.include(request, response);

        try
        {
            
            Hashtable ListaVentas = oDatosVenta.ListarVentas();
            Enumeration eVentas = ListaVentas.elements();

            out.println("<div class=\"row-fluid\">"
                    + "<div class=\"span10\">"
                    + "<div class=\"box\">"
                    + "<div class=\"box-head\">"
                    + "<h3>Lista de ventas</h3>"
                    + "</div>"
                    + "<div class=\"box-content box-nomargin\">"
                    + "<table class=\"table table-striped table-bordered\">"
                    + "<thead>"
                    + "<tr>"
                    + "<th>Codigo</th>"
                    + "<th>Cliente</th>"
                    + "<th class=\"mobile-hide\">Fecha</th>"
                    + "<th>Total</th>"
                    + "<th>Acciones</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>");


            while (eVentas.hasMoreElements())
            {
                Ventas aux = (Ventas)eVentas.nextElement();
                 DatosClientes oDatosClientes=new DatosClientes();
                 String nombreCliente= oDatosClientes.BuscarCliente(aux.getIdCliente()).getUsuario();
                out.println("<tr>"
                        + "<td>" + aux.getCodigo() + "</td>"
                        + "<td>" + nombreCliente + "</td>"
                        + "<td class=\"mobile-hide\">" + aux.getFecha() + "</td>"
                        + "<td class=\"green\">" + aux.getTotal() + "</td>"                        
                        + "<td class=\"actions\"><div class=\"btn-group\"> <form action=\"CargaVentaDetalle\" method=\"post\"> <input type=\"hidden\" name=\"codigo\" value=\"" + aux.getCodigo() +"\"/><input type=\"image\" value=\"Ver Detalle\" class=\"btn btn-mini tip deleteRow\" title=\"Ver Detalle\"/></form></div></td>"
                        + "</tr>");
            }

            out.println("</tbody>"
                    + "</table>"
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
