package Datos;

import Entidades.Clientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class DatosClientes extends BibliotecaDB
{

    public Boolean Login(String Usuario, String Contraseña) throws SQLException, ClassNotFoundException, Exception
    {
        String sql = "SELECT * FROM clientes WHERE Usuario= '" + Usuario + "' AND Password= '" + Contraseña + "' AND Estado=1";
        PreparedStatement s = CrearSentencia(sql);
        ResultSet rows = Consultar(s);

        if (rows.next())
        {
            return true;
        }

        return false;
    }

    public void AgregarCliente(Clientes oCliente) throws SQLException, ClassNotFoundException, Exception
    {
        if (oCliente != null)
        {
            String sql = "INSERT INTO Clientes (Usuario, Password, Tipo) VALUES ('" + oCliente.getUsuario() + "','" + oCliente.getContraseña() + "','" + oCliente.getTipo() + "')";
            EjecutarSentencia(sql);
        }
    }

    public void ModificarCliente(Clientes oCliente) throws SQLException, ClassNotFoundException, Exception
    {
        if (oCliente != null)
        {
            String sql = "UPDATE Clientes SET Usuario = '" + oCliente.getUsuario()
                    + "', Password = '" + oCliente.getContraseña()
                    + "', Tipo = '" + oCliente.getTipo()
                    + "' WHERE idCliente = " + oCliente.getID();
            EjecutarSentencia(sql);
        }
    }

    public void EliminarCliente(int ID) throws SQLException, ClassNotFoundException, Exception
    {
        String sql = "UPDATE clientes SET Estado=0 WHERE idCliente = " + ID +" AND Tipo='Cliente'";
        EjecutarSentencia(sql);
    }

    public Clientes BuscarCliente(int codigo) throws SQLException, ClassNotFoundException, Exception
    {
        Clientes aux = null;
        String sql = "SELECT * FROM clientes WHERE idCliente = " + codigo + " LIMIT 1";
        PreparedStatement s = CrearSentencia(sql);
        System.out.println(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Clientes(Resultado.getInt("idCliente"), Resultado.getString("Usuario"), Resultado.getString("Password"), Resultado.getString("Tipo"));
        }

        return aux;
    }
    
    public Clientes BuscarCliente(String Usuario) throws SQLException, ClassNotFoundException, Exception
    {
        Clientes aux = null;
        String sql = "SELECT * FROM clientes WHERE Usuario = '" + Usuario + "' LIMIT 1";
        PreparedStatement s = CrearSentencia(sql);
        System.out.println(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Clientes(Resultado.getInt("idCliente"), Resultado.getString("Usuario"), Resultado.getString("Password"), Resultado.getString("Tipo"));
        }

        return aux;
    }

    public Hashtable ListarClientes() throws SQLException, ClassNotFoundException, Exception
    {
        Hashtable ListaRTA = new Hashtable();
        Clientes aux = null;
        String sql = "SELECT * FROM Clientes WHERE Estado=1";
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);

        while (Resultado.next())
        {
            aux = new Clientes(Resultado.getInt("idCliente"), Resultado.getString("Usuario"), Resultado.getString("Password"), Resultado.getString("Tipo"));
            ListaRTA.put(aux.getID(), aux);
        }

        return ListaRTA;
    }
}
