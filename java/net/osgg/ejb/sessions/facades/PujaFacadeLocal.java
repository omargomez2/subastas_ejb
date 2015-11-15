/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.facades;

import java.util.List;
import javax.ejb.Local;
import net.osgg.ejb.entities.Puja;

/**
 *
 * @author omar s. gómez, 2015
 */
@Local
public interface PujaFacadeLocal {

    void create(Puja puja);

    void edit(Puja puja);

    void remove(Puja puja);

    Puja find(Object id);

    List<Puja> findAll();

    public List<Puja> getPujas(String idSubasta);

    public Puja getPujaUltimoPrecio(String idSubasta);

}
