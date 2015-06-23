/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryMethod;

/**
 *
 * @author EST1659109
 */
public class CrearBiblioteca implements Biblioteca{
    Libros libro;

    //@Override
    public Libros EnlazarAutorLibro(Libros Nlibro) {
        return libro;
    }

    @Override
    public Libros EnlazarAutorLibro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
