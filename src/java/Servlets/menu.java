package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "menu", urlPatterns =
{
    "/menu"
})
public class menu extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //HttpSession Session = request.getSession(true);
        //RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        //valida.include(request, response);
        out.println("<div class=\"main\">"
                + "<div class=\"container-fluid\">"
                + "<div class=\"content\">"
                + "<div class=\"row-fluid no-margin\">"
                + "<div class=\"span12\">"
                + "<ul class=\"quicktasks\">"
                + "<li> <a href=\"CargaLibro\"> <img src=\"img/icons/essen/32/book.png\" > <span>Agregar libros</span> </a> </li>"
                + "<li> <a href=\"VerLibro\"> <img src=\"img/icons/essen/32/book-finder.png\" > <span>Ver libros</span> </a> </li>"
                + "<li> <a href=\"CargaCliente\"> <img src=\"img/icons/essen/32/user-add.png\" > <span>Agregar cliente</span> </a> </li>"
                + "<li> <a href=\"VerCliente\"> <img src=\"img/icons/essen/32/user-finder.png\" > <span>Ver clientes</span> </a> </li>"
                + "<li> <a href=\"VerVentas\"> <img src=\"img/icons/essen/32/bank.png\" > <span>Ver ventas</span> </a> </li>"
                + "</ul>"
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
