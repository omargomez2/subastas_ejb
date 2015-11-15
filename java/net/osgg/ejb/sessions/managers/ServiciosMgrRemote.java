/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions.managers;

import java.util.List;
import javax.ejb.Remote;
import net.osgg.ejb.entities.Postor;
import net.osgg.ejb.entities.Puja;

/**
 *
 * @author omar s. gómez, 2015
 */
@Remote
public interface ServiciosMgrRemote {
    
    public long getSystemDateTime();

    public List<Puja> getPujas(String idSubasta);

    public java.lang.String getSystemDateTimeFormated();
    
}
