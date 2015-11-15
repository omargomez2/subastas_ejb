/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.exceptions;

/**
 *
 * @author omar s. gómez, 2015
 */
public class RegistroSubastaException extends Exception {

    /**
     * Creates a new instance of <code>RegistroSubastaException</code> without detail message.
     */
    public RegistroSubastaException() {
        super(); 
    }


    /**
     * Constructs an instance of <code>RegistroSubastaException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public RegistroSubastaException(String msg) {
        super(msg);
    }
}
