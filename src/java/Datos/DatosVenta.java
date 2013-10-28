
package Datos;

import Entidades.Clientes;
import Entidades.Libros;
import Entidades.LineaVenta;
import Entidades.Ventas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Date;


public class DatosVenta extends BibliotecaDB
{
    private DatosLibros oDatosLibros = new DatosLibros();
    private DatosClientes oDatosClientes = new DatosClientes();  
    Date fecha= new Date();
    
    public void AgregarVenta(Ventas oVenta) throws SQLException, ClassNotFoundException, Exception
    {
        if(oVenta != null)
        {
            Enumeration lineaVenta= oVenta.getoListaLineaVenta().elements();
            double total=0;
            LineaVenta aux = null;
            String sql = "INSERT INTO ventas (idCliente, Total, Fecha) VALUES (" + oVenta.getIdCliente() + "," + oVenta.getTotal() +", (SELECT NOW()))";
            PreparedStatement s = CrearSentencia(sql);
            EjecutarSentencia(sql);
            sql= "SELECT MAX(codVenta) AS codVenta FROM ventas";
            s = CrearSentencia(sql);
            ResultSet Resultado = Consultar(s);
            int codigoDeLaVenta=0;
            
            while (Resultado.next())
            {
               codigoDeLaVenta = Resultado.getInt("codVenta");
            }
            
            while(lineaVenta.hasMoreElements())
            {
                aux = (LineaVenta)lineaVenta.nextElement();
                String sqls = "INSERT INTO lineaventa (codVenta, codLibro, Cantidad, valorLibro, subTotal) VALUES (" + codigoDeLaVenta + "," + aux.getCodLibro() + ","+aux.getCantidad()+ "," + aux.getValorLibro()+ "," +aux.getSubTotal()+")";
                total+=aux.getSubTotal();
                s = CrearSentencia(sqls);
                EjecutarSentencia(sqls);
            } 
            String sqls= "UPDATE ventas SET total="+total+" WHERE codVenta="+codigoDeLaVenta;
            s = CrearSentencia(sqls);
            EjecutarSentencia(sqls);
        }
        
   }
    
    public Ventas ListarVentasCodigo(int codVenta) throws SQLException, ClassNotFoundException, Exception
    {
        
        String sql = "SELECT * FROM ventas WHERE codVenta="+codVenta;
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);
        Ventas rta= null;
        
        while(Resultado.next())
        {    
            rta = new Ventas(Resultado.getInt("codVenta"),Resultado.getInt("idCliente"),Resultado.getDouble("Total"),Resultado.getDate("Fecha"));
            
        }
        
        return rta;
    }
    
      public Hashtable ListarVentas() throws SQLException, ClassNotFoundException, Exception
    {
        Hashtable ListaRTA = new Hashtable();
        String sql = "SELECT * FROM ventas";
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);
        Ventas rta= null;
        
        while(Resultado.next())
        {    
            rta = new Ventas(Resultado.getInt("codVenta"),Resultado.getInt("idCliente"),Resultado.getDouble("Total"),Resultado.getDate("Fecha"));
            ListaRTA.put(rta.getCodigo(),rta);
        }
        
        return ListaRTA;
    }
    
    public Hashtable ListarLineaVenta(int Codigo) throws SQLException, ClassNotFoundException, Exception
    {        
        String sql = "SELECT * FROM lineaventa WHERE codVenta = " + Codigo;
        PreparedStatement s = CrearSentencia(sql);
        ResultSet Resultado = Consultar(s);
        LineaVenta rta= null;
        Hashtable ListaRTA= new Hashtable();
        int contador=1;
        while(Resultado.next())
        {            
            rta= new LineaVenta(Resultado.getInt("codVenta"),Resultado.getInt("CodLibro"),Resultado.getInt("Cantidad"),Resultado.getFloat("valorLibro"),Resultado.getDouble("subTotal"));       
            ListaRTA.put(contador, rta);
            contador++;
        }
        
        return ListaRTA;
    }
}
