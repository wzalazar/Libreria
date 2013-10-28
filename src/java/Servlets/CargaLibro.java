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

@WebServlet(name = "CargaLibro", urlPatterns = {
    "/CargaLibro"
})
public class CargaLibro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession(true);
        RequestDispatcher valida = request.getRequestDispatcher("ValidaLogin");
        valida.include(request, response);
        try {
            RequestDispatcher incluir = request.getRequestDispatcher("incluir");
            incluir.include(request, response);
            RequestDispatcher top = request.getRequestDispatcher("top");
            top.include(request, response);
            RequestDispatcher menu = request.getRequestDispatcher("menu");
            menu.include(request, response);
            RequestDispatcher formulario_libro = request.getRequestDispatcher("formulario_libro");
            formulario_libro.include(request, response);
            RequestDispatcher bottom = request.getRequestDispatcher("bottom");
            bottom.include(request, response);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatosLibros BD = new DatosLibros();

        String titulo = request.getParameter("titulo");
        String genero = request.getParameter("genero");
        String autor = request.getParameter("autor");
        try
        {
        int year = Integer.valueOf(request.getParameter("year"));
        double precio = Double.valueOf(request.getParameter("precio"));
      
        if((titulo!="")&&(genero!="")&&(autor!=""))
        {
            Libros Lib = new Libros(titulo, genero, precio, year, autor);
            try {
                BD.AgregarLibro(Lib);
                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher mensajeCrearLibroCorrecto = request.getRequestDispatcher("mensajeCrearLibroCorrecto");
                mensajeCrearLibroCorrecto.include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CargaLibro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CargaLibro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CargaLibro.class.getName()).log(Level.SEVERE, null, ex);
            }


            processRequest(request, response);
        }
        else
        {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher mensajeCrearLibroIncorrecto = request.getRequestDispatcher("mensajeCrearLibroIncorrecto");
            mensajeCrearLibroIncorrecto.include(request, response);
        }
        }
        catch(Exception Ex)
        {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher mensajeCrearLibroIncorrecto = request.getRequestDispatcher("mensajeCrearLibroIncorrecto");
            mensajeCrearLibroIncorrecto.include(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
