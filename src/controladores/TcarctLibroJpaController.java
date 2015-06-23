/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import FactoryMethod.TcarctLibro;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import FactoryMethod.Libros;

/**
 *
 * @author EST1659109
 */
public class TcarctLibroJpaController implements Libros {

    public TcarctLibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TcarctLibro tcarctLibro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tcarctLibro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TcarctLibro tcarctLibro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tcarctLibro = em.merge(tcarctLibro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tcarctLibro.getId();
                if (findTcarctLibro(id) == null) {
                    throw new NonexistentEntityException("The tcarctLibro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TcarctLibro tcarctLibro;
            try {
                tcarctLibro = em.getReference(TcarctLibro.class, id);
                tcarctLibro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tcarctLibro with id " + id + " no longer exists.", enfe);
            }
            em.remove(tcarctLibro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TcarctLibro> findTcarctLibroEntities() {
        return findTcarctLibroEntities(true, -1, -1);
    }

    public List<TcarctLibro> findTcarctLibroEntities(int maxResults, int firstResult) {
        return findTcarctLibroEntities(false, maxResults, firstResult);
    }

    private List<TcarctLibro> findTcarctLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TcarctLibro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TcarctLibro findTcarctLibro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TcarctLibro.class, id);
        } finally {
            em.close();
        }
    }

    public int getTcarctLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TcarctLibro> rt = cq.from(TcarctLibro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
