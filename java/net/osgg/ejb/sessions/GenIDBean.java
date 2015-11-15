/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions;


import javax.ejb.Stateless;
import net.osgg.util.UUID;

/**
 *
 * @author omar s. gómez, 2015
 */
@Stateless(mappedName="GenIDBean")
public class GenIDBean implements GenIDRemote, GenIDLocal {

    public String getUUID() {
        UUID u = new UUID();
        return u.toString();
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
 
}
