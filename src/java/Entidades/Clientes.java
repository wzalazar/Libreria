package Entidades;

public class Clientes
{
    private int ID;
    private String Usuario;
    private String Contraseña;    
    private String Tipo;

    public String getTipo()
    {
        return Tipo;
    }

    public void setTipo(String Tipo)
    {
        this.Tipo = Tipo;
    }
    
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    public String getUsuario()
    {
        return Usuario;
    }

    public void setUsuario(String Usuario)
    {
        this.Usuario = Usuario;
    }

    public String getContraseña()
    {
        return Contraseña;
    }

    public void setContraseña(String Contraseña)
    {
        this.Contraseña = Contraseña;
    }

    public Clientes(int ID, String Usuario, String Contraseña, String Tipo)
    {
        setID(ID);
        setUsuario(Usuario);
        setContraseña(Contraseña);
        setTipo(Tipo);
    }  
    
    public Clientes(String Usuario, String Contraseña, String Tipo)
    {
        setUsuario(Usuario);
        setContraseña(Contraseña);
        setTipo(Tipo);
    }  
}


