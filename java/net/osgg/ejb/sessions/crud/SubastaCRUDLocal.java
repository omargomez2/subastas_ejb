/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.crud;

import java.util.List;
import javax.ejb.Local;
import net.osgg.ejb.entities.Subasta;

/**
 *
 * @author omar s. gómez, 2015
 */
@Local
public interface SubastaCRUDLocal {

    void create(Subasta subasta);

    void edit(Subasta subasta);

    void remove(Subasta subasta);

    Subasta find(Object id);

    List<Subasta> findAll();

}
