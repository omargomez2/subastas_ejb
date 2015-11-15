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
import net.osgg.ejb.entities.Puja;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless
public class PujaFacade implements PujaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Puja puja) {
        em.persist(puja);
    }

    public void edit(Puja puja) {
        em.merge(puja);
    }

    public void remove(Puja puja) {
        em.remove(em.merge(puja));
    }

    public Puja find(Object id) {
        return em.find(net.osgg.ejb.entities.Puja.class, id);
    }

    public List<Puja> findAll() {
        return em.createQuery("select object(o) from Puja as o").getResultList();
    }
    
    public List<Puja> getPujas( String idSubasta){
        Query query = em.createQuery("SELECT p FROM Puja p WHERE p.idSubasta=:id ORDER BY p.precio");
        query.setParameter("id", idSubasta);        
        return query.getResultList();
    }
    
    public Puja getPujaUltimoPrecio( String idSubasta ){
        List<Puja> pujas = getPujas(idSubasta);
        if (!pujas.isEmpty())
          return pujas.get(pujas.size() -1 );
        else{
          Puja puja = new Puja();
          puja.setPrecio(0.0);
          return puja;
       }           
    }  

}
