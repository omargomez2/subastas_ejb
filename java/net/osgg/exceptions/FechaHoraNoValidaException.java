/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.exceptions;

/**
 *
 * @author omar s. gómez, 2015
 */
public class FechaHoraNoValidaException extends Exception {

    /**
     * Creates a new instance of <code>FechaHoraNoValidaException</code> without detail message.
     */
    public FechaHoraNoValidaException() {
        super(); 
    }


    /**
     * Constructs an instance of <code>FechaHoraNoValidaException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public FechaHoraNoValidaException(String msg) {
        super(msg);
    }
}
