package Servlets;

import Datos.DatosLibros;
import Entidades.Libros;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "modificarLibro", urlPatterns =
{
    "/modificarLibro"
})
public class modificarLibro extends HttpServlet
{
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
            out.println("<title>Servlet modificarLibro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet modificarLibro at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        DatosLibros oDatosLibros = new DatosLibros();
        
               
            int codigo = Integer.valueOf(request.getParameter("codigo"));
            String titulo = request.getParameter("titulo");
            String genero = request.getParameter("genero");
            String autor = request.getParameter("autor");
            try
            {
                double precio = Double.valueOf(request.getParameter("precio"));
                int year = Integer.valueOf(request.getParameter("year"));
                if((titulo!="")&&(genero!="")&&(autor!=""))
                {
                    Libros aux = new Libros(codigo, titulo, genero, precio, year, autor);

                    oDatosLibros.ModificarLibro(aux);
                    RequestDispatcher mensajeModificarLibroCorrecto = request.getRequestDispatcher("mensajeModificarLibroCorrecto");
                    mensajeModificarLibroCorrecto.forward(request, response);        
                }
                else
                {
                    response.setContentType("text/html;charset=UTF-8");
                    RequestDispatcher mensajeModificarLibroIncorrecto = request.getRequestDispatcher("mensajeModificarLibroIncorrecto");
                    mensajeModificarLibroIncorrecto.include(request, response);
                }
            }
            catch (Exception Ex)
            {
                   response.setContentType("text/html;charset=UTF-8");
                   RequestDispatcher mensajeModificarLibroIncorrecto = request.getRequestDispatcher("mensajeModificarLibroIncorrecto");
                   mensajeModificarLibroIncorrecto.include(request, response);
            }
        
             
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
