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

@WebServlet(name = "Principal", urlPatterns =
{
    "/Principal"
})
public class Principal extends HttpServlet
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
            out.println("<html> "
                    + "<head>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
                    + "<title>Libreria Servlet 2012</title>"
                    + "</head>"
                    + "<body>"
                    + "<br>"
                    + "<h1><center> Ud. se ha logueado como " + (String) Session.getAttribute("Usuario") + "</center></h1><br>"
                    + "<a href='Logout'>  <center> @@ -->> Salir <<-- @@ </center> </a>"
                    + "</body> </html>");
        } finally
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
