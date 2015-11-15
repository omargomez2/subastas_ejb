/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.exceptions;

/**
 *
 * @author omar s. gómez, 2015
 */
public class PrecioPujaNoValidoException extends Exception {

    /**
     * Creates a new instance of <code>PrecioPujaNoValidoException</code> without detail message.
     */
    public PrecioPujaNoValidoException() {
        super();
    }


    /**
     * Constructs an instance of <code>PrecioPujaNoValidoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public PrecioPujaNoValidoException(String msg) {
        super(msg);
    }
}
