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

@WebServlet(name = "ValidaLogin", urlPatterns =
{
    "/ValidaLogin"
})
public class ValidaLogin extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("Usuario") == null)
        {
            session.setAttribute("Mensaje", new String("Usted no esta logueado"));
            RequestDispatcher rd = request.getRequestDispatcher("/Logueo");
            rd.forward(request, response);

        } else
        {
            String loggedIn = (String) session.getAttribute("Logeado");
            
            if (loggedIn != null && !loggedIn.equals("true"))
            {
                session.setAttribute("Mensaje", new String("Usted no esta logueado"));
                RequestDispatcher rd = request.getRequestDispatcher("/Logueo");
                rd.forward(request, response);
            }
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
