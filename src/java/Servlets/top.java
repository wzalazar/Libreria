/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

@WebServlet(name = "top", urlPatterns =
{
    "/top"
})
public class top extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //HttpSession Session = request.getSession(true);
        //RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        //valida.include(request, response);
        out.println("<div class=\"style-switcher\">"
                + "<h3>Style-switcher</h3>"
                + "<ul class=\"color\">"
                + "<li> <a href=\"#\" class=\"style\">Default</a> </li>"
                + "<li> <a href=\"#\" class=\"blue\">Blue</a> </li>"
                + "<li> <a href=\"#\" class=\"green\">Green</a> </li>"
                + "<li> <a href=\"#\" class=\"red\">Red</a> </li>"
                + "</ul>"
                + "<h3>Pattern-switcher</h3>"
                + "<ul class=\"pattern\">"
                + "<li> <a href=\"#\" class=\"default\">Default</a> </li>"
                + "<li> <a href=\"#\" class=\"dark\">Dark wood</a> </li>"
                + "<li><a href=\"#\" class=\"light\">Light</a></li>"
                + "<li><a href=\"#\" class=\"wood\">Wood</a></li>"
                + "<li><a href=\"#\" class=\"retina-wood\">Retina-wood</a></li>"
                + "<li><a href=\"#\" class=\"linen\">Linen</a></li>"
                + "<li><a href=\"#\" class=\"paper\">Paper</a></li>"
                + "</ul>"
                + "</div>"
                + "<div class=\"topbar\">"
                + "<div class=\"container-fluid\"> <a href=\"dashboard\" class=\"company\">Sistema libreria</a><p style=\"color:#FFF; position:absolute; right:96px;top:23px;\">Salir</p><a href=\"Logout\" title=\"Salir\"><img width=\"32\" height=\"32\" style=\"position:absolute;right:50px;top:15px;\" src=\"img/exit.png\"></a> </div>"
                + "</div>"
                + "<div class=\"breadcrumbs\">"
                + "<div class=\"container-fluid\">"
                + "<ul class=\"bread pull-left\">"
                + "<li> <a href=\"dashboard.html\"><i class=\"icon-home icon-white\"></i></a> </li>"
                //+ "<li> <a href=\"dashboard.html\"> Principal </a> </li>"
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
