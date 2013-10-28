/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Facundo
 */
public class LineaVenta 
{
    int codVenta;
    int codLibro;
    int Cantidad=1;
    double valorLibro;
    double SubTotal;
  

    public int getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }



    public int getCodLibro() {
        return codLibro;
    }

    public void setCodLibro(int codLibro) {
        this.codLibro = codLibro;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getValorLibro() {
        return valorLibro;
    }

    public void setValorLibro(double valorLibro) {
        this.valorLibro = valorLibro;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }

    public LineaVenta(int codVenta, int codLibro, int Cantidad, double valorLibro, double SubTotal) {
        setCodVenta(codVenta);
        setCodLibro(codLibro);
        setCantidad(Cantidad);
        setValorLibro(valorLibro);
        setSubTotal(SubTotal);
    }
    
      public LineaVenta(int codLibro) {
          setCodLibro(codLibro);
       
    }
    
    
}
