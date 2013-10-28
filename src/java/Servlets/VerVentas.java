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

@WebServlet(name = "VerVentas", urlPatterns =
{
    "/VerVentas"
})
public class VerVentas extends HttpServlet
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
            RequestDispatcher incluir = request.getRequestDispatcher("incluir");
            incluir.include(request, response);
            RequestDispatcher top = request.getRequestDispatcher("top");
            top.include(request, response);
            RequestDispatcher menu = request.getRequestDispatcher("menu");
            menu.include(request, response);
            RequestDispatcher tabla_venta = request.getRequestDispatcher("tabla_venta");
            tabla_venta.include(request, response);
            RequestDispatcher bottom = request.getRequestDispatcher("bottom");
            bottom.include(request, response);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
