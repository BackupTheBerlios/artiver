/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author Matze
 */
public class UserNotFoundException extends Exception 
{

    public UserNotFoundException(String message)
    {
       super(message);
    }

    public UserNotFoundException()
    {
       super();
    }
    
}
