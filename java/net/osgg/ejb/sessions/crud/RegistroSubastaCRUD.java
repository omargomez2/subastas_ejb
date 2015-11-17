/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.crud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.osgg.ejb.entities.RegistroSubasta;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless
public class RegistroSubastaCRUD implements RegistroSubastaCRUDLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(RegistroSubasta registroSubasta) {
        em.persist(registroSubasta);
    }

    public void edit(RegistroSubasta registroSubasta) {
        em.merge(registroSubasta);
    }

    public void remove(RegistroSubasta registroSubasta) {
        em.remove(em.merge(registroSubasta));
    }

    public RegistroSubasta find(Object id) {
        return em.find(net.osgg.ejb.entities.RegistroSubasta.class, id);
    }

    public List<RegistroSubasta> findAll() {
        return em.createQuery("select object(o) from RegistroSubasta as o").getResultList();
    }
    
    public RegistroSubasta getRegistroSubasta(String idPostor, String idSubasta){
        Query query = em.createQuery("SELECT r FROM RegistroSubasta r WHERE r.idPostor=:idPostor and r.idSubasta=:idSubasta");
        query.setParameter("idPostor", idPostor);
        query.setParameter("idSubasta", idSubasta);
        try{
            return (RegistroSubasta) query.getSingleResult();
        }catch (javax.persistence.NoResultException nre){
            return new RegistroSubasta();
        }
        
    }

}
