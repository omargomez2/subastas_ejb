/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.exceptions;

/**
 *
 * @author omar s. gómez, 2015
 */
public class PostorException extends Exception {

    /**
     * Creates a new instance of <code>PostorException</code> without detail message.
     */
    public PostorException() {
        super(); 
    }


    /**
     * Constructs an instance of <code>PostorException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public PostorException(String msg) {
        super(msg);
    }
}
