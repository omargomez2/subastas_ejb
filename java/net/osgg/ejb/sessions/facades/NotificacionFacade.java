/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.osgg.ejb.entities.Notificacion;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless
public class NotificacionFacade implements NotificacionFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Notificacion notificacion) {
        em.persist(notificacion);
    }

    public void edit(Notificacion notificacion) {
        em.merge(notificacion);
    }

    public void remove(Notificacion notificacion) {
        em.remove(em.merge(notificacion));
    }

    public Notificacion find(Object id) {
        return em.find(net.osgg.ejb.entities.Notificacion.class, id);
    }

    public List<Notificacion> findAll() {
        return em.createQuery("select object(o) from Notificacion as o").getResultList();
    }
    
    public List<Notificacion> getNotificaciones( String idSubasta){
        Query query = em.createQuery("SELECT n FROM Notificacion n WHERE n.idSubasta=:id");
        query.setParameter("id", idSubasta);        
        return query.getResultList(); 
    }
    

}
