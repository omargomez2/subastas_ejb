/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.facades;

import java.util.List;
import javax.ejb.Local;
import net.osgg.ejb.entities.Postor;

/**
 *
 * @author omar s. gómez, 2015
 */
@Local
public interface PostorFacadeLocal {

    void create(Postor postor);

    void edit(Postor postor);

    void remove(Postor postor);

    Postor find(Object id);

    List<Postor> findAll();

}
