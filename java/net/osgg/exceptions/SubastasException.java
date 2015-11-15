/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.exceptions;

/**
 *
 * @author omar s. gómez, 2015
 */
public class SubastasException extends Exception {

    /**
     * Creates a new instance of <code>SubastasException</code> without detail message.
     */
    public SubastasException() {
        super(); 
    }


    /**
     * Constructs an instance of <code>SubastasException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SubastasException(String msg) {
        super(msg);
    }
}
