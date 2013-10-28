package Servlets;

import Datos.DatosClientes;
import Entidades.Clientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Logueo", urlPatterns =
{
    "/Logueo"
})
public class Logueo extends HttpServlet
{

    DatosClientes oDatosClientes;
    static int codCliente;

    @Override
    public void init() throws ServletException
    {
        try
        {
            oDatosClientes = new DatosClientes();
            oDatosClientes.Conectar();
        } catch (Exception ex)
        {
            Logger.getLogger(Logueo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy()
    {
        oDatosClientes.Desconectar();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession(true);


        try
        {

            out.println("<html> "
                    + " <head>"
                    + " <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
                    + " <title>Libreria Servlet 2012</title>");
            RequestDispatcher incluir = request.getRequestDispatcher("incluir");
            incluir.include(request, response);

            out.println(" </head>");
            out.println("<body class=\"login_body\">"
                    + "<div class=\"wrap\">"
                    + "<h2>Sistema Libreria</h2>"
                    + "<h4>Bienvenido a la pagina de logueo</h4>"
                    + "<form name ='formulario' action='Logueo'  method ='POST' autocomplete=\"off\"  class=\"validate\">"
                    + "<div class=\"alert alert-error\">"
                    + "<strong>Error!</strong> Please enter an username and a password."
                    + "</div>"
                    + "<div class=\"login\">"
                    + "<div class=\"email\">"
                    + "<label for=\"user\">Usuario</label><div class=\"email-input\"><div class=\"control-group\"><div class=\"input-prepend\"><span class=\"add-on\"><i class=\"icon-envelope\"></i></span><input id='Usuario' name='Usuario' class=\"{required:true}\" type=\"text\"></div></div></div>"
                    + "</div>"
                    + "<div class=\"pw\">"
                    + "<label for=\"pw\">Password</label><div class=\"pw-input\"><div class=\"control-group\"><div class=\"input-prepend\"><span class=\"add-on\"><i class=\"icon-lock\"></i></span><input d='Password' name='Password' class=\"{required:true}\" type=\"password\"></div></div></div>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"submit\">"
                    + "<button class=\"btn btn-red5\">Ingresar</button>"
                    + "</div>"
                    + "<div class=\"login social\">"
                    + "<div class=\"btn-row\">"
                    + "<a href=\"#\" class=\"btn btn-social btn-twitter\"><img src=\"img/twitter.png\" >Sign in with Twitter</a>"
                    + "<a href=\"#\" class=\"btn btn-social btn-fb\"><img src=\"img/facebook.png\" >Sign in with Facebook</a>"
                    + "</div>"
                    + "<div class=\"btn-row\">"
                    + "<a href=\"#\" class=\"btn btn-social btn-dr\"><img src=\"img/dribble.png\" >Sign in with Dribble</a>"
                    + "<a href=\"#\" class=\"btn btn-social btn-fo\"><img src=\"img/forrst.png\" >Sign in with Forrst</a>"
                    + "</div>"
                    + "</div>"
                    + "</form>"
                    + "</div>"
                    + "<script src=\"js/jquery.js\"></script>"
                    + "<script src=\"js/jquery.validate.min.js\"></script>"
                    + "<script src=\"js/jquery.metadata.js\"></script>"
                    + "<script src=\"js/error.js\"></script>");
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
        HttpSession Session = request.getSession(true);

        try
        {
            String Usuario = request.getParameter("Usuario");
            String Password = request.getParameter("Password");

            if (oDatosClientes.Login(Usuario, Password))
            {
                Clientes aux = oDatosClientes.BuscarCliente(Usuario);
                Session.setAttribute("Logueado", new String("True"));
                Session.setAttribute("Usuario", Usuario);
                codCliente=aux.getID();
               

                if ("Administrador".equals(aux.getTipo()))
                {
                    RequestDispatcher rd = request.getRequestDispatcher("/dashboard");
                    rd.forward(request, response);
                } else
                {
                    RequestDispatcher rd = request.getRequestDispatcher("/dashboard_carrito");
                    rd.forward(request, response);
                }

            } else
            {
                RequestDispatcher rd = request.getRequestDispatcher("/mensajeLogueoIncorrecto");
                rd.forward(request, response);
            }

        } catch (Exception ex)
        {
            
        }

    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
