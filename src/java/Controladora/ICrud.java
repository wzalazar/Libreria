/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

public interface ICrud 
{
    public void Agregar(Object objeto);
    public void Eliminar(Object objeto);
    public Object Buscar(Object objeto);
}
