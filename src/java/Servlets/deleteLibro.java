package Servlets;

import Datos.DatosLibros;
import Entidades.Libros;
import com.google.common.util.concurrent.ExecutionError;
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

@WebServlet(name = "deleteLibro", urlPatterns =
{
    "/deleteLibro"
})
public class deleteLibro extends HttpServlet
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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deleteLibro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteLibro at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } 
        finally
        {
            out.close();
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
        try
        {
            int codigo = Integer.valueOf(request.getParameter("codigo"));
            oDatosLibros.EliminarLibro(codigo);
            RequestDispatcher CargaLibro = request.getRequestDispatcher("/VerLibro");
            CargaLibro.forward(request, response);
            processRequest(request, response);
        } 
        catch (Exception ex)
        {
        }
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
