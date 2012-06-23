/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author Fatih
 */
public class GroupConnectedToUserException extends Exception
{
    public GroupConnectedToUserException()
    {
        super();
    }
    
    public GroupConnectedToUserException(String message)
    {
        super(message);
    }
}
