/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions;

import javax.ejb.Remote;

/**
 *
 * @author omar s. gómez, 2015
 */
@Remote
public interface GenIDRemote {

    String getUUID();
    
}
