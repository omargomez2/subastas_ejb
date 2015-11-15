/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.facades;

import java.util.List;
import javax.ejb.Local;
import net.osgg.ejb.entities.Subastador;

/**
 *
 * @author omar s. gómez, 2015
 */
@Local
public interface SubastadorFacadeLocal {

    void create(Subastador subastador);

    void edit(Subastador subastador);

    void remove(Subastador subastador);

    Subastador find(Object id);

    List<Subastador> findAll();

}
