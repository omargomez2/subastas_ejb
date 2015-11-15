/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.exceptions;

/**
 *
 * @author omar s. gómez, 2015
 */
public class PujaDuplicadaporPostorException extends Exception {

    /**
     * Creates a new instance of <code>PujaDuplicadaporPostorException</code> without detail message.
     */
    public PujaDuplicadaporPostorException() {
        super(); 
    }


    /**
     * Constructs an instance of <code>PujaDuplicadaporPostorException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public PujaDuplicadaporPostorException(String msg) {
        super(msg);
    }
}
