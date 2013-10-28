package Entidades;

public class Libros
{
    private int Codigo;
    private String Nombre;
    private String Genero;
    private double Precio;
    private int Año;
    private String Autor;

    public String getNombre()
    {
        return Nombre;
    }

    public void setNombre(String Nombre)
    {
        this.Nombre = Nombre;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }
    
    public int getCodigo()
    {
        return Codigo;
    }

    public void setCodigo(int Codigo)
    {
        this.Codigo = Codigo;
    }

    public double getPrecio()
    {
        return Precio;
    }

    public void setPrecio(double Precio)
    {
        this.Precio = Precio;
    }

    public int getAño()
    {
        return Año;
    }

    public void setAño(int Año)
    {
        this.Año = Año;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }   

    public Libros(int Codigo, String Nombre, String Genero, double Precio, int Año, String Autor) 
    {
        setCodigo(Codigo);
        setNombre(Nombre);
        setGenero(Genero);
        setPrecio(Precio);
        setAño(Año);
        setAutor(Autor);
    }
    
     public Libros(String Nombre, String Genero, double Precio, int Año, String Autor) 
    {
        
        setNombre(Nombre);
        setGenero(Genero);
        setPrecio(Precio);
        setAño(Año);
        setAutor(Autor);
    }

   
}
