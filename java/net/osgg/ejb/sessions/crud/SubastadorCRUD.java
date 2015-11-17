/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.crud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.osgg.ejb.entities.Subastador;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless
public class SubastadorCRUD implements SubastadorCRUDLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Subastador subastador) {
        em.persist(subastador);
    }

    public void edit(Subastador subastador) {
        em.merge(subastador);
    }

    public void remove(Subastador subastador) {
        em.remove(em.merge(subastador));
    }

    public Subastador find(Object id) {
        return em.find(net.osgg.ejb.entities.Subastador.class, id);
    }

    public List<Subastador> findAll() {
        return em.createQuery("select object(o) from Subastador as o").getResultList();
    }

}
