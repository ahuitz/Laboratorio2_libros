/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patrones;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author EST1659109
 */
public class conexion {
    private static conexion miConexion;
    
    public static conexion getConexion(){
        if(miConexion==null)
            miConexion=new conexion();
        return miConexion;
    }
    
    private conexion(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Libros_JPA");
        EntityManager em =emf.createEntityManager();        
    }
}
