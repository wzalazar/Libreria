package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "incluir", urlPatterns =
{
    "/incluir"
})
public class incluir extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<meta content=\"width=device-width, user-scalable=no, initial-scale=1\" name=\"viewport\">");
        out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.css\">");
        out.println("<link rel=\"stylesheet\" href=\"css/jquery.fancybox.css\">");
        out.println("<link rel=\"stylesheet\" href=\"css/login.css\">");
        out.println("<link href=\"css/style.css\" rel=\"stylesheet\">");
        out.println("<script src=\"js/jquery.js\"></script> ");
        out.println("<script src=\"js/jquery.validate.min.js\"></script> ");
        out.println("<script src=\"js/error.js\"></script> ");
        out.println("<link href=\"css/error.css\" rel=\"stylesheet\">");
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
