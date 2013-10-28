/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import Entidades.Libros;
import java.util.Enumeration;
import java.util.Hashtable;
import Datos.DatosLibros;

/**
 *
 * @author Facundo
 */
public class libroControladora implements ICrud{
    private Libros oLibro;
    private Hashtable oListaLibros = new Hashtable();
    private DatosLibros oDatosLibros=new DatosLibros();

    public Libros getoLibro()
    {
        return oLibro;
    }

    public void setoLibro(Libros oLibro)
    {
        this.oLibro = oLibro;
    }

    public Hashtable getoListaLibros()
    {
        return oListaLibros;
    }

    public void setoListaLibros(Hashtable oListaLibros)
    {
        this.oListaLibros = oListaLibros;
    }

    @Override
    public void Agregar(Object objeto) 
    {
        if(Buscar(objeto) == null)
        {
            oListaLibros.put(((Libros)objeto).getCodigo(), ((Libros)objeto));           
        }
    }

    @Override
    public void Eliminar(Object objeto)
    {
        if(Buscar(objeto)!=null)
        {
            oListaLibros.remove(((Libros)objeto));            
        }       
    }

    @Override
    public Object Buscar(Object objeto) 
    {
        return oListaLibros.get(((Libros)objeto).getCodigo());
    }    
    
    public Libros BuscarLibro(int codigo) throws Exception
    {
       Libros oLibros=oDatosLibros.BuscarLibro(codigo);
       return oLibros;
    }
    
    public double Total()
    {
        Enumeration enums = getoListaLibros().elements();
        double rta = 0;
        
        while(enums.hasMoreElements())
        {
            Libros aux = (Libros)enums.nextElement();
            rta += aux.getPrecio();
        }
        
        return rta;
    }
    
    public libroControladora(){}
}
