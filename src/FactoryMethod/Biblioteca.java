/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryMethod;
import patrones.conexion;

/**
 *
 * @author EST1659109
 */
public interface Biblioteca {
    public conexion conectar=null;
    public Libro libro=null;
    public CrearBiblioteca fabrica=null;
    
    public Libro metodoFabrica();
   
    
}
