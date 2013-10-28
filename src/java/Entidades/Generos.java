package Entidades;

public class Generos
{
    private int Codigo;
    private String Nombre;

    public int getCodigo()
    {
        return Codigo;
    }

    public void setCodigo(int Codigo)
    {
        this.Codigo = Codigo;
    }

    public String getNombre()
    {
        return Nombre;
    }

    public void setNombre(String Nombre)
    {
        this.Nombre = Nombre;
    }

    public Generos(int Codigo, String Nombre)
    {
        setCodigo(Codigo);
        setNombre(Nombre);
    }    
}
