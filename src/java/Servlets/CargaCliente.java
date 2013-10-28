package Servlets;

import Datos.DatosClientes;
import Entidades.Clientes;
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

@WebServlet(name = "CargaCliente", urlPatterns =
{
    "/CargaCliente"
})
public class CargaCliente extends HttpServlet
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
            RequestDispatcher formulario_cliente = request.getRequestDispatcher("formulario_cliente");
            formulario_cliente.include(request, response);
            RequestDispatcher bottom = request.getRequestDispatcher("bottom");
            bottom.include(request, response);
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
        DatosClientes oDatosClientes = new DatosClientes();

        String Usuario = request.getParameter("usuario");
        String Password = request.getParameter("password");
        String Tipo = request.getParameter("tipo");

        if((Usuario!="")&&(Password!="")&&(Tipo!=""))
        {
        Clientes oCliente = new Clientes(Usuario, Password, Tipo);
       
        try
        {
            oDatosClientes.AgregarCliente(oCliente);
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher mensajeCrearUsuarioCorrecto = request.getRequestDispatcher("mensajeCrearUsuarioCorrecto");
            mensajeCrearUsuarioCorrecto.include(request, response);
        } catch (SQLException ex)
        {
            Logger.getLogger(CargaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(CargaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            Logger.getLogger(CargaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        processRequest(request, response);
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher mensajeCrearUsuarioIncorrecta = request.getRequestDispatcher("mensajeCrearUsuarioIncorrecta");
            mensajeCrearUsuarioIncorrecta.include(request, response);
           
        }

    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
