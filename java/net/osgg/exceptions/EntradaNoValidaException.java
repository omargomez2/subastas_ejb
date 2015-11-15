/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.exceptions;




/**
 *
 * @author omar s. gómez, 2015
 */
public class EntradaNoValidaException extends Exception {

    /**
     * Creates a new instance of <code>EntradaNoValidaException</code> without detail message.
     */
    public EntradaNoValidaException() {
        super(); 
    }


    /**
     * Constructs an instance of <code>EntradaNoValidaException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public EntradaNoValidaException(String msg) {
        super(msg);
    }
}
