/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author S1
 */
public class CategoryAlreadyExistsException extends Exception
{
    public CategoryAlreadyExistsException(String message)
    {
        
        super(message);
        
    }
    
    public CategoryAlreadyExistsException()
    {
        super();
    }
    
}
