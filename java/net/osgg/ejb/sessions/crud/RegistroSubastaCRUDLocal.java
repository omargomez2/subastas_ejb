/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.crud;

import java.util.List;
import javax.ejb.Local;
import net.osgg.ejb.entities.RegistroSubasta;

/**
 *
 * @author omar s. gómez, 2015
 */
@Local
public interface RegistroSubastaCRUDLocal {

    void create(RegistroSubasta registroSubasta);

    void edit(RegistroSubasta registroSubasta);

    void remove(RegistroSubasta registroSubasta);

    RegistroSubasta find(Object id);

    List<RegistroSubasta> findAll();

    public RegistroSubasta getRegistroSubasta(String idPostor, String idSubasta);

}
