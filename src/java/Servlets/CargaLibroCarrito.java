
package Servlets;

import Servlets.Logueo;
import Datos.DatosLibros;
import Controladora.ventasControladora;
import Entidades.Clientes;
import Entidades.Libros;
import Entidades.LineaVenta;
import Entidades.Ventas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Session;
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
@WebServlet(name = "CargaLibroCarrito", urlPatterns =
{
    "/CargaLibroCarrito"
})
public class CargaLibroCarrito extends HttpServlet
{
    Ventas oVentas;
    LineaVenta oLineaVenta;

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CargaLibroCarrito</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CargaLibroCarrito at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally
        {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            
            Clientes usuario;
            int codLibro = Integer.valueOf(request.getParameter("codigo"));
            oVentas=new Ventas(Logueo.codCliente);
            ventasControladora oventasControladora=new ventasControladora(oVentas);
            oLineaVenta=new LineaVenta(codLibro);
            oventasControladora.agregarLineaVenta(oLineaVenta,oVentas);
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher dash_carrito = request.getRequestDispatcher("dashboard_carrito");
            dash_carrito.include(request, response);

        } catch (Exception ex)
        {
            
        }
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}
