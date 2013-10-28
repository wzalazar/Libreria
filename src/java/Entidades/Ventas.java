package Entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;


public class Ventas
{
    private int Codigo;
    private int idCliente;    
    public static Hashtable oListaLineaVenta= new Hashtable();    
    private double total;
    private Date fecha;

  

    public double getTotal()
    {
        return total;
    }

    public Enumeration CantVentas()
    {
        Enumeration cant = oListaLineaVenta.elements();
        
        return cant;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public Hashtable getoListaLineaVenta() {
        return oListaLineaVenta;
    }

    public void setoListaLineaVenta(Hashtable oListaLineaVenta) {
        this.oListaLineaVenta = oListaLineaVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Ventas(int idCliente) 
    {
        setIdCliente(idCliente);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    public Ventas(int Codigo, int idCliente, double total, Date fecha) 
    {
        setCodigo(Codigo);
        setIdCliente(idCliente);
        setTotal(total);
        setFecha(fecha);
    }

   
  
}
