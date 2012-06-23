/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author Fat Don
 */
public class StateAlreadyExistsException extends Exception
{
    public StateAlreadyExistsException()
    {
        super();
    }
    
    public StateAlreadyExistsException(String message)
    {
        super(message);
    }
}
