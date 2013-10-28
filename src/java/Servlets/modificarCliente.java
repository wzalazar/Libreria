/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Datos.DatosClientes;
import Entidades.Clientes;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "modificarCliente", urlPatterns =
{
    "/modificarCliente"
})
public class modificarCliente extends HttpServlet
{

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            out.println("<title>Servlet modificarCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet modificarCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally
        {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        DatosClientes oDatosClientes = new DatosClientes();
        
        try
        {
            int id = Integer.valueOf(request.getParameter("codigo"));
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            String tipo = request.getParameter("tipo");
            if((usuario!="")&&(password!="")&&(tipo!=""))
            {
                Clientes aux = new Clientes(id, usuario, password, tipo);
                oDatosClientes.ModificarCliente(aux);
                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher mensajeModificarUsuarioCorrecto = request.getRequestDispatcher("mensajeModificarUsuarioCorrecto");
                mensajeModificarUsuarioCorrecto.include(request, response);  
            }
            else
            {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher mensajeModificarUsuarioIncorrecto = request.getRequestDispatcher("mensajeModificarUsuarioIncorrecto");
            mensajeModificarUsuarioIncorrecto.include(request, response);
            }
        }
        catch(Exception ex)
        {
            
        }
        
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
