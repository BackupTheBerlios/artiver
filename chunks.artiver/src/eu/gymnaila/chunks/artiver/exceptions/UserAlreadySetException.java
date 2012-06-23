/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author User
 */
public class UserAlreadySetException extends Exception 
{

    public UserAlreadySetException(String message)
    {
       super(message);
    }

    public UserAlreadySetException()
    {
       super();
    }
    
}
