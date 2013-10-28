package Datos;

import Entidades.Generos;
import Entidades.Libros;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

public class DatosLibros extends BibliotecaDB
{

    public void AgregarLibro(Libros oLibro) throws SQLException, ClassNotFoundException, Exception
    {
        if (oLibro != null)
        {

            String sql = "INSERT INTO libros (Nombre, Genero, Precio, Year, Autor) VALUES ('"
                    + oLibro.getNombre() + "','" + oLibro.getGenero()
                    + "'," + oLibro.getPrecio() + "," + oLibro.getAño()
                    + ",'" + oLibro.getAutor() + "')";
            EjecutarSentencia(sql);
        }
    }

    public void ModificarLibro(Libros oLibro) throws SQLException, ClassNotFoundException, Exception
    {
        if (oLibro != null)
        {
            String sql = "UPDATE Libros SET Nombre = '"
                    + oLibro.getNombre() + "', Genero = '"
                    + oLibro.getGenero() + "', Precio = "
                    + oLibro.getPrecio() + ", Year = "
                    + oLibro.getAño() + ", Autor = '"
                    + oLibro.getAutor() + "'" 
                    + " WHERE codLibro = "
                    + oLibro.getCodigo();
            EjecutarSentencia(sql);
        }
    }

    public void EliminarLibro(int codLibro) throws SQLException, ClassNotFoundException, Exception
    {
        String sql = "UPDATE libros SET Estado=0 WHERE codLibro = " + codLibro;
        EjecutarSentencia(sql);
    }    

    public Hashtable ListarLibros() throws SQLException, ClassNotFoundException, Exception
    {
        Hashtable ListaRTA = new Hashtable();
        Libros aux = null;
        String sql = "SELECT * FROM libros WHERE Estado=1";
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Libros(Resultado.getInt("codLibro"), Resultado.getString("Nombre"), Resultado.getString("Genero"), Resultado.getDouble("Precio"), Resultado.getInt("Year"), Resultado.getString("Autor"));
            ListaRTA.put(aux.getCodigo(), aux);
        }

        return ListaRTA;
    }

    public Libros BuscarLibro(int codLibro) throws SQLException, ClassNotFoundException, Exception
    {
        Libros aux = null;
        String sql = "SELECT * FROM libros WHERE codLibro = " + codLibro + "";        
        PreparedStatement s = CrearSentencia(sql);
        System.out.println(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Libros(Resultado.getInt("codLibro"), Resultado.getString("Nombre"), Resultado.getString("Genero"), Resultado.getDouble("Precio"), Resultado.getInt("Year"), Resultado.getString("Autor"));
        }

        return aux;
    }

    public HashMap BuscarLibro(double precio) throws SQLException, ClassNotFoundException, Exception
    {
        HashMap ListaRTA = new HashMap();
        Libros aux = null;
        String sql = "SELECT * FROM libros WHERE Precio = " + precio;
        PreparedStatement s = CrearSentencia(sql);
        System.out.println(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Libros(Resultado.getInt("codLibro"), Resultado.getString("Nombre"), Resultado.getString("Genero"), Resultado.getDouble("Precio"), Resultado.getInt("Year"), Resultado.getString("Autor"));
            ListaRTA.put(aux.getCodigo(), aux);
        }

        return ListaRTA;
    }

    public Hashtable ListarLibrosGenero(String genero) throws Exception {
        Hashtable ListaRTA = new Hashtable();
        Libros aux = null;
        String sql = "SELECT * FROM libros WHERE Estado=1 AND Genero='"+genero+"'";
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Libros(Resultado.getInt("codLibro"), Resultado.getString("Nombre"), Resultado.getString("Genero"), Resultado.getDouble("Precio"), Resultado.getInt("Year"), Resultado.getString("Autor"));
            ListaRTA.put(aux.getCodigo(), aux);
        }

        return ListaRTA;
    }

    public Hashtable ListarLibrosPrecio(double precio) throws Exception 
    {
        Hashtable ListaRTA = new Hashtable();
        Libros aux = null;
        String sql = "SELECT * FROM libros WHERE Estado=1 AND Precio="+precio+"";
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Libros(Resultado.getInt("codLibro"), Resultado.getString("Nombre"), Resultado.getString("Genero"), Resultado.getDouble("Precio"), Resultado.getInt("Year"), Resultado.getString("Autor"));
            ListaRTA.put(aux.getCodigo(), aux);
        }

        return ListaRTA;   
    }

    public Hashtable ListarLibrosAño(int anio) throws Exception 
    {
        Hashtable ListaRTA = new Hashtable();
        Libros aux = null;
        String sql = "SELECT * FROM libros WHERE Estado=1 AND Year="+anio+"";
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Libros(Resultado.getInt("codLibro"), Resultado.getString("Nombre"), Resultado.getString("Genero"), Resultado.getDouble("Precio"), Resultado.getInt("Year"), Resultado.getString("Autor"));
            ListaRTA.put(aux.getCodigo(), aux);
        }

        return ListaRTA;
    }

    public Hashtable ListarLibrosAutor(String autor) throws Exception 
    {
         Hashtable ListaRTA = new Hashtable();
        Libros aux = null;
        String sql = "SELECT * FROM libros WHERE Estado=1 AND Autor='"+autor+"'";
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Libros(Resultado.getInt("codLibro"), Resultado.getString("Nombre"), Resultado.getString("Genero"), Resultado.getDouble("Precio"), Resultado.getInt("Year"), Resultado.getString("Autor"));
            ListaRTA.put(aux.getCodigo(), aux);
        }

        return ListaRTA;
    }
}
