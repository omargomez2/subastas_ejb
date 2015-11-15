/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.ejb.sessions;

import javax.ejb.Local;

/**
 *
 * @author omar s. gómez, 2015
 */
@Local
public interface GenIDLocal {

    String getUUID();
    
}
