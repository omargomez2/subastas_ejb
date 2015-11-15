/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.osgg.ejb.entities.Postor;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless
public class PostorFacade implements PostorFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Postor postor) {
        em.persist(postor);
    }

    public void edit(Postor postor) {
        em.merge(postor);
    }

    public void remove(Postor postor) {
        em.remove(em.merge(postor));
    }

    public Postor find(Object id) {
        return em.find(net.osgg.ejb.entities.Postor.class, id);
    }

    public List<Postor> findAll() {
        return em.createQuery("select object(o) from Postor as o").getResultList();
    }

}
