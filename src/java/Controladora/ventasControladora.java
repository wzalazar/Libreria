package Controladora;


import Datos.DatosVenta;
import Entidades.LineaVenta;
import Entidades.Libros;
import Controladora.libroControladora;
import Entidades.Ventas;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

public class ventasControladora {

    private Ventas oVentas;
    private DatosVenta oDatosVenta= new DatosVenta();

    public Ventas getoVentas() {
        return oVentas;
    }

    public void setoVentas(Ventas oVentas) {
        setoVentas(oVentas);
    }

    public ventasControladora(Ventas auxVentas) {
        this.oVentas = auxVentas;
    }

    public ventasControladora() {
    }

    public Hashtable devolverLineVenta() {
        return Ventas.oListaLineaVenta;
    }

    public void agregarLineaVenta(LineaVenta auxLineaVenta, Ventas oVentas) throws Exception {
        int codLibro = auxLineaVenta.getCodLibro();
        libroControladora olibroControladora = new libroControladora();
        Libros oLibros = olibroControladora.BuscarLibro(codLibro);

        try {
            Enumeration enumListaLineaVenta = Ventas.oListaLineaVenta.elements();
            int encuentra = 0;
            while (enumListaLineaVenta.hasMoreElements()) {
                LineaVenta oLineaVenta = (LineaVenta) enumListaLineaVenta.nextElement();
                if (oLineaVenta.getCodLibro() == codLibro) {
                    oLineaVenta.setValorLibro(oLineaVenta.getValorLibro());
                    oLineaVenta.setCantidad(oLineaVenta.getCantidad() + 1);
                    oLineaVenta.setSubTotal(oLibros.getPrecio() * oLineaVenta.getCantidad());
                    Ventas.oListaLineaVenta.put(oLineaVenta.hashCode(), oLineaVenta);
                    encuentra = 1;
                }
            }

            if (encuentra == 0) {
                auxLineaVenta.setValorLibro(oLibros.getPrecio());
                auxLineaVenta.setSubTotal(oLibros.getPrecio());
                Ventas.oListaLineaVenta.put(auxLineaVenta.hashCode(), auxLineaVenta);

            }
        } catch (Exception ex) {
        }
    }

    public void eliminarLineaVenta(LineaVenta auxLineaVenta, Ventas oVentas) {
        int codLibro = auxLineaVenta.getCodLibro();
        Hashtable oListaLineaVenta = oVentas.getoListaLineaVenta();
        Enumeration enumListaLineaVenta = Ventas.oListaLineaVenta.elements();

        while (enumListaLineaVenta.hasMoreElements()) {
            LineaVenta oLineaVenta = (LineaVenta) enumListaLineaVenta.nextElement();
            if (oLineaVenta.getCodLibro() == codLibro) {
                oVentas.getoListaLineaVenta().remove(oLineaVenta.hashCode());
            }
        }
    }
    
    public double devolverTotal()
    {
        Hashtable oListaLineaVenta = oVentas.getoListaLineaVenta();
        double total=0;
        if (oListaLineaVenta!=null)
        {
            Enumeration enumListaLineaVenta = Ventas.oListaLineaVenta.elements();

            while (enumListaLineaVenta.hasMoreElements()) {
                LineaVenta oLineaVenta = (LineaVenta) enumListaLineaVenta.nextElement();
                total+=oLineaVenta.getSubTotal();

            }
        }
        return total;
    }

    public void guardarVenta(Ventas oVenta) throws SQLException, ClassNotFoundException, Exception {
        
        oDatosVenta.AgregarVenta(oVenta);
    }
}

