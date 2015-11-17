/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.crud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.osgg.ejb.entities.Subasta;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless
public class SubastaCRUD implements SubastaCRUDLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Subasta subasta) {
        em.persist(subasta);
    }

    public void edit(Subasta subasta) {
        em.merge(subasta);
    }

    public void remove(Subasta subasta) {
        em.remove(em.merge(subasta));
    }

    public Subasta find(Object id) {
        return em.find(net.osgg.ejb.entities.Subasta.class, id);
    }

    public List<Subasta> findAll() {
        return em.createQuery("select object(o) from Subasta as o").getResultList();
    }

}
